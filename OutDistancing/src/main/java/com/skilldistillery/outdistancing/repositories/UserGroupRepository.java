package com.skilldistillery.outdistancing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.outdistancing.entities.UserGroup;



public interface UserGroupRepository extends JpaRepository<UserGroup, Integer> {

	
}
