package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Rekviziti;
import com.example.isa.model.VrstaRekvizita;

public interface RekvizitiRepository extends JpaRepository<Rekviziti, Long>{
	
	public List<Rekviziti> findByVrsta(VrstaRekvizita vrsta);//TESTED
	
}
