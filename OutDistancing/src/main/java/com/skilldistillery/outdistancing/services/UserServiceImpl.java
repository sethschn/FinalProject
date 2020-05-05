package com.skilldistillery.outdistancing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Category;
import com.skilldistillery.outdistancing.entities.Event;
import com.skilldistillery.outdistancing.entities.Location;
import com.skilldistillery.outdistancing.entities.User;
import com.skilldistillery.outdistancing.repositories.EventRepository;
import com.skilldistillery.outdistancing.repositories.LocationRepository;
import com.skilldistillery.outdistancing.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private LocationRepository locRepo;
	
	@Autowired
	private EventRepository eventRepo;

	@Override
	public List<User> listAllUsers() {
		
		return userRepo.findAll();
	}



	@Override
	public User findById(int userId, String username) {
		User user = userRepo.findByUsername(username);
		Optional<User> optUser = userRepo.findById(userId);
		
		if (optUser.isPresent() && user!= null) {
			user = optUser.get();
		} else {
			return null;
		}
		return user;
	}


//	@Override
//	public User createUser(User user) {
//		Location newLocation = user.getLocation();
//		locRepo.saveAndFlush(newLocation);
//		try {
//			user = userRepo.saveAndFlush(user);
//			return user;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}


	@Override
	public User updateUser(int userId, User user, String username) {
		User newUser = userRepo.findByUsername(username);
		Location updateLocation = user.getLocation();
		locRepo.saveAndFlush(updateLocation);
		
		Optional<User> optUser = userRepo.findById(userId);
		if (optUser.isPresent() && newUser != null) {
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
			managedUser.setLocation(updateLocation);
			return userRepo.saveAndFlush(managedUser);
		}
		return null;
	}
	
	@Override
	public Boolean changeUserEnabled(int userId, String username) {
		User user = userRepo.findByUsername(username);
		Optional<User> optUser = userRepo.findById(userId);
        if (optUser.isPresent() && user != null) {
            User updateUser = optUser.get();
            updateUser.setEnabled(!updateUser.isEnabled());
			userRepo.save(updateUser);
			return true;
		} else {
			return false;
		}
	}
	


	@Override
	public User findUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	@Override
	public User addUserEvent(int eventId, String username){
		User user = userRepo.findByUsername(username);
		Optional<Event> optEvent = eventRepo.findById(eventId);
		if (optEvent.isPresent() && user != null) {
			Event addEvent = optEvent.get();
			user.addEvent(addEvent);
			return userRepo.saveAndFlush(user);
		}
		return null;
	}
	
	@Override
	public User removeUserEvent(int eventId, String username) {
		User user = userRepo.findByUsername(username);
		Optional<Event> optEvent = eventRepo.findById(eventId);
		if (optEvent.isPresent() && user != null) {
			Event removeEvent = optEvent.get();
			user.removeEvent(removeEvent);
			return userRepo.saveAndFlush(user);
		}
		return null;
	}
	

}
