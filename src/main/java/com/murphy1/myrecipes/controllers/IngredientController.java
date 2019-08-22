package com.murphy1.myrecipes.controllers;

import com.murphy1.myrecipes.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {

    private final RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipes/{recipeId}/ingredient")
    public String showIngredients(@PathVariable String recipeId, Model model){
        model.addAttribute("recipe", recipeService.findRecipeById(new Long(recipeId)));

        return "recipes/ingredient/list";
    }

}
