package com.artivistas.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.artivistas.model.User;
import com.artivistas.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User updateUser(Long id, User user) {
	
		User userReturned = findByUserId(id);
		BeanUtils.copyProperties(user, userReturned, "id");
		
		return userRepository.save(userReturned);
	}

	public void updateFiealdEnable(Long id, Boolean enable) {
		User userReturned = findByUserId(id);
		userReturned.setEnabled(enable);
		userRepository.save(userReturned);
	}
	
	private User findByUserId(Long id) {
		User userReturned = userRepository.findById(id).orElse(null);
		if (userReturned == null) {
			throw new EmptyResultDataAccessException(1);
		}else
			return userReturned;
	}
	
	
	
}
