package com.skilldistillery.outdistancing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.skilldistillery.outdistancing.entities.User;
import com.skilldistillery.outdistancing.repositories.UserRepository;

public class AuthServiceImpl implements AuthService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository repo;
	
	@Override
	public User register(User user) {
		System.out.println(user.getPassword());
		String encrypted = encoder.encode(user.getPassword());
		user.setPassword(encrypted); // only persist encoded password
		System.out.println(user.getPassword());
		user.setEnabled(true);
		user.setRole("standard");
		repo.saveAndFlush(user);
		return user;
	}

}
