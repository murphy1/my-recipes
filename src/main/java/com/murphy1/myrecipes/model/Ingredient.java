package com.murphy1.myrecipes.model;

import javax.persistence.*;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Recipe recipe;

    @OneToOne
    private UnitOfMeasure unitOfMeasure;

    private String description;
    private Double amount;

    public Ingredient(){

    }

    public Ingredient(String description, Double amount, UnitOfMeasure unitOfMeasure){
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

    public Ingredient(String description, Double amount, UnitOfMeasure unitOfMeasure, Recipe recipe){
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
