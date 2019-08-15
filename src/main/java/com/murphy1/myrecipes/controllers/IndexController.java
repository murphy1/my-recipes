package com.murphy1.myrecipes.controllers;

import com.murphy1.myrecipes.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String getIndex(Model model){

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }

}
