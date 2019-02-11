package com.project.itbar.repos;

import com.project.itbar.domain.Coctail;
import com.project.itbar.domain.CoctailIngredient;
import com.project.itbar.domain.CoctailIngredientPK;
import com.project.itbar.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoctailIngredientRepo extends CrudRepository<CoctailIngredient, CoctailIngredientPK> {

    List<CoctailIngredient> findByCoctailIngredientPKCoctail(Coctail coctail);
    List<CoctailIngredient> findByCoctailIngredientPKIngredient(Ingredient ingredient);
}