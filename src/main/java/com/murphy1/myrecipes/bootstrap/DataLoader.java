package com.murphy1.myrecipes.bootstrap;

import com.murphy1.myrecipes.model.*;
import com.murphy1.myrecipes.repositories.CategoryRepository;
import com.murphy1.myrecipes.repositories.RecipeRepository;
import com.murphy1.myrecipes.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public DataLoader(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){

        List<Recipe> recipes = new ArrayList<>();

        Optional<UnitOfMeasure> eachUnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUnitOfMeasureOptional.isPresent()){
            throw new RuntimeException("Each UOM not found!");
        }

        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaspoonOptional.isPresent()){
            throw new RuntimeException("Teaspoon UOM not found!");
        }

        Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tablespoonOptional.isPresent()){
            throw new RuntimeException("Tablespoon UOM not found!");
        }

        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupOptional.isPresent()){
            throw new RuntimeException("Cup UOM not found!");
        }

        Optional<UnitOfMeasure> pinchOptional = unitOfMeasureRepository.findByDescription("Pinch");

        if(!pinchOptional.isPresent()){
            throw new RuntimeException("Pinch UOM not found!");
        }

        Optional<UnitOfMeasure> ounceOptional = unitOfMeasureRepository.findByDescription("Ounce");

        if(!ounceOptional.isPresent()){
            throw new RuntimeException("Ounce UOM not found!");
        }

        Optional<UnitOfMeasure> dashOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashOptional.isPresent()){
            throw new RuntimeException("Dash UOM not found!");
        }

        UnitOfMeasure eachUom = eachUnitOfMeasureOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonOptional.get();
        UnitOfMeasure cupUom = cupOptional.get();
        UnitOfMeasure pinchUom = pinchOptional.get();
        UnitOfMeasure ounceUom = ounceOptional.get();
        UnitOfMeasure dashUom = dashOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacamole = new Recipe();
        guacamole.setDescription("Guacamole");
        guacamole.setCookTime(0);
        guacamole.setPrepTime(10);
        guacamole.setServings(2);
        guacamole.setSource("Simply Recipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        Notes guacNote = new Notes();
        guacNote.setRecipeNotes("2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)");
        guacNote.setRecipe(guacamole);
        guacamole.setNotes(guacNote);

        guacamole.getIngredients().add(new Ingredient("Avocado", 2.0, eachUom, guacamole));
        guacamole.getIngredients().add(new Ingredient("Serrano Chili", 3.0, eachUom, guacamole));
        guacamole.getIngredients().add(new Ingredient("Kosher Salt", 2.0, pinchUom, guacamole));

        guacamole.setDifficulty(Difficulty.EASY);

        guacamole.getCategories().add(americanCategory);
        guacamole.getCategories().add(mexicanCategory);

        recipes.add(guacamole);

        return recipes;

    }

}
