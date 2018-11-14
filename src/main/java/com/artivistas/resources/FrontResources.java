package com.artivistas.resources;

import java.awt.Font;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
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
import com.artivistas.model.Front;
import com.artivistas.repository.FrontRepository;
import com.artivistas.service.FrontService;

@RestController
@RequestMapping("/fronts")
public class FrontResources {
	
	@Autowired
	private FrontRepository frontRepository;
	
	@Autowired
	private FrontService frontService;
	
	@Autowired
	private ApplicationEventPublisher publish;
	
	@GetMapping("/profile-group/{id}")
	public ResponseEntity<?> findfrontByProfileGroup(@PathVariable Long id){
		List<Front> frontsReturned = frontService.findByProfileGroup(id);
		
		return !frontsReturned.isEmpty() ? ResponseEntity.ok(frontsReturned) : ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Front> frontById(@PathVariable Long id){
		
		Front frontReturned = frontRepository.findById(id).orElse(null);
		
		return frontReturned != null ? ResponseEntity.ok(frontReturned) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Front> save(@RequestBody @Valid Front front, HttpServletResponse response){
		
		Front frontReturned = frontRepository.save(front);
		publish.publishEvent(new ResourceCreatedEvent(this, response, frontReturned.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(frontReturned);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Front> update(@PathVariable Long id, @RequestBody Front front){
		
		Front FrontReturned = frontService.update(id, front);
		
		return ResponseEntity.ok(FrontReturned);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		frontRepository.deleteById(id);
	}

}
