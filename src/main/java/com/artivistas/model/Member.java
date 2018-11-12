package com.artivistas.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="fk_profile_user")
	private ProfileUser profileUser;
	
	@ManyToOne
	@JoinColumn(name="fk_profile_group")
	private ProfileGroup profileGroup;
	
	@ManyToMany
	@JoinTable(name="front_has_members", joinColumns=
	{@JoinColumn(name="fk_member")}, inverseJoinColumns=
	{@JoinColumn(name="fk_front")})
	private List<Front> fronts;
	
	private LocalDate admission;
	
	private boolean memberActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProfileUser getProfileUser() {
		return profileUser;
	}

	public void setProfileUser(ProfileUser profileUser) {
		this.profileUser = profileUser;
	}

	public LocalDate getAdmission() {
		return admission;
	}

	public void setAdmission(LocalDate admission) {
		this.admission = admission;
	}

	public List<Front> getFronts() {
		return fronts;
	}

	public void setFronts(List<Front> fronts) {
		this.fronts = fronts;
	}

	public boolean isMemberActive() {
		return memberActive;
	}

	public void setMemberActive(boolean memberActive) {
		this.memberActive = memberActive;
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
		Member other = (Member) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
