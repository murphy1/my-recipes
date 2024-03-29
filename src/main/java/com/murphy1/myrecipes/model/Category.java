package com.murphy1.myrecipes.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories")
    private Set<Recipe> recipes = new HashSet<>();

}
