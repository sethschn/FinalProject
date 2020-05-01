package com.skilldistillery.outdistancing.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.outdistancing.entities.Location;
import com.skilldistillery.outdistancing.entities.Location;
import com.skilldistillery.outdistancing.services.LocationService;
import com.skilldistillery.outdistancing.services.ResourceService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class LocationController {

	@Autowired
	private LocationService locationSvc;

	@GetMapping("locations")
	public List<Location> listLocations() {
		return locationSvc.listAllLocations();
	}

	@GetMapping("locations/{locationId}")
	public Location showById(@PathVariable("locationId") int id, HttpServletResponse response) {
		Location location = locationSvc.findById(id);
		if (location == null) {
			response.setStatus(404);
		}
		return location;
	}
	
	@PostMapping("locations")
	@ResponseBody
	public Location createNewLocation(@RequestBody Location resource, HttpServletRequest request,
			HttpServletResponse response, Principal principal) {
		try {
			Location addLocation = locationSvc.createLocation(resource, "");
			response.setStatus(201);
			StringBuffer url = request.getRequestURL();
			url.append("/").append(resource.getId());
			String location = url.toString();
			response.addHeader("Location", location);
			return addLocation;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			return null;
		}
	}
	
	@PutMapping("locations/{locationId}")
	public Location updateLocationEntry(@PathVariable("locationId") int locationId, @RequestBody Location location,
			HttpServletResponse resp, Principal principal) {
		try {
			location = locationSvc.updateLocation(locationId, location, principal.getName());
			if (location == null) {
				resp.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			location = null;
		}
		return location;
	}
	
	@DeleteMapping("locations/{locationId}")
	public void deleteResourceEntry(@PathVariable("locationId") int locationId, HttpServletResponse response, Principal principal) {
		try {
			if (locationSvc.deleteById(locationId, principal.getName())) {
				response.setStatus(204);

			} else {
				response.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(409);
		}
	}

}
