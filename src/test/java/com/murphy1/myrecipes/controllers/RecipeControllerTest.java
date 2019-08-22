package com.murphy1.myrecipes.controllers;

import com.murphy1.myrecipes.model.Recipe;
import com.murphy1.myrecipes.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    void showRecipes() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        when(recipeService.findRecipeById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipes/1/show"))
                .andExpect(status().isOk());
    }

    @Test
    void newRecipe() throws Exception{
        Recipe recipe = new Recipe();

        mockMvc.perform(get("/recipes/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void saveOrUpdate() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(2L);

        when(recipeService.saveRecipe(any())).thenReturn(recipe);

        mockMvc.perform(post("/recipe"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/2/show"));
    }
}