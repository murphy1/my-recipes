package com.murphy1.myrecipes.controllers;

import com.murphy1.myrecipes.model.Recipe;
import com.murphy1.myrecipes.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    IngredientController ingredientController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    void showIngredients() throws Exception{
        Recipe recipe = new Recipe();

        when(recipeService.findRecipeById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipes/1/ingredient"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("recipes/ingredient/list"));

        verify(recipeService, times(1)).findRecipeById(anyLong());
    }

}
