package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    //private List<Category> categories= new ArrayList<>();
    //private Long nextId = 1L;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory!=null){
            throw new APIException("Category with the name " + category.getCategoryName() + " already exists!!");
        }
        //category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryId));

        categoryRepository.delete(category);
        return "Category with categoryId : " + categoryId + " deleted successfully";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryId));

        existingCategory.setCategoryName((category.getCategoryName()));
        categoryRepository.save(existingCategory);
        return existingCategory;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new ResourceNotFoundException("No Category Created till now!!");
        }
        return categories;
    }
}
