package com.project.itbar.repos;

import com.project.itbar.domain.Coctail;
import com.project.itbar.domain.CoctailIngredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoctailIngredientRepo extends CrudRepository<Coctail, Long> {

    List<CoctailIngredient> findByName(String name);
}
