package com.artivistas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artivistas.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
