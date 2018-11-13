package com.artivistas.resources;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artivistas.event.ResourceCreatedEvent;
import com.artivistas.model.ProfileGroup;
import com.artivistas.repository.ProfileGroupRepository;
import com.artivistas.service.ProfileGroupService;

@RestController
@RequestMapping("profile-groups")
public class ProfileGroupResources {
	
	@Autowired
	private ProfileGroupRepository profileGroupRepository;
	
	@Autowired
	private ProfileGroupService profileGroupService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public ResponseEntity<?> listAll(){
		List<ProfileGroup> profileGroupsReturned = profileGroupRepository.findAll();
		
		return !profileGroupsReturned.isEmpty() ? ResponseEntity.ok(profileGroupsReturned) : ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<ProfileGroup> save(@RequestBody @Valid ProfileGroup profileGroup, HttpServletResponse response){
		
		ProfileGroup profileGroupReturned = profileGroupRepository.save(profileGroup);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, profileGroup.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(profileGroupReturned);
	}
	
	@PutMapping({"/id"})
	public ResponseEntity<ProfileGroup> update(@PathVariable Long id, @RequestBody @Valid ProfileGroup profileGroup){
		
		ProfileGroup profileGroupReturned = profileGroupService.update(id, profileGroup);
		
		return ResponseEntity.ok(profileGroupReturned);
	}
	
	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyActive(@PathVariable Long id, Boolean active, HttpServletResponse response) {
		
		profileGroupService.updatePropertyActive(id, active);
		
	}
	
}
