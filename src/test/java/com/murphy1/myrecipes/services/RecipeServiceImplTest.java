package com.murphy1.myrecipes.services;

import com.murphy1.myrecipes.model.Recipe;
import com.murphy1.myrecipes.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    RecipeServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void findRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe returnedRecipe = service.findRecipeById(1L);

        assertNotNull(returnedRecipe);
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    void saveRecipe(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeRepository.save(any())).thenReturn(recipe);
        Recipe returnedRecipe = recipeRepository.save(recipe);

        assertNotNull(returnedRecipe);
        verify(recipeRepository, times(1)).save(any());
    }
}