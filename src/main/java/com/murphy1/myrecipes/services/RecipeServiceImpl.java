package com.murphy1.myrecipes.services;

import com.murphy1.myrecipes.model.Recipe;
import com.murphy1.myrecipes.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {

        Set<Recipe> recipeSet = new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(recipeSet :: add);

        return recipeSet;

    }

    public Recipe findRecipeById(Long l){

        Optional<Recipe> returnedRecipe = recipeRepository.findById(l);

        if(!returnedRecipe.isPresent()){
            throw new RuntimeException("Recipe not found!");
        }

        return returnedRecipe.get();
    }

    @Override
    @Transactional
    public Recipe saveRecipe(Recipe recipe) {
        Recipe savedRecipe = recipeRepository.save(recipe);
        return savedRecipe;
    }

    @Override
    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }
}
