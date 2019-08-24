package com.murphy1.myrecipes.controllers;

import com.murphy1.myrecipes.model.Ingredient;
import com.murphy1.myrecipes.services.IngredientService;
import com.murphy1.myrecipes.services.RecipeService;
import com.murphy1.myrecipes.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService uomService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
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

    @GetMapping
    @RequestMapping("/recipes/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){

        model.addAttribute("ingredient", ingredientService.getIngredientById(new Long(recipeId), new Long(ingredientId)));

        model.addAttribute("uomList", uomService.uomList());

        return "/recipes/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping("ingredient")
    public String saveIngredient(@ModelAttribute Ingredient ingredient){
        //savedIngredient = ingredientService.sa

        return "/recipe/";
    }

}
