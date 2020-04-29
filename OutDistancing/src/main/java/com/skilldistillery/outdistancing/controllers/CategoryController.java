package com.skilldistillery.outdistancing.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.outdistancing.entities.Category;
import com.skilldistillery.outdistancing.entities.Event;
import com.skilldistillery.outdistancing.entities.Resource;
import com.skilldistillery.outdistancing.services.CategoryService;
import com.skilldistillery.outdistancing.services.EventService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class CategoryController {
	
	@Autowired
	private CategoryService categorySvc;
	
	@GetMapping("categories")
	public List<Category> index(){
		return categorySvc.listAllCategories();
	}
	
	@GetMapping("categories/{categoryId}")
	public Category showById(@PathVariable("categoryId") Integer id, HttpServletResponse response) {
		Category category = categorySvc.findById(id);
		if (category == null) {
			response.setStatus(404);
		}
		return category;
	}
	
	@PostMapping("categories")
	public Category create(@RequestBody Category category, HttpServletRequest req, HttpServletResponse res) {
		Category createdCategory = categorySvc.createCategory(category);
		if (createdCategory != null) {
			res.setStatus(201);
			StringBuffer reqUrl = req.getRequestURL();
			reqUrl.append("/").append(createdCategory.getId());
			res.setHeader("Location", reqUrl.toString());
		} else {
			res.setStatus(404);
		}
		return createdCategory;
	}
	
	@PutMapping("categories/{categoryId}")
	public Category updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category,
			HttpServletResponse resp) {
		try {
			category = categorySvc.updateCategory(categoryId, category);
			if (category == null) {
				resp.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			category = null;
		}
		return category;
	}
	
//  DELETE todos/{tid}
	@DeleteMapping("categories/{categoryId}")
	public boolean destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int categoryId) {
		boolean isEnabled = categorySvc.changeCategoryEnabled(categoryId);
		if (isEnabled) {
			res.setStatus(200);
		}else {
			res.setStatus(404);
		}
		return isEnabled;
	}

}