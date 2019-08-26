package com.murphy1.myrecipes.repositories;

import com.murphy1.myrecipes.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
