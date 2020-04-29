package com.skilldistillery.outdistancing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Resource;
import com.skilldistillery.outdistancing.entities.User;
import com.skilldistillery.outdistancing.repositories.ResourceRepository;
import com.skilldistillery.outdistancing.repositories.UserRepository;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceRepository resourceRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Resource> listAllResources() {
		return resourceRepo.findAll();
	}

	@Override
	public Resource findById(int resourceId) {
		Optional<Resource> optResource = resourceRepo.findById(resourceId);
		Resource resource = null;
		if (optResource.isPresent()) {
			resource = optResource.get();
		} else {
			return null;
		}
		return resource;
	}

	@Override
	public Resource createResource(Resource resource, String username) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
		try {
			resource = resourceRepo.saveAndFlush(resource);
			return resource;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		}
		return null;
	}

	@Override
	public Resource updateResource(int resourceId, Resource resource, String username) {
		User user = userRepo.findByUsername(username);
		Optional<Resource> optResource = resourceRepo.findById(resourceId);
		if (optResource.isPresent() && user!=null) {
			Resource managedResource = optResource.get();
			managedResource.setName(resource.getName());
			managedResource.setDescription(resource.getDescription());
			managedResource.setLink(resource.getLink());
			managedResource.setImageUrl(resource.getImageUrl());
			return resourceRepo.saveAndFlush(managedResource);
		}
		return null;
	}




	public boolean deleteById(int resourceId, String username) {
		User user = userRepo.findByUsername(username);
		Optional<Resource> optHike = resourceRepo.findById(resourceId);
		if (optHike.isPresent() && user!=null) {
			resourceRepo.deleteById(resourceId);
			return true;
		}
		return false;
	}
	
}

	
	
	
	



