package com.example.isa.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

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

	public User getCurrentUser() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("**\n\n"+(Long)auth.getPrincipal());
		return userRepository.findById((Long) auth.getPrincipal());
	}

	public void setCurrentUser(User user) {
		final Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
		final Authentication authentication = new PreAuthenticatedAuthenticationToken(user.getId(), null, authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
