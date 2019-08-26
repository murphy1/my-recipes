package com.murphy1.myrecipes.controllers;

import com.murphy1.myrecipes.model.Ingredient;
import com.murphy1.myrecipes.model.Recipe;
import com.murphy1.myrecipes.model.UnitOfMeasure;
import com.murphy1.myrecipes.services.IngredientService;
import com.murphy1.myrecipes.services.RecipeService;
import com.murphy1.myrecipes.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.*;

class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService uomService;

    IngredientController ingredientController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController(recipeService, ingredientService, uomService);
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

    @Test
    void getIngredientById() throws Exception{
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();

        when(ingredientService.getIngredientById(anyLong(), anyLong())).thenReturn(ingredient);

        mockMvc.perform(get("/recipes/1/ingredient/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    void updateIngredient() throws Exception{
        Ingredient ingredient = new Ingredient();
        List<UnitOfMeasure> list = new ArrayList<>();
        UnitOfMeasure uom = new UnitOfMeasure();
        list.add(uom);

        when(ingredientService.getIngredientById(anyLong(), anyLong())).thenReturn(ingredient);
        when(uomService.uomList()).thenReturn(list);

        mockMvc.perform(get("/recipes/1/ingredient/1/update"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("/recipes/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    void saveIngredientTest() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setRecipe(recipe);

        when(ingredientService.saveIngredient(any())).thenReturn(ingredient);

        mockMvc.perform(post("/recipe/1/ingredient"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/1/ingredient/1/show"));

    }

}
