package com.skilldistillery.outdistancing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.outdistancing.entities.Resource;
import com.skilldistillery.outdistancing.services.ResourceService;

@RestController
@RequestMapping ("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class ResourceController {
	
	@Autowired
	private ResourceService resourceSvc;
	
	
	 @GetMapping("resources")
		public List<Resource> listHikes() {
			return resourceSvc.listAllResources();
	 }

}
