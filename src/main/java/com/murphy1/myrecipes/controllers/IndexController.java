package com.murphy1.myrecipes.controllers;

import com.murphy1.myrecipes.model.Category;
import com.murphy1.myrecipes.model.UnitOfMeasure;
import com.murphy1.myrecipes.repositories.CategoryRepository;
import com.murphy1.myrecipes.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String getIndex(){

        Optional<Category> category = categoryRepository.findByDescription("Irish");
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByDescription("Cup");

        System.out.println("Category id: "+category.get().getId());
        System.out.println("Unit Of Measure Id: "+uom.get().getId());

        return "index";
    }

}
