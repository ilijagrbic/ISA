package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.BioskopPozoristeType;

public interface BioskopPozoristeRepository extends JpaRepository<BioskopPozoriste, Long>{

	public BioskopPozoriste findById(Long id);//nontested
	
	public List<BioskopPozoriste> findByType(BioskopPozoristeType type);//nontested
	
	
}
