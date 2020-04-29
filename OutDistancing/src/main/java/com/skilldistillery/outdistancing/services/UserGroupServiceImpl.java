package com.skilldistillery.outdistancing.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.outdistancing.entities.Category;
import com.skilldistillery.outdistancing.entities.UserGroup;
import com.skilldistillery.outdistancing.repositories.UserGroupRepository;

@Service
public class UserGroupServiceImpl implements UserGroupService {
	
	@Autowired
	private UserGroupRepository usergroupRepo;

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
	public UserGroup createGroup(UserGroup userGroup) {
		if (userGroup != null) {
			return usergroupRepo.saveAndFlush(userGroup);			
		}else {
			return null;
		}
	}

	@Override
	public UserGroup updateGroup(int userGroupId, UserGroup userGroup) {
        Optional<UserGroup> optUserGroup = usergroupRepo.findById(userGroupId);
        if (optUserGroup.isPresent()) {
            UserGroup updateUserGroup = optUserGroup.get();
            updateUserGroup.setCreator(userGroup.getCreator());
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
	public Boolean deleteGroup(int userGroupId) {
        Optional<UserGroup> optUserGroup = usergroupRepo.findById(userGroupId);
        if (optUserGroup.isPresent()) {
			UserGroup userGroup = optUserGroup.get();
			if (userGroup != null) {
				usergroupRepo.deleteById(userGroupId);
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean changeGroupEnabled(int userGroupId) {
		Optional<UserGroup> optUserGroup = usergroupRepo.findById(userGroupId);
        if (optUserGroup.isPresent()) {
            UserGroup updateUserGroup = optUserGroup.get();
            updateUserGroup.setEnabled(!updateUserGroup.isEnabled());
            usergroupRepo.save(updateUserGroup);
			return true;
		} else {
			return false;
		}
	}

}
