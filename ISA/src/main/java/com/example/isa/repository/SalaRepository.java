package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.MovieShow;
import com.example.isa.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long>{

	public Sala findById(Long id);
	
	List<Sala> findByBioskopId(long id);
	
}
