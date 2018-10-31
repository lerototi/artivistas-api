package com.artivistas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "profile_user")
public class ProfileUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idProfile;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "surname", nullable = false, length = 50)
	private String surname;
	
	@Column
	private String cellphone;

	// TODO carregar imagem
	// @Column(name="image", nullable = true)
	// private String Image;

	@Column(name = "description", nullable = true, length = 300)
	private String description;

	@Column(name = "facebook", nullable = true, length = 50)
	private String facebook;

	@Column(name = "instagram", nullable = true, length = 50)
	private String instagram;

	@Column(name = "brithday", nullable = true)
	private Date dateOfBrithday;

	@Column(name = "date_profile", nullable = false)
	private Date dateOfCreationProfile;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_user")
	@JsonBackReference
	private User user;


	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(Long idProfile) {
		this.idProfile = idProfile;
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

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public Date getDateOfBrithday() {
		return dateOfBrithday;
	}

	public void setDateOfBrithday(Date dateOfBrithday) {
		this.dateOfBrithday = dateOfBrithday;
	}

	public Date getDateOfCreationProfile() {
		return dateOfCreationProfile;
	}

	public void setDateOfCreationProfile(Date dateOfCreationProfile) {
		this.dateOfCreationProfile = dateOfCreationProfile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProfile == null) ? 0 : idProfile.hashCode());
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
		ProfileUser other = (ProfileUser) obj;
		if (idProfile == null) {
			if (other.idProfile != null)
				return false;
		} else if (!idProfile.equals(other.idProfile))
			return false;
		return true;
	}
	

	
}
