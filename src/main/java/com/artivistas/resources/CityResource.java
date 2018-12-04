package com.artivistas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.artivistas.model.City;
import com.artivistas.repository.CityRepository;

@RestController
@RequestMapping("/cities")
public class CityResource {
	
	@Autowired
	private CityRepository cityRepository;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		List<City> cities = cityRepository.findAll();
		
		return !cities.isEmpty() ? ResponseEntity.ok(cities) : ResponseEntity.noContent().build(); 
	}

}
