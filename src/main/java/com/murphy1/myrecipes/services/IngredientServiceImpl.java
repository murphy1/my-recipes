package com.murphy1.myrecipes.services;

import com.murphy1.myrecipes.model.Ingredient;
import com.murphy1.myrecipes.model.Recipe;
import com.murphy1.myrecipes.repositories.RecipeRepository;
import com.murphy1.myrecipes.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository uomRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository uomRepository) {
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
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

        Long ingredientId = ingredient.getId();

        Recipe testRecipe = ingredient.getRecipe();

        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredient.getRecipe().getId());

        if (!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe not found!");
        }
        Recipe recipe = recipeOptional.get();
        Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                .filter(ingredient1 -> ingredient1.getId().equals(ingredient.getId()))
                .findFirst();

        if (ingredientOptional.isPresent()){
            Ingredient ingredientFound = ingredientOptional.get();

            ingredientFound.setDescription(ingredient.getDescription());
            ingredientFound.setAmount(ingredient.getAmount());
            ingredientFound.setUnitOfMeasure(uomRepository.findByDescription(ingredient.getUnitOfMeasure()
                    .getDescription()).get());
        }else{
            recipe.addIngredient(ingredient);
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        return savedRecipe.getIngredients().stream()
                .filter(ingredient1 -> ingredient1.getId().equals(ingredient.getId())).findFirst().get();
    }
}
