package com.project.itbar.repos;

import com.project.itbar.domain.Coctail;
import com.project.itbar.domain.Ingredient;
import com.project.itbar.domain.IngredientGroup;
import com.project.itbar.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public interface IngredientRepo extends CrudRepository<Ingredient, BigInteger> {

    List<Ingredient> findByName(String name);

    List<Ingredient> findByIngredientGroup(IngredientGroup ingredientGroup);

    List <Ingredient> findByAuthor(User systemUser);

    List<Ingredient> findByAuthorIn(Collection<User> users);

    List<Ingredient> findByAuthorAndNameContainingIgnoreCase(User user, String name);

    List<Ingredient> findByAuthorInAndNameContainingIgnoreCase(Collection<User> users, String filter);

    List<Ingredient> findByAuthorInAndIngredientGroup(Collection<User> users, IngredientGroup group);

    List<Ingredient> findByAuthorAndIngredientGroup(User user, IngredientGroup group);

    List<Ingredient> findByAuthorInAndIngredientGroupAndNameContainingIgnoreCase(Collection<User> users, IngredientGroup group, String filter);

    List<Ingredient> findByAuthorAndIngredientGroupAndNameContainingIgnoreCase(User systemUser, IngredientGroup group, String filter);
}
