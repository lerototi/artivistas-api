package com.artivistas.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.artivistas.model.Social;
import com.artivistas.repository.SocialRepository;

@Service
public class SocialService {
		
		@Autowired
		private SocialRepository repository;

	public Social update(Long id, Social social) {
		
		Social socialReturned = findById(id);
		BeanUtils.copyProperties(social, socialReturned, "id");
		
		return repository.save(socialReturned);
	}

	public Social findById(Long id) {
		
		Social socialReturned = repository.findById(id).orElse(null);
		if(socialReturned == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return socialReturned;
	}
	
}
