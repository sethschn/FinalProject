package com.skilldistillery.outdistancing.services;

import java.util.List;

import com.skilldistillery.outdistancing.entities.Resource;

public interface ResourceService {
	List<Resource> listAllResources();
	Resource findById(int resourceId);
	Resource createResource(Resource resource);
	Resource updateResource (int resourceId, Resource resource);
	Boolean deleteResource(int resourceId);
}
