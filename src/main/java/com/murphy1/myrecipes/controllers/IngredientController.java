package com.murphy1.myrecipes.controllers;

import com.murphy1.myrecipes.model.Ingredient;
import com.murphy1.myrecipes.model.Recipe;
import com.murphy1.myrecipes.model.UnitOfMeasure;
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

        model.addAttribute("ingredient", ingredientService.getIngredientById(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        model.addAttribute("uomList", uomService.uomList());

        return "/recipes/ingredient/ingredientform";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveIngredient(Ingredient ingredient){
        Ingredient savedIngredient = ingredientService.saveIngredient(ingredient);

        return "redirect:/recipes/"+savedIngredient.getRecipe().getId()+"/ingredient/"+savedIngredient.getId()+"/show";
    }

    @GetMapping
    @RequestMapping("/recipes/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId){
        ingredientService.deleteIngredient(new Long(ingredientId));

        return "redirect:/";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model){
        Recipe recipe = recipeService.findRecipeById(new Long(recipeId));

        Ingredient ingredient = new Ingredient();
        ingredient.setRecipe(recipe);

        model.addAttribute("ingredient", ingredient);

        ingredient.setUnitOfMeasure(new UnitOfMeasure());

        model.addAttribute("uomList", uomService.uomList());

        return "recipes/ingredient/ingredientform";
    }

}
