package com.artivistas.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="profile_group")
public class ProfileGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotNull
	@Column(length=60)
	private String name;
	
	@Column(length=500)
	private String description;
	
	@NotNull
	private LocalDate founded;
	
	@NotNull
	private LocalDate registered;
	
	@NotNull
	private boolean active;
	
	@OneToOne
	@JoinColumn(name = "fk_city")
	private City city;
	
	@OneToMany(mappedBy="profileGroup", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Member> members;
	
	@OneToMany(mappedBy="profileGroup", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Front> fronts;
	
	@OneToMany(mappedBy="profileGroup", cascade =CascadeType.ALL)
	@JsonBackReference
	private List<Social> socials;
	
	@ManyToOne
	@JoinColumn(name="fk_user")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public List<Front> getFronts() {
		return fronts;
	}

	public void setFronts(List<Front> fronts) {
		this.fronts = fronts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getFounded() {
		return founded;
	}

	public void setFounded(LocalDate founded) {
		this.founded = founded;
	}


	public LocalDate getRegistered() {
		return registered;
	}

	public void setRegistered(LocalDate registered) {
		this.registered = registered;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}


	public List<Social> getSocials() {
		return socials;
	}

	public void setSocials(List<Social> socials) {
		this.socials = socials;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfileGroup other = (ProfileGroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
