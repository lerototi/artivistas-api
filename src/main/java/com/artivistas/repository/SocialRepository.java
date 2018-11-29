package com.artivistas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artivistas.model.Social;

public interface SocialRepository extends JpaRepository<Social, Long> {

	List<Social> findByProfileGroup(Object profileGroup);
	
}
