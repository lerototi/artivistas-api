package com.artivistas.security.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;



public class UserSystem extends User {
	
	private static final long serialVersionUID = 1L;

	private com.artivistas.model.User user;

	public UserSystem(com.artivistas.model.User user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getMail(), user.getPassword(), authorities);
		this.user = user;
	}
	
	public com.artivistas.model.User getUser() {
		return user;
	}

}
