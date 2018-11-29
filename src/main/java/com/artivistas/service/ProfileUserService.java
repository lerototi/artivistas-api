package com.artivistas.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.artivistas.model.ProfileUser;
import com.artivistas.repository.ProfileUserRepository;

@Service
public class ProfileUserService {
	
	@Autowired
	private ProfileUserRepository profileUserRepository;

	public ProfileUser updateService(Long id, ProfileUser profileUser) {
		ProfileUser profileUserReturned = findprofileUserById(id);
		BeanUtils.copyProperties(profileUser, profileUserReturned, "id");
		return profileUserRepository.save(profileUserReturned);
	}

	private ProfileUser findprofileUserById(Long id) {
		ProfileUser profileUserReturned = profileUserRepository.findById(id).orElse(null);
		if (profileUserReturned == null) {
			throw new EmptyResultDataAccessException(1);
		}else
			return profileUserReturned;
	}

}
