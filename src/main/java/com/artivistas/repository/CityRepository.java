package com.artivistas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artivistas.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
