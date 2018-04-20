package com.example.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.Licitacija;
import com.example.isa.model.Rekviziti;
import com.example.isa.model.users.User;
import com.example.isa.repository.LicitacijaRepository;

@Service
public class LicitacijaService {
	

		
		
		@Autowired	
		LicitacijaRepository licitacijaRepository;
		
		public Licitacija findOne(Long id) {
			return licitacijaRepository.findOne(id);
		}

		public List<Licitacija> findAll() {
			return licitacijaRepository.findAll();
		}
		
		public List<Licitacija> findAll(Rekviziti rekvizit) {
			return licitacijaRepository.findByRekvizit(rekvizit);
		}

		public Licitacija save(Licitacija rekvizit) {
			return licitacijaRepository.save(rekvizit);
		}

		public void remove(Long id) {
			licitacijaRepository.delete(id);
			
		}
		
		public List<Licitacija> findAll(User user) {
			return licitacijaRepository.findByPonudjac(user);
		}


		
	



}
