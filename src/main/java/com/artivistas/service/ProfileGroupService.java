package com.artivistas.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.artivistas.model.ProfileGroup;
import com.artivistas.repository.ProfileGroupRepository;

@Service
public class ProfileGroupService {

	@Autowired
	private ProfileGroupRepository profileGroupRepository;
	
	public ProfileGroup update(Long id, ProfileGroup profileGroup) {
			
		ProfileGroup profileGroupReturned = findProfileGroupById(id);
		BeanUtils.copyProperties(profileGroup, profileGroupReturned, "id");
		
		return profileGroupRepository.save(profileGroupReturned);
	}

	public ProfileGroup findProfileGroupById(Long id) {
		ProfileGroup profileGroupReturned = profileGroupRepository.getOne(id);
		if (profileGroupReturned == null) {
			throw new EmptyResultDataAccessException(1);
		}else
		return profileGroupReturned;
	}

	public void updatePropertyActive(Long id, Boolean active) {
		
		ProfileGroup profileGroupReturned = profileGroupRepository.getOne(id);
		if (!profileGroupReturned.isActive()) {
			profileGroupReturned.setActive(true);
		}else 
		profileGroupReturned.setActive(false);
		
		profileGroupRepository.save(profileGroupReturned);
	}

	
}
