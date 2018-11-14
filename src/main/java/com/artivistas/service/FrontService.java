package com.artivistas.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.artivistas.model.Front;
import com.artivistas.model.ProfileGroup;
import com.artivistas.repository.FrontRepository;

@Service
public class FrontService {

	
	@Autowired
	private FrontRepository frontRepository;

	public List<Front> findByProfileGroup(Long id) {
		ProfileGroup profileGroup = new ProfileGroup();
		profileGroup.setId(id);

		return frontRepository.findByProfileGroup(profileGroup);
	}

	public Front update(Long id, Front front) {
		Front frontReturned = findFrontById(id);
		BeanUtils.copyProperties(front, frontReturned, "id");
		
		return frontRepository.save(frontReturned);
	}

	private Front findFrontById(Long id) {
		Front frontReturned =frontRepository.getOne(id);
		if (frontReturned==null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return frontReturned;
	}
	
}
