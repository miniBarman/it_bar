package com.project.itbar.controller;

import com.project.itbar.domain.*;
import com.project.itbar.repos.CoctailIngredientRepo;
import com.project.itbar.repos.CoctailRepo;
import com.project.itbar.repos.IngredientRepo;
import com.project.itbar.repos.UserRepo;
import com.project.itbar.utils.Constants;
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

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

    @Value("${upload.path}")
    private String uploadPath;//ToDo probably need to move this in some one place in Application or somthing like that

    //just redirection from / to /main
    //maybe we can use something more smooth???
    @GetMapping("/")
    public String main(Model model) {
        return "redirect:/main";
    }

    //same problem WA for login button
    @GetMapping("/main_login")
    public String mainLogin(Model model) {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Coctail> coctails;

        if (filter != null && !filter.isEmpty()) {
            coctails = coctailRepo.findByName(filter);
        } else {
            coctails = coctailRepo.findAll();
        }

        model.addAttribute("coctails", coctails);
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
        Map<Integer, Map<Coctail, List<Ingredient>>> resultMap = new HashMap<>();

        for (Coctail coctail : coctails){
            System.out.println("processing: " + coctail.getName());
            List<Ingredient> missingIngredientsList = getMissingIngredientsList(coctail, ingredients);
            int missingIngredientsCount = missingIngredientsList != null ? missingIngredientsList.size() : 0;
            System.out.println("missingIngredientsCount: " + missingIngredientsCount);
            Map<Coctail, List<Ingredient>> coctailWithMissingIngredients = resultMap.get(missingIngredientsCount);
            if (coctailWithMissingIngredients == null){
                System.out.println("There is no map for this count");
                coctailWithMissingIngredients = new HashMap<>();
                resultMap.put(missingIngredientsCount, coctailWithMissingIngredients);
            }
            System.out.println("put coctail map for " + coctail);
            coctailWithMissingIngredients.put(coctail, missingIngredientsList);
        }
        return resultMap;
    }

    private List<Ingredient> getMissingIngredientsList(Coctail coctail, List<Ingredient> ingredients) {
        List<Ingredient> missingIngredients = new LinkedList();
        for(CoctailIngredient coctailIngredient : coctail.getCoctailIngredients()){
            Ingredient ingredient = coctailIngredient.getIngredient();
            System.out.println("processing: " + ingredient.getName());
            if(!ingredients.contains(ingredient)){
                System.out.println("ingredient is missing");
                missingIngredients.add(ingredient);
            }
        }
        return missingIngredients;
    }

    //don't know but page doesn't work without this stuff
    //probably there should be at least 1 get method on page
    @GetMapping("/add_coctail")
    public String addCoctail(Model model){
        return "add_coctail";
    }

    @PostMapping("/add_coctail")
    public String addCoctail(@AuthenticationPrincipal User user,
                      @RequestParam String name,
                      @RequestParam String description,
                      Map<String, Object> model,
                      @RequestParam("file") MultipartFile file) throws IOException {

        Coctail coctail = new Coctail(name, description, user);

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
        coctailRepo.save(coctail);
        model.put("message", "Коктейль " + name + " был успешно добавлен");

        return "add_coctail";
    }

    //don't know but page doesn't work without this stuff
    //probably there should be at least 1 get method on page
    @GetMapping("/add_ingredient")
    public String addIngredient(Model model){
        return "add_ingredient";
    }

    @PostMapping("/add_ingredient")
    public String addIngredient(@AuthenticationPrincipal User user,
                             @RequestParam String name,
                             @RequestParam String description,
                             Map<String, Object> model,
                             @RequestParam("file") MultipartFile file) throws IOException {

        Ingredient ingredient = new Ingredient(name, description, user);

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
        model.put("message", "Ингредиент " + name + " был успешно добавлен");

        return "add_ingredient";
    }

    @GetMapping("/ingredients")
    public String getIngredients(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        if (filter != null && !filter.isEmpty()) {
            Iterable<Ingredient> ingredients = ingredientRepo.findByName(filter);
            model.addAttribute("ingredients", ingredients);
        }

        Map<String, Iterable<Ingredient>> ingredientsByGroups = new HashMap<>();
        for (IngredientGroup group : IngredientGroup.values()) {
            Iterable<Ingredient> ingredientsByGroup = ingredientRepo.findByIngredientGroup(group);
            if (ingredientsByGroup != null)
                ingredientsByGroups.put(Constants.INGREDIENT_GROUP_MAPPING.get(group.name()), ingredientsByGroup);
        }
        System.out.println(ingredientsByGroups);

        model.addAttribute("ingredientsByGroups", ingredientsByGroups);
        model.addAttribute("allIngredients", ingredientRepo.findAll());
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

        return getIngredients("", model);
    }

    @PostMapping("/delete_ingredient_from_bar")
    public String deleteIngredientFromBar(@AuthenticationPrincipal User user,
                                     @RequestParam Ingredient ingredient, Model model){

        user.getBarIngredients().remove(ingredient);
        userRepo.save(user);

        return getIngredients("", model);
    }

}