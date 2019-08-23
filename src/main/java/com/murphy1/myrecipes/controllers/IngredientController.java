package com.murphy1.myrecipes.controllers;

import com.murphy1.myrecipes.services.IngredientService;
import com.murphy1.myrecipes.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("/recipes/{recipeId}/ingredient")
    public String showIngredients(@PathVariable String recipeId, Model model){
        model.addAttribute("recipe", recipeService.findRecipeById(new Long(recipeId)));

        return "recipes/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipes/{recipeId}/ingredient/{ingredientId}/show")
    public String getIngredientById(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.getIngredientById(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        return "recipes/ingredient/show";
    }

}
