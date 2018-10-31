package com.artivistas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artivistas.model.ProfileUser;
import com.artivistas.repository.ProfileUserRepository;
import com.artivistas.service.ProfileUserService;

@RestController
@RequestMapping("profile-users")
public class ProfileResources {

	
	@Autowired
	private ProfileUserRepository profileUserRepository;
	
	@Autowired
	private ProfileUserService profileUserService;
	
	@GetMapping
	private ResponseEntity<?> findProfiles(){
		List<ProfileUser> profilesReturned =  profileUserRepository.findAll();
		return !profilesReturned.isEmpty() ? ResponseEntity.ok(profilesReturned) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<ProfileUser> findById(@PathVariable Long id){
		ProfileUser profileUser = profileUserRepository.findById(id).orElse(null);
		
		return profileUser != null ? ResponseEntity.ok(profileUser) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<ProfileUser> updateProfileUser(@PathVariable Long id, @RequestBody ProfileUser profileUser){
		
		
		ProfileUser profileUserReturned = profileUserService.updateService(id, profileUser);
		
		return ResponseEntity.ok(profileUserReturned);
	}
}
