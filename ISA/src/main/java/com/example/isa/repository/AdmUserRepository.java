package com.example.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.users.AdmUser;
import com.example.isa.model.users.RegUser;

public interface AdmUserRepository extends JpaRepository<AdmUser, Long>{

	public AdmUser findById(Long id);
	
	public AdmUser findByEmailAndPassword(String email, String password);
	
	public AdmUser findByEmail(String email);
}
