package com.example.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.Glumac;

public interface GlumacRepository extends JpaRepository<Glumac, Long>{

	public Glumac findById(Long id);
	
}
