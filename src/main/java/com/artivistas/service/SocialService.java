package com.artivistas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artivistas.model.ProfileGroup;
import com.artivistas.model.Social;
import com.artivistas.repository.SocialRepository;

@Service
public class SocialService {

	@Autowired
	private SocialRepository socialRepository;

	public List<Social> findByProfileGroup(Long id) {

		ProfileGroup profileGroup = new ProfileGroup();
		profileGroup.setId(id);
		
		return socialRepository.findByProfileGroup(profileGroup);
	}

	
}
