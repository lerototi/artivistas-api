package com.artivistas.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artivistas.event.ResourceCreatedEvent;
import com.artivistas.model.ProfileGroup;
import com.artivistas.model.Social;
import com.artivistas.repository.SocialRepository;
import com.artivistas.service.SocialService;

@RestController
@RequestMapping("/socials")
public class SocialResources {

	@Autowired
	private SocialRepository socialRepository;
	
	@Autowired
	private SocialService socialService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@PostMapping
	public ResponseEntity<Social> save (@RequestBody Social social, HttpServletResponse response){
		
		Social socialReturned = socialRepository.save(social);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, socialReturned.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(socialReturned);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Social> update (@RequestBody Social social, @PathVariable Long id){
		
		Social SocialReturned = socialService.update(id, social);
		
		return ResponseEntity.ok(SocialReturned);
	}
	
	@GetMapping("/{idProfileGroup}")
	public ResponseEntity<?> socialsProfileGroup(@PathVariable Long idProfileGroup){
		
		ProfileGroup profileGroup = new ProfileGroup();
		profileGroup.setId(idProfileGroup);
		
		List<Social> socialsReturned = socialRepository.findByProfileGroup(profileGroup);
		return !socialsReturned.isEmpty() ? ResponseEntity.ok(socialsReturned) : ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		socialRepository.deleteById(id);
	}
	
}
