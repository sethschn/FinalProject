package com.skilldistillery.outdistancing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Location;
import com.skilldistillery.outdistancing.entities.Resource;
import com.skilldistillery.outdistancing.entities.User;
import com.skilldistillery.outdistancing.repositories.LocationRepository;
import com.skilldistillery.outdistancing.repositories.UserRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Location> listAllLocations() {
		return locationRepo.findAll();
	}

	@Override
	public Location findById(int locationId) {
		Optional<Location> optLocation = locationRepo.findById(locationId);
		Location location = null;
		if (optLocation.isPresent()) {
			location = optLocation.get();
		} else {
			return null;
		}
		return location;
	}

	@Override
	public Location createLocation(Location location, String username) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
		try {
			location = locationRepo.saveAndFlush(location);
			return location;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		}
		return null;
	}
	
	@Override
	public Location updateLocation(int locationId, Location location, String username) {
		User user = userRepo.findByUsername(username);
		Optional<Location> optLocation = locationRepo.findById(locationId);
		if (optLocation.isPresent() && user!=null) {
			Location managedLocation = optLocation.get();
			managedLocation.setStreet(location.getStreet());
			managedLocation.setCity(location.getCity());
			managedLocation.setState(location.getState());;
			managedLocation.setCountry(location.getCountry());
			managedLocation.setPostalCode(location.getPostalCode());
			managedLocation.setTitle(location.getTitle());;
			managedLocation.setLocationUrl(location.getLocationUrl());
			return locationRepo.saveAndFlush(managedLocation);
		}
		return null;
	}

	@Override
	public boolean deleteById(int locationId, String username) {
		User user = userRepo.findByUsername(username);
		Optional<Location> optLocation = locationRepo.findById(locationId);
		if (optLocation.isPresent() && user!=null) {
			locationRepo.deleteById(locationId);
			return true;
		}
		return false;
	}




	
	
}

	
	
	
	



