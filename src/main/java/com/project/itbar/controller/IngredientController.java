package com.project.itbar.controller;

import com.project.itbar.domain.Coctail;
import com.project.itbar.domain.Ingredient;
import com.project.itbar.repos.CoctailRepo;
import com.project.itbar.repos.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    @Autowired
    private IngredientRepo ingredientRepo;

    @GetMapping
    public List<Ingredient> list(){
        return StreamSupport.stream(ingredientRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
