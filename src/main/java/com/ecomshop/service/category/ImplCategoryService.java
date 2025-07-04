package com.ecomshop.service.category;

import com.ecomshop.model.Category;

import java.util.List;

public interface ImplCategoryService {

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    List<Category> getAllCategories();

    Category addCategory(Category category);

    Category updateCategory(Category category, Long id);

    void deleteCategoryById(Long id);


}
