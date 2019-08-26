package com.murphy1.myrecipes.services;

import com.murphy1.myrecipes.model.Ingredient;
import com.murphy1.myrecipes.model.Recipe;
import com.murphy1.myrecipes.repositories.IngredientRepository;
import com.murphy1.myrecipes.repositories.RecipeRepository;
import com.murphy1.myrecipes.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository uomRepository;

    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    Recipe recipeMock;

    IngredientServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new IngredientServiceImpl(recipeRepository, uomRepository, ingredientRepository);
    }

    @Test
    void getIngredientByIdTest() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        Ingredient ingredient1 = new Ingredient();
        ingredient.setId(2L);

        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient);
        ingredients.add(ingredient1);

        Optional<Recipe> recipeOptional = recipeRepository.findById(anyLong());

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeMock.getIngredients()).thenReturn(ingredients);
        Set<Ingredient> returnedIngredients =  recipe.getIngredients();

        assertNotNull(recipeOptional);
        assertNotNull(ingredients);
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    void deleteIngredientTest() throws Exception{
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);

        ingredientRepository.deleteById(1L);

        verify(ingredientRepository, times(1)).deleteById(anyLong());
    }

}