package com.skilldistillery.outdistancing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Category;
import com.skilldistillery.outdistancing.entities.Resource;
import com.skilldistillery.outdistancing.entities.User;
import com.skilldistillery.outdistancing.repositories.CategoryRepository;
import com.skilldistillery.outdistancing.repositories.UserRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<Category> listAllCategories() {
		return categoryRepo.findAll();
	}

	@Override
	public Category findById(int categoryId) {
		Optional<Category> optCategory = categoryRepo.findById(categoryId);
		Category category = null;
		if (optCategory.isPresent()) {
			category = optCategory.get();
		} else {
			return null;
		}
		return category;
	}

	@Override
	public Category createCategory(Category category, String username) {
		User user = userRepo.findByUsername(username);
		if (category != null && user != null) {
			try {
				return categoryRepo.saveAndFlush(category);	
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}		
		}else {
			return null;
		}
	}

	@Override
	public Category updateCategory(int categoryId, Category category, String username) {
		User user = userRepo.findByUsername(username);
		Optional<Category> optCategory = categoryRepo.findById(categoryId);
        if (optCategory.isPresent() && user != null) {
            Category updateCategory = optCategory.get();
            updateCategory.setDescription(category.getDescription());
            updateCategory.setEnabled(category.isEnabled());
            updateCategory.setImageUrl(category.getImageUrl());
            updateCategory.setShortDescription(category.getShortDescription());
            updateCategory.setType(category.getType());
            return categoryRepo.saveAndFlush(updateCategory);
        }
        return null;
	}

	@Override
	public Boolean deleteCategory(int categoryId, String username) {
		User user = userRepo.findByUsername(username);
		Optional<Category> optCategory = categoryRepo.findById(categoryId);
		if (optCategory.isPresent() && user != null) {
			Category updateCategory = optCategory.get();
			if (updateCategory != null) {
				categoryRepo.deleteById(categoryId);
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public Boolean changeCategoryEnabled(int categoryId, String username) {
		User user = userRepo.findByUsername(username);
		Optional<Category> optCategory = categoryRepo.findById(categoryId);
        if (optCategory.isPresent() && user != null) {
            Category updateCategory = optCategory.get();
            updateCategory.setEnabled(!updateCategory.isEnabled());
			categoryRepo.save(updateCategory);
			return true;
		} else {
			return false;
		}
	}

}
