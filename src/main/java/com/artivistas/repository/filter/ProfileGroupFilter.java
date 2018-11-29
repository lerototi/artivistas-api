package com.artivistas.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ProfileGroupFilter {

	private String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate foundedFrom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate foundedUntil;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate registeredFrom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate registeredUntil;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getFoundedFrom() {
		return foundedFrom;
	}
	public void setFoundedFrom(LocalDate foundedFrom) {
		this.foundedFrom = foundedFrom;
	}
	public LocalDate getFoundedUntil() {
		return foundedUntil;
	}
	public void setFoundedUntil(LocalDate foundedUntil) {
		this.foundedUntil = foundedUntil;
	}
	public LocalDate getRegisteredFrom() {
		return registeredFrom;
	}
	public void setRegisteredFrom(LocalDate registeredFrom) {
		this.registeredFrom = registeredFrom;
	}
	public LocalDate getRegisteredUntil() {
		return registeredUntil;
	}
	public void setRegisteredUntil(LocalDate registeredUntil) {
		this.registeredUntil = registeredUntil;
	}
	
	
	
}
