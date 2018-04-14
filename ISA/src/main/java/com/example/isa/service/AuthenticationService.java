package com.example.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.isa.model.users.User;
import com.example.isa.repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;
	
	public User findUser(User user) {
		return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
	}

	// Verifikacija na osnovu maila
	public User verifyUser(String verificationCode) {
		User user = userRepository.findByVerificationCode(verificationCode);
		user.setActiaved(true);
		userRepository.save(user);
		return user;
	}
	/*
	public User getCurrentUser() {
		System.out.println("Uzimamo korisnika trenutno zakacenog");
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("**\n\n"+auth.getPrincipal());
		return userRepository.findById((Long) auth.getPrincipal());
	}
	
	public void setCurrentUser(User user) {
		System.out.println(" * USER "+ user.getId());
		final Authentication authentication = new PreAuthenticatedAuthenticationToken(user.getId(), null);
		System.out.println(" *PRINCIPAL "+(Long)authentication.getPrincipal());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("**\n\n"+(Long)auth.getPrincipal());
	}*/
	
}
