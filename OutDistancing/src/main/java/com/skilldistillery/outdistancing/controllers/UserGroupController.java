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
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.outdistancing.entities.User;
import com.skilldistillery.outdistancing.entities.UserGroup;
import com.skilldistillery.outdistancing.services.UserGroupService;
import com.skilldistillery.outdistancing.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class UserGroupController {
	
	@Autowired
	private UserGroupService usergroupSvc;
	
	@Autowired
	private UserService userSvc;
	
	@GetMapping("usergroups")
	public List<UserGroup> index(){
		return usergroupSvc.listAllGroups();
	}
	
	@GetMapping("usergroups/{userGroupId}")
	public UserGroup showById(@PathVariable("userGroupId") Integer id, HttpServletResponse response) {
		UserGroup userGroup = usergroupSvc.findById(id);
		if (userGroup == null) {
			response.setStatus(404);
		}
		return userGroup;
	}
	
	@PostMapping("usergroups")
	public UserGroup create(@RequestBody UserGroup userGroup, HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
		UserGroup createdUserGroup = usergroupSvc.createGroup(userGroup, principal.getName());
			res.setStatus(201);
			StringBuffer reqUrl = req.getRequestURL();
			reqUrl.append("/").append(createdUserGroup.getId());
			res.setHeader("Location", reqUrl.toString());
			return createdUserGroup;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("usergroups/{userGroupId}")
	public UserGroup updateUserGroup(@PathVariable("userGroupId") int userGroupId, @RequestBody UserGroup userGroup,
			HttpServletResponse resp, Principal principal) {
		try {
			userGroup = usergroupSvc.updateGroup(userGroupId, userGroup, principal.getName());
			if (userGroup == null) {
				resp.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			userGroup = null;
		}
		return userGroup;
	}
	
	@PostMapping("usergroups/{userGroupId}/joingroup")
	public UserGroup addUserToGroup(@PathVariable("userGroupId") int userGroupId,
			HttpServletResponse resp, Principal principal) {
			UserGroup userGroup = null;
		try {
			userGroup = usergroupSvc.addUserGroup(userGroupId, principal.getName());
			if (userGroup == null) {
				resp.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return userGroup;
		}
		return userGroup;
	}
	

	@DeleteMapping("usergroups/{userGroupId}")
	public boolean changeEnabled(HttpServletRequest req, HttpServletResponse res, @PathVariable int userGroupId, Principal principal) {
		boolean isEnabled = usergroupSvc.changeGroupEnabled(userGroupId, principal.getName());
		if (isEnabled) {
			res.setStatus(200);
		}else {
			res.setStatus(404);
		}
		return isEnabled;
	}

}
