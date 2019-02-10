package com.project.itbar.repos;

import com.project.itbar.domain.Coctail;
import com.project.itbar.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepo extends CrudRepository<Ingredient, Long> {

    List<Ingredient> findByName(String name);
}
