package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Projekcija;

public interface ProjekcijaRepository extends JpaRepository<Projekcija, Long>{

	public List<Projekcija> findByFilmId(long id);
	
	public Projekcija findById(long id);
	
	
}
