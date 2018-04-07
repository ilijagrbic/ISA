package com.example.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.users.Role;
import com.example.isa.model.users.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findById(Long id);
	
	public User findByEmailAndPassword(String email, String password);
	
	public User findByEmailAndPasswordAndRole(String email, String password,Role role);
	
	public User findByEmail(String email);
	
	public User findByVerificationCode(String verificationCode);
}
