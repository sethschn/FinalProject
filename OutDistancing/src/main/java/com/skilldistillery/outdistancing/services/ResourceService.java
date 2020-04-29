package com.skilldistillery.outdistancing.services;

import java.util.List;

import com.skilldistillery.outdistancing.entities.Resource;

public interface ResourceService {
	List<Resource> listAllResources();
	Resource findById(int resourceId);
	Resource updateResource (int resourceId, Resource resource, String username);
	boolean deleteById(int resourceId, String username);
//	Boolean changeResourceEnabled(String name);
	Resource createResource(Resource resource, String username);
}
