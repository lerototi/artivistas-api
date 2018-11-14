package com.artivistas.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	
	@Column(name = "mail", nullable = false, length = 50, unique=true)
	@NotNull
	@Size(min=3, max = 50)
	private String mail;
	
	@Column(name = "password", nullable = false)
	@NotNull
	@Size(min=6, max = 30)
	private String password;
	
	@Column(name = "enabled", nullable = false)
	@NotNull
	private boolean enabled;

	
	@OneToOne(mappedBy="user",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private ProfileUser pflUser;
	
	@OneToMany(mappedBy="userCreator")
	@JsonIgnore
	private List<ProfileGroup> profileGroups;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name ="authority_user", joinColumns= @JoinColumn(name="fk_user"),
	inverseJoinColumns=@JoinColumn(name="fk_authority"))
	private List<Authority> authority;
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public ProfileUser getPflUser() {
		return pflUser;
	}
	public void setPflUser(ProfileUser pflUser) {
		this.pflUser = pflUser;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public List<ProfileGroup> getProfileGroups() {
		return profileGroups;
	}
	public void setProfileGroups(List<ProfileGroup> profileGroups) {
		this.profileGroups = profileGroups;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public List<Authority> getAuthority() {
		return authority;
	}
	public void setAuthority(List<Authority> authority) {
		this.authority = authority;
	}
	
	
}
