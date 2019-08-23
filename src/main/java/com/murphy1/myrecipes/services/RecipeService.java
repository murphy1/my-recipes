package com.murphy1.myrecipes.services;

import com.murphy1.myrecipes.model.Ingredient;
import com.murphy1.myrecipes.model.Recipe;

import java.util.Set;

public interface RecipeService{
    Set<Recipe> getRecipes();
    Recipe findRecipeById(Long l);
    Recipe saveRecipe(Recipe recipe);
    void deleteRecipeById(Long id);
}
