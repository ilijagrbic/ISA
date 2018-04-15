package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.MovieShow;
import com.example.isa.model.Rezervacija;
import com.example.isa.model.Sala;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long>{

	public Rezervacija findById(Long id);
	
	List<Rezervacija> findByRezervantId(long id);
	
	List<Rezervacija> findByProjekcijaId(long id);
	
}
