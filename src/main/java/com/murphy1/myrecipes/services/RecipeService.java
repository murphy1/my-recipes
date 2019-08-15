package com.murphy1.myrecipes.services;

import com.murphy1.myrecipes.model.Recipe;
import com.murphy1.myrecipes.repositories.RecipeRepository;

import java.util.Set;

public interface RecipeService{
    Set<Recipe> getRecipes();
}
