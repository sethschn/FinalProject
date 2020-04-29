package com.skilldistillery.outdistancing.services;

import java.util.List;

import com.skilldistillery.outdistancing.entities.UserGroup;



public interface UserGroupService {
	List<UserGroup> listAllGroups();
	UserGroup findById(int userGroupId);
	UserGroup createGroup(UserGroup userGroup, String username);
	UserGroup updateGroup (int userGroupId, UserGroup userGroup, String username);
	Boolean deleteGroup(int userGroupId);
	Boolean changeGroupEnabled(int userGroupId);
}
