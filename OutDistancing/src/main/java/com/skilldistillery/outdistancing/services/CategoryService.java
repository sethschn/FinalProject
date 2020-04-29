package com.skilldistillery.outdistancing.services;

import java.util.List;

import com.skilldistillery.outdistancing.entities.Category;


public interface CategoryService {
	List<Category> listAllCategories();
	Category findById(int categoryId);
	Category createCategory(Category category);
	Category updateCategory (int categoryId, Category category);
	Boolean deleteCategory(int categoryId);
	Boolean changeCategoryEnabled(int categoryId);
}
