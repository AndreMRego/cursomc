package com.andrerego.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.andrerego.cursomc.security.UserSS;

public class UserService {
	
	public static UserSS auhtenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
			
	}

}
