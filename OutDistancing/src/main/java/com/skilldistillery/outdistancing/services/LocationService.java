package com.skilldistillery.outdistancing.services;

import java.util.List;

import com.skilldistillery.outdistancing.entities.Location;
import com.skilldistillery.outdistancing.entities.Resource;

public interface LocationService {
	List<Location> listAllLocations();
	Location findById(int locationId);
	Location updateLocation (int locationId, Location location, String username);
	boolean deleteById(int resourceId, String username);
//	Boolean changeResourceEnabled(String name);
	Location createLocation(Location location, String username);
}
