package com.example.isa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Repertoire;

public interface RepertoireRepository extends JpaRepository<Repertoire, Long>{

	Optional<Repertoire> findByBioskopId(long id);
	
}
