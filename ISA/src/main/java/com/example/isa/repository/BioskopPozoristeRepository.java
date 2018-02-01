package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.BioskopPozoristeType;

public interface BioskopPozoristeRepository extends JpaRepository<BioskopPozoriste, Long>{

	public BioskopPozoriste findById(Long id);//TESTED
	
	public List<BioskopPozoriste> findByType(BioskopPozoristeType type);//TESTED
	
	
}
