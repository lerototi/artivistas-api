package com.artivistas.resources;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artivistas.config.property.ArtivistasApiProperty;

@RestController
@RequestMapping("/tokens")
public class TokenResource {
	
	@Autowired
	ArtivistasApiProperty artivistasApiProperty;

	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(artivistasApiProperty.getSecurity().isEnableHttps()); //TODO: Em produção será true
		cookie.setPath(request.getContentType() + "/oauth/token");
		cookie.setMaxAge(0);
		
		response.addCookie(cookie);
		response.setStatus(HttpStatus.NO_CONTENT.value());
	}
}
