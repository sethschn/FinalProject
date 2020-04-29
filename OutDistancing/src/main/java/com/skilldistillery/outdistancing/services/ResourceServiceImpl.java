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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource updateResource(int resourceId, Resource resource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteResource(int resourceId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	


}
