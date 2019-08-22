package com.murphy1.myrecipes.controllers;

import com.murphy1.myrecipes.model.Recipe;
import com.murphy1.myrecipes.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipes/{id}/show")
    public String showRecipes(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findRecipeById(new Long(id)));
        return "recipes/show";
    }

    @RequestMapping("recipes/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new Recipe());
        return "recipes/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute Recipe recipe){
        Recipe savedRecipe = recipeService.saveRecipe(recipe);

        return "redirect:/recipes/"+savedRecipe.getId()+"/show";
    }

    @RequestMapping("/recipes/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findRecipeById(new Long(id)));
        return "recipes/recipeform";
    }
}
