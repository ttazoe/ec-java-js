package com.tatazoe.ecjavajs.service;

import com.tatazoe.ecjavajs.model.Category;
import com.tatazoe.ecjavajs.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    // Get services
    public List<Category> listCategories(){
        return categoryRepository.findAll();
    }

    public Category readCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    // Null Safe
    public Optional<Category> readCategory(Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }

    // Post services
    public void createCategory(Category category){
        categoryRepository.save(category);
    }

    // Put services
    public void updateCategory(Integer categoryId, Category newCategory){
        Optional<Category> optionalCategory = readCategory(categoryId);
        Category category = optionalCategory.get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setImageUrl(newCategory.getImageUrl());
        categoryRepository.save(category);
    }



}
