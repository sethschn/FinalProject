package com.skilldistillery.outdistancing.controllers;

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

import com.skilldistillery.outdistancing.entities.Resource;
import com.skilldistillery.outdistancing.services.ResourceService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class ResourceController {

	@Autowired
	private ResourceService resourceSvc;

	@GetMapping("resources")
	public List<Resource> listResources() {
		return resourceSvc.listAllResources();
	}

	@GetMapping("resources/{resourceId}")
	public Resource showById(@PathVariable("resourceId") int id, HttpServletResponse response) {
		Resource resource = resourceSvc.findById(id);
		if (resource == null) {
			response.setStatus(404);
		}
		return resource;
	}
	
	@PostMapping("resources")
	@ResponseBody
	public Resource createNewResource(@RequestBody Resource resource, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Resource addResource = resourceSvc.createResource(resource);
			response.setStatus(201);
			StringBuffer url = request.getRequestURL();
			url.append("/").append(resource.getId());
			String location = url.toString();
			response.addHeader("Location", location);
			return addResource;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
			return null;
		}
	}
	
	@PutMapping("resources/{resourceId}")
	public Resource updateResourceEntry(@PathVariable("resourceId") int resourceId, @RequestBody Resource resource,
			HttpServletResponse resp) {
		try {
			resource = resourceSvc.updateResource(resourceId, resource);
			if (resource == null) {
				resp.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			resource = null;
		}
		return resource;
	}
	
	@DeleteMapping("resources/{resourceId}")
	public void deleteResourceEntry(@PathVariable("resourceId") int resourceId, HttpServletResponse response) {
		try {
			if (resourceSvc.deleteById(resourceId)) {
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
