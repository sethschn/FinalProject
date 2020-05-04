package com.skilldistillery.outdistancing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Category;
import com.skilldistillery.outdistancing.entities.User;
import com.skilldistillery.outdistancing.entities.UserGroup;
import com.skilldistillery.outdistancing.repositories.UserGroupRepository;
import com.skilldistillery.outdistancing.repositories.UserRepository;

@Service
public class UserGroupServiceImpl implements UserGroupService {
	
	@Autowired
	private UserGroupRepository usergroupRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<UserGroup> listAllGroups() {
		return usergroupRepo.findAll();
	}

	@Override
	public UserGroup findById(int userGroupId) {
		Optional<UserGroup> optUserGroup = usergroupRepo.findById(userGroupId);
		UserGroup userGroup = null;
		if (optUserGroup .isPresent()) {
			userGroup = optUserGroup .get();
		} else {
			return null;
		}
		return userGroup;
	}

	@Override
	public UserGroup createGroup(UserGroup userGroup, String username) {
		User currentUser = userRepo.findByUsername(username);
		if (userGroup != null && currentUser != null) {
			userGroup.setCreator(currentUser);
			try {
				return usergroupRepo.saveAndFlush(userGroup);							
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public UserGroup updateGroup(int userGroupId, UserGroup userGroup, String username) {
		User currentUser = userRepo.findByUsername(username);
		Optional<UserGroup> optUserGroup = usergroupRepo.findById(userGroupId);
        if (optUserGroup.isPresent() && currentUser != null) {
            UserGroup updateUserGroup = optUserGroup.get();
            //updateUserGroup.setCreator(currentUser);
            updateUserGroup.setDescription(userGroup.getDescription());
            updateUserGroup.setImageUrl(userGroup.getImageUrl());
            updateUserGroup.setName(userGroup.getName());
            updateUserGroup.setShortDescription(userGroup.getShortDescription());
            updateUserGroup.setUsers(userGroup.getUsers());
            return usergroupRepo.saveAndFlush(updateUserGroup);
        }
        return null;
	}

	@Override
	public Boolean deleteGroup(int userGroupId, String username) {
		User user = userRepo.findByUsername(username);
        Optional<UserGroup> optUserGroup = usergroupRepo.findById(userGroupId);
        if (optUserGroup.isPresent() && user != null) {
			UserGroup userGroup = optUserGroup.get();
			if (userGroup != null) {
				usergroupRepo.deleteById(userGroupId);
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean changeGroupEnabled(int userGroupId, String username) {
		User user = userRepo.findByUsername(username);
		Optional<UserGroup> optUserGroup = usergroupRepo.findById(userGroupId);
        if (optUserGroup.isPresent() && user != null) {
            UserGroup updateUserGroup = optUserGroup.get();
            updateUserGroup.setEnabled(!updateUserGroup.isEnabled());
            usergroupRepo.save(updateUserGroup);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UserGroup addUserGroup(int userGroupId, String username) {
		User user = userRepo.findByUsername(username);
		Optional<UserGroup> optUserGroup = usergroupRepo.findById(userGroupId);
        if (optUserGroup.isPresent() && user != null) {
        	UserGroup updateUserGroup = optUserGroup.get();
        	List<User> groupUsers = updateUserGroup.getUsers();
        	groupUsers.add(user);
        	updateUserGroup.setUsers(groupUsers);
        	return usergroupRepo.saveAndFlush(updateUserGroup);
        }
		return null;
	}

}
