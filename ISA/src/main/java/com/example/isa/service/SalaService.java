package com.example.isa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.MovieShow;
import com.example.isa.model.Projekcija;
import com.example.isa.model.Sala;
import com.example.isa.repository.BioskopPozoristeRepository;
import com.example.isa.repository.MovieShowRepository;
import com.example.isa.repository.SalaRepository;

@Service
public class SalaService {

	@Autowired
	private SalaRepository salaRepository;
	
	@Autowired
	private BioskopPozoristeRepository bioskopRepository;
	
	@Autowired
	private MovieShowRepository movieRepository;
	
	@Autowired
	private ProjekcijaService projService;
	
	public List<Sala> getAllinCinema(Long id)
	{
		return salaRepository.findByBioskopId(id);
	}
	
	public List<Sala> getAllInMovie(Long id){
		MovieShow mov = movieRepository.findById(id);
		HashMap<Long, Sala> temp = new HashMap<Long, Sala>();
		
		ArrayList<Projekcija> arr = (ArrayList<Projekcija>)projService.findAllInCinemaByMovie(mov.getRepertoar().getBioskop().getId(), id);
		
		for(Projekcija p:arr) {
			if(!temp.containsKey(p.getId())) {
				temp.put(p.getSala().getId(), p.getSala());
			}
		}
		
		ArrayList<Sala> retVal = new ArrayList<Sala>();
		retVal.addAll(temp.values());
		
		return retVal;
	}
	
	public Sala getById(long id) {
		return salaRepository.findById(id);
	}
	
	public Sala create(Sala newSala) {
		return salaRepository.save(newSala);
	}
}
