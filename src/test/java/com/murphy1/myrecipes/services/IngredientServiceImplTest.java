package com.murphy1.myrecipes.services;

import com.murphy1.myrecipes.model.Ingredient;
import com.murphy1.myrecipes.model.Recipe;
import com.murphy1.myrecipes.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    IngredientServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new IngredientServiceImpl(recipeRepository);
    }
}