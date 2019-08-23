package com.murphy1.myrecipes.services;

import com.murphy1.myrecipes.model.Ingredient;
import com.murphy1.myrecipes.model.Recipe;
import com.murphy1.myrecipes.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Ingredient getIngredientById(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe not found!");
        }

        Recipe recipe = recipeOptional.get();
        Ingredient returnIngredient = new Ingredient();

        for (Ingredient ingredient : recipe.getIngredients()){
            if (ingredient.getId().equals(ingredientId)){
                returnIngredient = ingredient;
            }
        }

        return returnIngredient;
    }
}
