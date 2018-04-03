package com.example.isa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.isa.model.users.RegUser;

public interface RegUserRepository extends JpaRepository<RegUser, Long>{
	
	public RegUser findById(Long id);
	
	public RegUser findByEmailAndPassword(String email, String password);
	
	public RegUser findByEmail(String email);
	
	public RegUser findByVerificationCode(String verificationCode);
}
