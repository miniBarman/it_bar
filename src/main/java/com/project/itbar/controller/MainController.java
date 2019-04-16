package com.project.itbar.controller;

import com.project.itbar.domain.*;
import com.project.itbar.repos.*;
import com.project.itbar.utils.Constants;
import com.project.itbar.utils.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class MainController {
    @Autowired
    private CoctailRepo coctailRepo;
    @Autowired
    private IngredientRepo ingredientRepo;
    @Autowired
    private CoctailIngredientRepo coctailIngredientRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LabelRepo labelRepo;

    @Value("${upload.path}")
    private String uploadPath;//ToDo probably need to move this in some one place in Application or somthing like that

    @GetMapping("/")
    public String main(Model model) {
        return "redirect:/main";
    }

    @GetMapping("/help")
    public String help(Model model) {
        return "/help";
    }

    @GetMapping("/main_login")
    public String mainLogin(Model model) {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User user,
                       @RequestParam(required = false, defaultValue = "") String filter,
                       Model model) {
        User systemUser = userRepo.findByUsername("system");
        Collection<User> users = new LinkedList<>();

        if(user != null){
            users = Stream.of(user, systemUser).collect(Collectors.toList());
        }

        Iterable<Coctail> allCoctails = user != null ? coctailRepo.findByAuthorIn(users) : coctailRepo.findByAuthor(systemUser);
        Iterable<Coctail> coctails;

        if (filter != null && !filter.isEmpty()) {
            coctails = user != null ? coctailRepo.findByAuthorInAndNameContainingIgnoreCase(users, filter) : coctailRepo.findByAuthorAndNameContainingIgnoreCase(systemUser, filter);
        } else {
            coctails = allCoctails;
        }


        model.addAttribute("coctails", coctails);
        model.addAttribute("allCoctails", allCoctails);
        model.addAttribute("filter", filter);

        return "main";
    }

    @GetMapping("/possible_coctails")
    public String possibleCoctails(@AuthenticationPrincipal User user,
                                   @RequestParam(required = false, defaultValue = "") String filter,
                                   Model model) {
        Iterable<Coctail> coctails;

        if (filter != null && !filter.isEmpty()) {
            coctails = coctailRepo.findByName(filter);
        } else {
            coctails = coctailRepo.findAll();
        }

        Map<Integer, Map<Coctail, List<Ingredient>>> cocteilsByExistenceInBar = sortByExistence(coctails, user.getBarIngredients());

        model.addAttribute("cocteilsByExistenceInBar", cocteilsByExistenceInBar);
        model.addAttribute("filter", filter);

        return "possible_coctails";
    }

    private Map<Integer, Map<Coctail, List<Ingredient>>> sortByExistence(Iterable<Coctail> coctails, List<Ingredient> ingredients) {
        Map<Integer, Map<Coctail, List<Ingredient>>> resultMap = new TreeMap<>();

        for (Coctail coctail : coctails){
            List<Ingredient> missingIngredientsList = getMissingIngredientsList(coctail, ingredients);
            int missingIngredientsCount = missingIngredientsList != null ? missingIngredientsList.size() : 0;
            Map<Coctail, List<Ingredient>> coctailWithMissingIngredients = resultMap.get(missingIngredientsCount);
            if (coctailWithMissingIngredients == null){
                coctailWithMissingIngredients = new HashMap<>();
                resultMap.put(missingIngredientsCount, coctailWithMissingIngredients);
            }
            coctailWithMissingIngredients.put(coctail, missingIngredientsList);
        }
        return resultMap;
    }

    private List<Ingredient> getMissingIngredientsList(Coctail coctail, List<Ingredient> ingredients) {
        List<Ingredient> missingIngredients = new LinkedList();
        for(CoctailIngredient coctailIngredient : coctail.getCoctailIngredients()){
            Ingredient ingredient = coctailIngredient.getIngredient();
            if(!ingredients.contains(ingredient)){
                missingIngredients.add(ingredient);
            }
        }
        return missingIngredients;
    }

    @GetMapping("/add_coctail")
    public String addCoctail(@AuthenticationPrincipal User user, Model model){
        User systemUser = userRepo.findByUsername("system");
        Collection<User> users = Stream.of(user, systemUser).collect(Collectors.toList());;
        model.addAttribute("allIngredients", ingredientRepo.findByAuthorIn(users));
        model.addAttribute("unitList", Constants.UNIT_LIST);
        model.addAttribute("coctailLabels", labelRepo.findAll());
        return "add_coctail";
    }

    @PostMapping("/add_coctail")
    public String addCoctail(@AuthenticationPrincipal User user,
                      @RequestParam String name,
                      @RequestParam String description,
                      @RequestParam String recipe,
                      @RequestParam String coctailLabels,
                      @RequestParam List<String> ingredients,
                      @RequestParam List<String> volumes,
                      @RequestParam List<String> units,
                      RedirectAttributes redirectAttributes,
                      @RequestParam("file") MultipartFile file) throws IOException {
        Coctail coctail = new Coctail(name, recipe, description, user);

        if(file != null  && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/img/" + resultFilename));

            coctail.setImage(resultFilename);
        }

        List<CoctailIngredient> coctailIngredientList = new LinkedList<>();
        for (int i = 0; i < ingredients.size(); i++) {
            //TODO: check this field
            Ingredient ingredient = ingredientRepo.findByName(ingredients.get(i)).get(0);
            //TODO: check this field
            float volume = Float.parseFloat(volumes.get(i));
            if (ingredient != null){
                coctailIngredientList.add(new CoctailIngredient(coctail, ingredient, volume, units.get(i)));
            }
        }

        coctail.setCoctailIngredients(coctailIngredientList);

        if (coctailLabels != null && !coctailLabels.isEmpty()) {
            List<Label> coctailLabelsList = new LinkedList<>();
            for (String labelName : coctailLabels.split(",")) {
                List<Label> labelList = labelRepo.findByName(labelName);
                if (!labelList.isEmpty()) {
                    coctailLabelsList.add(labelList.get(0));
                } else {
                    Label label = new Label(labelName);
                    labelRepo.save(label);
                    coctailLabelsList.add(label);
                }
            }

            coctail.setLabels(coctailLabelsList);
        }

        coctailRepo.save(coctail);
        redirectAttributes.addFlashAttribute("message", new SystemMessage(Constants.MessageType.SUCCESS, "Коктейль " + name + " был успешно добавлен"));

        return "redirect:/add_coctail";
    }

    @GetMapping("/add_ingredient")
    public String addIngredient(Model model){
        model.addAttribute("ingredientGroups",
                Arrays.stream(IngredientGroup.values()).map(IngredientGroup::name).map(Constants.INGREDIENT_GROUP_MAPPING::get).collect(Collectors.toList()));
        return "/add_ingredient";
    }

    @PostMapping("/add_ingredient")
    public String addIngredient(@AuthenticationPrincipal User user,
                             @RequestParam String name,
                             @RequestParam String description,
                             @RequestParam String groupName,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("file") MultipartFile file) throws IOException {

        System.out.println("test post");

        String ingredientGroup = Constants.INGREDIENT_GROUP_MAPPING.entrySet().stream()
                .filter(e -> e.getValue().equals(groupName))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        Ingredient ingredient = new Ingredient(name, description, ingredientGroup, user);

        if(file != null  && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/img/" + resultFilename));

            ingredient.setImage(resultFilename);
        }
        ingredientRepo.save(ingredient);

        redirectAttributes.addFlashAttribute("message", new SystemMessage(Constants.MessageType.SUCCESS, "Ингредиент " + name + " был успешно добавлен"));
        return "redirect:/add_ingredient";
    }

    @GetMapping("/ingredients")
    public String getIngredients(@AuthenticationPrincipal User user,
                                 @RequestParam(required = false, defaultValue = "") String filter,
                                 Model model) {
        User systemUser = userRepo.findByUsername("system");
        Collection<User> users = new LinkedList<>();

        if(user != null){
            users = Stream.of(user, systemUser).collect(Collectors.toList());
        }

        Map<String, Iterable<Ingredient>> ingredientsByGroups = new LinkedHashMap<>();

        //TODO: probably should be refactored
        for (IngredientGroup group : IngredientGroup.values()) {
            List<Ingredient> ingredientsByGroup = new LinkedList();

            if (filter != null && !filter.isEmpty()) {
                ingredientsByGroup = user != null ? ingredientRepo.findByAuthorInAndIngredientGroupAndNameContainingIgnoreCase(users, group, filter)
                        : ingredientRepo.findByAuthorAndIngredientGroupAndNameContainingIgnoreCase(systemUser, group, filter);
            } else {
                ingredientsByGroup = user != null ? ingredientRepo.findByAuthorInAndIngredientGroup(users, group)
                        : ingredientRepo.findByAuthorAndIngredientGroup(systemUser, group);
            }

            if (ingredientsByGroup != null && !ingredientsByGroup.isEmpty())
                ingredientsByGroups.put(Constants.INGREDIENT_GROUP_MAPPING.get(group.name()), ingredientsByGroup);
        }

        model.addAttribute("ingredientsByGroups", ingredientsByGroups);
        model.addAttribute("allIngredients", user != null ? ingredientRepo.findByAuthorIn(users)
                : ingredientRepo.findByAuthor(systemUser));
        model.addAttribute("filter", filter);

        return "ingredients";
    }

    @GetMapping("ingredient/{ingredient}")
    public String ingredient(@PathVariable Ingredient ingredient, Model model) {

        model.addAttribute("ingredient", ingredient);
        List<CoctailIngredient> coctailIngredients = coctailIngredientRepo.findByCoctailIngredientPKIngredient(ingredient);
        List<Coctail> coctails =  coctailIngredients.stream().map(CoctailIngredient::getCoctail).collect(Collectors.toList());
        model.addAttribute("coctails", coctails);

        return "ingredient";
    }

    @PostMapping("/add_ingredient_to_bar")
    public String addIngredientToBar(@AuthenticationPrincipal User user,
                                @RequestParam Ingredient ingredient, Model model){

        user.getBarIngredients().add(ingredient);
        userRepo.save(user);

        return "redirect:/ingredients";
    }

    @PostMapping("/delete_ingredient_from_bar")
    public String deleteIngredientFromBar(@AuthenticationPrincipal User user,
                                     @RequestParam Ingredient ingredient, Model model){

        user.getBarIngredients().remove(ingredient);
        userRepo.save(user);

        return "redirect:/ingredients";
    }

}