package com.artivistas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artivistas.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	List<Member> findByProfileGroup (Object profileGroup);
	
}
