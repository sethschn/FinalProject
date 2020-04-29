package com.skilldistillery.outdistancing.services;

import java.util.List;

import com.skilldistillery.outdistancing.entities.User;

public interface UserService {
	List<User> listAllUsers();
	User findById(int userId);
//	User createUser(User user);
	User updateUser (int userId, User user);
	Boolean changeUserEnabled(String username);
}
