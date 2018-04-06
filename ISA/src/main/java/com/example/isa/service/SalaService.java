package com.example.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.Glumac;
import com.example.isa.model.Repertoire;
import com.example.isa.model.Sala;
import com.example.isa.repository.BioskopPozoristeRepository;
import com.example.isa.repository.RepertoireRepository;
import com.example.isa.repository.SalaRepository;

@Service
public class SalaService {

	@Autowired
	private SalaRepository salaRepository;
	
	@Autowired
	private BioskopPozoristeRepository bioskopRepository;
	
	public List<Sala> getAllinCinema(Long id)
	{
		return salaRepository.findByBioskopId(id);
	}
	
	public Sala getById(long id) {
		return salaRepository.findById(id);
	}
	
	public Sala create(Sala newSala) {
		return salaRepository.save(newSala);
	}
}
