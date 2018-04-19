package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.RekvizitState;
import com.example.isa.model.Rekviziti;
import com.example.isa.model.VrstaRekvizita;
import com.example.isa.model.users.User;

public interface RekvizitiRepository extends JpaRepository<Rekviziti, Long>{
	
	public List<Rekviziti> findByVrsta(VrstaRekvizita vrsta);//TESTED
	
	public List<Rekviziti> findByPostavljac(User user);//TESTED
	
	public List<Rekviziti> findByOdobren(RekvizitState odobren);//TESTED

	
}
