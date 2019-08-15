package com.murphy1.myrecipes.repositories;

import com.murphy1.myrecipes.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
