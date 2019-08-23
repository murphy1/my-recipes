package com.murphy1.myrecipes.services;

import com.murphy1.myrecipes.model.Ingredient;

public interface IngredientService {
    Ingredient getIngredientById(Long recipeId, Long ingredientId);
}
