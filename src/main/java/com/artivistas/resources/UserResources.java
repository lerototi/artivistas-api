package com.artivistas.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.artivistas.model.User;
import com.artivistas.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserResources {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public ResponseEntity<?> listAll(){
		List<User> users = userRepository.findAll();
		return !users.isEmpty() ? ResponseEntity.ok(users) : ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody @Valid User user, HttpServletResponse response){
		User userSaved = userRepository.save(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(userSaved.getIdUser()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(userSaved);
	}
	
	
}
