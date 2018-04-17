package com.example.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.Rekviziti;
import com.example.isa.repository.RekvizitiRepository;

@Service
public class RekvizitiService {
	

		
		
		@Autowired	
		RekvizitiRepository rekvizitiRepository;
		
		public Rekviziti findOne(Long id) {
			return rekvizitiRepository.findOne(id);
		}

		public List<Rekviziti> findAll() {
			return rekvizitiRepository.findAll();
		}

		public Rekviziti save(Rekviziti rekvizit) {
			return rekvizitiRepository.save(rekvizit);
		}

		public void remove(Long id) {
			rekvizitiRepository.delete(id);
			
		}

		
	



}
