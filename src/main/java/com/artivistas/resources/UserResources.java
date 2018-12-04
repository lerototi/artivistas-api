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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artivistas.event.ResourceCreatedEvent;
import com.artivistas.model.User;
import com.artivistas.repository.UserRepository;
import com.artivistas.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResources {

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private ApplicationEventPublisher publisher; //disparar evento spring (publicador)
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		List<User> users = userRepository.findAll();
		return !users.isEmpty() ? ResponseEntity.ok(users) : ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody @Valid User user, HttpServletResponse response){
		User userSaved = userRepository.save(user);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, user.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User userReturned = userRepository.findOne(id);
		
		return userReturned != null ? ResponseEntity.ok(userReturned) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePerson(@PathVariable Long id) {
		userRepository.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
		
		User userSaved = userService.updateUser(id, user);
		
		return ResponseEntity.ok(userSaved);
	}
	
	@PutMapping("/{id}/enable")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateFieldEnable(@PathVariable Long id, @RequestBody Boolean enable) {
		
		userService.updateFiealdEnable(id, enable);
	}
	
	
	
	
	
}
