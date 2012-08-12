package com.web.archetype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("authenticationService")
public class AuthenticationService {
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	public boolean login(String username, String password) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
			Authentication authenticate = authenticationManager.authenticate(token);
			if (authenticate.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(authenticate);
				return true;
			}
		} catch (AuthenticationException e) {
			
		}
		return false;
	}
	
	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		invalidateSession();
	}
	
	public void invalidateSession() {
		
	}

}
