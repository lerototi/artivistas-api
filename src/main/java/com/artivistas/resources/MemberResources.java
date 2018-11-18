package com.artivistas.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artivistas.event.ResourceCreatedEvent;
import com.artivistas.model.Member;
import com.artivistas.repository.MemberRepository;
import com.artivistas.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberResources {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/profile-group/{id}")
	public ResponseEntity<?> findByProfileGroup(@PathVariable Long id){
		List<Member> membersReturned = memberService.findByprofileGroup(id);
		
		return !membersReturned.isEmpty() ? ResponseEntity.ok(membersReturned) : ResponseEntity.notFound().build();
	}
	
	
	@GetMapping("/id")
	public ResponseEntity<Member> findById(@PathVariable Long id){
		
		Member memberReturned = memberRepository.getOne(id);
		
		return memberReturned != null ? ResponseEntity.ok(memberReturned) : ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	public ResponseEntity<Member> save(@RequestBody @Valid Member member, HttpServletResponse response){
		Member memberReturned = memberRepository.save(member);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, memberReturned.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(memberReturned);
	}
	
	@PutMapping("/id")
	public ResponseEntity<Member> update(@PathVariable Long id, @RequestBody @Valid Member member){
		
		Member memberReturned = memberService.update(id, member);
		
		return ResponseEntity.ok().body(memberReturned);
	}
	
	@DeleteMapping("/id")
	public void delete(@PathVariable Long id) {
		
		memberRepository.deleteById(id);
	}
	
}
