package com.skilldistillery.outdistancing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.outdistancing.entities.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
