package com.murphy1.myrecipes.controllers;

import com.murphy1.myrecipes.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipes/show/{id}")
    public String showRecipes(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findRecipeById(new Long(id)));
        return "recipes/show";
    }
}
