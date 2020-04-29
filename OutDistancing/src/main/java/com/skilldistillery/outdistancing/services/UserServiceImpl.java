package com.skilldistillery.outdistancing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Location;
import com.skilldistillery.outdistancing.entities.User;
import com.skilldistillery.outdistancing.repositories.LocationRepository;
import com.skilldistillery.outdistancing.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private LocationRepository locRepo;

	@Override
	public List<User> listAllUsers() {
		return userRepo.findAll();
	}



	@Override
	public User findById(int userId) {
		Optional<User> optUser = userRepo.findById(userId);
		User user = null;
		if (optUser.isPresent()) {
			user = optUser.get();
		} else {
			return null;
		}
		return user;
	}


	@Override
	public User createUser(User user) {
		Location newLocation = user.getLocation();
		locRepo.saveAndFlush(newLocation);
		try {
			user = userRepo.saveAndFlush(user);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public User updateUser(int userId, User user) {
		Optional<User> optUser = userRepo.findById(userId);
		if (optUser.isPresent()) {
			User managedUser = optUser.get();
			managedUser.setUsername(user.getUsername());
			managedUser.setPassword(user.getPassword());
			managedUser.setEmail(user.getEmail());
			managedUser.setFirstName(user.getFirstName());
			managedUser.setLastName(user.getLastName());
			managedUser.setDescription(user.getDescription());
			managedUser.setImageUrl(user.getImageUrl());
			managedUser.setEnabled(user.isEnabled());
			managedUser.setRole(user.getRole());
			return userRepo.saveAndFlush(managedUser);
		}
		return null;
	}
	
	@Override
	public Boolean changeUserEnabled(String username) {
		User user = null;
		user = userRepo.findByUsername(username);
		if (user != null) {
			user.setEnabled(!user.isEnabled());
			userRepo.save(user);
			return true;
		} else {
			return false;

		}
	}
	

}
