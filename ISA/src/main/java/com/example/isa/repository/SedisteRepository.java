package com.example.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Sediste;

public interface SedisteRepository extends JpaRepository<Sediste, Long>{

	public Sediste findById(Long id);
	
}
