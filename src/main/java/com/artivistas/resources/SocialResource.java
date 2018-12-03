package com.artivistas.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artivistas.event.ResourceCreatedEvent;
import com.artivistas.model.Social;
import com.artivistas.repository.SocialRepository;
import com.artivistas.service.SocialService;

@RestController
@RequestMapping("/socials")
public class SocialResource {
	
	@Autowired
	private SocialRepository socialRepository;
	
	@Autowired
	private SocialService socialService;
	
	@Autowired
	public ApplicationEventPublisher publisher;

	@GetMapping("/profile-group/{id}")
	public ResponseEntity<?> findByProfileGroupId(@PathVariable Long id){
		List<Social> socials = socialService.findByProfileGroup(id);
		
		return !socials.isEmpty() ? ResponseEntity.ok(socials) : ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Social> save(@RequestBody @Valid Social social, HttpServletResponse response){
		Social socialReturned = socialRepository.save(social);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, socialReturned.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(socialReturned);
	}
	
	
	
}
