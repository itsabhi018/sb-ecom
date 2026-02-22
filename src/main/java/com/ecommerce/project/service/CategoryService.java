package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import org.springframework.web.bind.annotation.RequestBody;


public interface CategoryService {

     CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize);

     CategoryDTO createCategory(CategoryDTO categoryDTO);

     CategoryDTO deleteCategory(Long categoryId);

     CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
