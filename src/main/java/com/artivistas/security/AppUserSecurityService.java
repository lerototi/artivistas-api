package com.artivistas.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.artivistas.model.User;
import com.artivistas.repository.UserRepository;
import com.artivistas.security.util.UserSystem;

@Service
public class AppUserSecurityService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

		Optional<User>  userOptional = userRepository.findByMail(mail);
		
		User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha não conferem"));
		
		
		return new UserSystem(user, getAuthority(user));
	}

	private Collection<? extends GrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getAuthority().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getName().toUpperCase())));
		return authorities;
	}

}
