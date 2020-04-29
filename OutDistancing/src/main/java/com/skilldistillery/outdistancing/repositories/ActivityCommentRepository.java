package com.skilldistillery.outdistancing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.outdistancing.entities.ActivityComment;


public interface ActivityCommentRepository extends JpaRepository<ActivityComment, Integer>{

}
