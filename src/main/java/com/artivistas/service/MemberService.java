package com.artivistas.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artivistas.model.Member;
import com.artivistas.model.ProfileGroup;
import com.artivistas.repository.MemberRepository;

@Service
public class MemberService {

	
	@Autowired
	private MemberRepository memberRepository;

	public List<Member> findByprofileGroup(Long id) {
	
		ProfileGroup profileGroup = new ProfileGroup();
		profileGroup.setId(id);
		
		
		return memberRepository.findByProfileGroup(profileGroup);
	}

	public Member update(Long id, @Valid Member member) {
		Member memberSaved = memberRepository.getOne(id);
		BeanUtils.copyProperties(member, memberSaved, "id");
		
		return memberRepository.save(memberSaved);
	}
}
