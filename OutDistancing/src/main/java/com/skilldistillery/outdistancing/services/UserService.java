package com.skilldistillery.outdistancing.services;

import java.util.List;

import com.skilldistillery.outdistancing.entities.User;

public interface UserService {
	List<User> listAllUsers();
	User findById(int userId, String username);
//	User createUser(User user);
	User updateUser (int userId, User user, String username);
	Boolean changeUserEnabled(int UserId, String username);
	User findUserByUsername(String username);
	User addUserEvent(int eventId, String username);
}
