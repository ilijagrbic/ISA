package com.example.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.Glumac;
import com.example.isa.repository.GlumacRepository;

@Service
public class GlumciService {
	
	@Autowired
	private GlumacRepository glumciRepository;
	
	public List<Glumac> getAll(){
		return glumciRepository.findAll();
	}
	
	public Glumac getById(long id) {
		return glumciRepository.findById(id);
	}
	
	public Glumac update(Glumac updatedGlumac) {
		Glumac toUpdate = getById(updatedGlumac.getId());
		if(toUpdate!=null) {
			toUpdate.setIme(updatedGlumac.getIme());
			toUpdate.setPrezime(updatedGlumac.getPrezime());
			return glumciRepository.save(toUpdate);
		}
		
		return null;
	}
	
	public Glumac create(Glumac newGlumac) {
		return glumciRepository.save(newGlumac);
	}
}
