package com.skilldistillery.outdistancing.services;

import java.util.List;

import com.skilldistillery.outdistancing.entities.Category;


public interface CategoryService {
	List<Category> listAllCategories();
	Category findById(int categoryId);
	Category createCategory(Category category, String username);
	Category updateCategory (int categoryId, Category category, String username);
	Boolean deleteCategory(int categoryId, String username);
	Boolean changeCategoryEnabled(int categoryId, String username);
}
