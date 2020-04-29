package com.skilldistillery.outdistancing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Resource;
import com.skilldistillery.outdistancing.repositories.ResourceRepository;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceRepository resourceRepo;

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
	public Resource createResource(Resource resource) {
		try {
			resource = resourceRepo.saveAndFlush(resource);
			return resource;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Resource updateResource(int resourceId, Resource resource) {
		Optional<Resource> optResource = resourceRepo.findById(resourceId);
		if (optResource.isPresent()) {
			Resource managedResource = optResource.get();
			managedResource.setName(resource.getName());
			managedResource.setDescription(resource.getDescription());
			managedResource.setLink(resource.getLink());
			managedResource.setImageUrl(resource.getImageUrl());
			return resourceRepo.saveAndFlush(managedResource);
		}
		return null;
	}




	public boolean deleteById(int resourceId) {
		Optional<Resource> optHike = resourceRepo.findById(resourceId);
		if (optHike.isPresent()) {
			resourceRepo.deleteById(resourceId);
			return true;
		}
		return false;
	}
	
	

//	@Override
//	public Boolean changeResourceEnabled(String name) {
//		Resource resource = null;
//		resource = resourceRepo.findByName(name);
//		if (resource != null) {
//			resource.setEnabled(!resource.getEnabled());
//			resourceRepo.save(resource);
//			return true;
//		} else {
//			return false;
//
//		}

	}

	
	
	
	



