package com.example.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.BioskopPozoristeType;
import com.example.isa.model.RekvizitState;
import com.example.isa.model.Rekviziti;
import com.example.isa.model.VrstaRekvizita;
import com.example.isa.model.users.User;
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
		
		public List<Rekviziti> getAllZvanicna(){
			return rekvizitiRepository.findByVrsta(VrstaRekvizita.ZVANICNI);
		}
		
		public List<Rekviziti> getAllPolovna(){
			return rekvizitiRepository.findByVrsta(VrstaRekvizita.POLOVNI);
		} 
		public List<Rekviziti> getUserRekviziti(User user){
			return rekvizitiRepository.findByPostavljac(user);
		} 
		
		public List<Rekviziti> getNeobredjeni(){
			return rekvizitiRepository.findByOdobren(RekvizitState.NEOBRADJEN);
		} 
		
		public List<Rekviziti> getOdobreni(){
			return rekvizitiRepository.findByOdobren(RekvizitState.PRIHVACEN);
		} 

		
	



}
