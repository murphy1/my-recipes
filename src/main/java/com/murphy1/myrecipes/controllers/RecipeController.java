package com.murphy1.myrecipes.controllers;

import com.murphy1.myrecipes.services.RecipeService;
import org.springframework.stereotype.Controller;

@Controller
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public String showRecipes(){
        return null;
    }
}
