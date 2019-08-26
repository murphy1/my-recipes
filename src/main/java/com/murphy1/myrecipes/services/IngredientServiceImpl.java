package com.murphy1.myrecipes.services;

import com.murphy1.myrecipes.model.Ingredient;
import com.murphy1.myrecipes.model.Recipe;
import com.murphy1.myrecipes.model.UnitOfMeasure;
import com.murphy1.myrecipes.repositories.IngredientRepository;
import com.murphy1.myrecipes.repositories.RecipeRepository;
import com.murphy1.myrecipes.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository uomRepository;
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository uomRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
        this.ingredientRepository = ingredientRepository;
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

    @Override
    @Transactional
    public Ingredient saveIngredient(Ingredient ingredient) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredient.getRecipe().getId());

        if(!recipeOptional.isPresent()){
            //todo toss error if not found!
            log.error("Recipe not found for id: " + ingredient.getRecipe().getId());
            return new Ingredient();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient1 -> ingredient1.getId().equals(ingredient.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(ingredient.getDescription());
                ingredientFound.setAmount(ingredient.getAmount());
                ingredientFound.setUnitOfMeasure(uomRepository
                        .findById(ingredient.getUnitOfMeasure().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                //add new Ingredient
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Long newIngredientId = savedRecipe.getIngredients().stream()
                    .filter(ingredient1 -> ingredient1.getDescription().equals(ingredient.getDescription()))
                    .findFirst().get().getId();

            ingredient.setId(newIngredientId);

            //to do check for fail
            return savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredient.getId()))
                    .findFirst()
                    .get();
        }
    }

    @Override
    public void deleteIngredient(Long ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }
}
