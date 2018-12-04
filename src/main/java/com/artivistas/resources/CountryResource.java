package com.artivistas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artivistas.model.Country;
import com.artivistas.repository.CountryRepository;

@RestController
@RequestMapping("/countries")
public class CountryResource {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		List<Country> countries = countryRepository.findAll();
		
		return !countries.isEmpty() ? ResponseEntity.ok(countries) : ResponseEntity.noContent().build(); 
	}

}
