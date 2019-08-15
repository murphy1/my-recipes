package com.murphy1.myrecipes.repositories;

import com.murphy1.myrecipes.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
