package com.murphy1.myrecipes.services;

import com.murphy1.myrecipes.model.Recipe;
import com.murphy1.myrecipes.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        return null;
    }
}
