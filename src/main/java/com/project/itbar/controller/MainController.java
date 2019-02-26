package com.project.itbar.controller;

import com.project.itbar.domain.Coctail;
import com.project.itbar.domain.CoctailIngredient;
import com.project.itbar.domain.Ingredient;
import com.project.itbar.domain.User;
import com.project.itbar.repos.CoctailIngredientRepo;
import com.project.itbar.repos.CoctailRepo;
import com.project.itbar.repos.IngredientRepo;
import com.project.itbar.repos.UserRepo;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        Iterable<Ingredient> ingredients;

        if (filter != null && !filter.isEmpty()) {
            ingredients = ingredientRepo.findByName(filter);
        } else {
            ingredients = ingredientRepo.findAll();
        }

        model.addAttribute("ingredients", ingredients);
        model.addAttribute("filter", filter);

        return "ingredients";
    }

    @GetMapping("ingredient/{ingredient}")
    public String ingredient(@PathVariable Ingredient ingredient, Model model) {
        model.addAttribute("ingredient", ingredient);

        List<CoctailIngredient> coctailIngredients = coctailIngredientRepo.findByCoctailIngredientPKIngredient(ingredient);

        //toporno?
        List<Coctail> coctails = new LinkedList<>();
        for(CoctailIngredient coctailIngredient : coctailIngredients){
            coctails.add(coctailIngredient.getCoctail());
        }

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