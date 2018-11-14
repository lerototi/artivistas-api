package com.artivistas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artivistas.model.Front;

public interface FrontRepository extends JpaRepository<Front, Long> {

	List<Front> findByProfileGroup(Object profileGroup);
	
}
