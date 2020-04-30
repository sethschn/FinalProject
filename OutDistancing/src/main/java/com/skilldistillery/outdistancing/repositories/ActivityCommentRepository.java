package com.skilldistillery.outdistancing.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.outdistancing.entities.ActivityComment;


public interface ActivityCommentRepository extends JpaRepository<ActivityComment, Integer>{
	List<ActivityComment> findByActivity_Id(int id);

}
