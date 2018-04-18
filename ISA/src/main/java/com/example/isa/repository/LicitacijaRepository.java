package com.example.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa.model.Licitacija;
import com.example.isa.model.Rekviziti;
import com.example.isa.model.users.User;

public interface LicitacijaRepository extends JpaRepository<Licitacija, Long>{
	public List<Licitacija> findByRekvizit(Rekviziti rekvizit);//TESTED
	

}
