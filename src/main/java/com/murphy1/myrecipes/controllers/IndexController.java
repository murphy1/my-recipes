package com.murphy1.myrecipes.controllers;

import com.murphy1.myrecipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String getIndex(Model model){

        model.addAttribute("recipes", recipeService.getRecipes());

        log.info("Recipes loaded to index page!");

        return "index";
    }

}
