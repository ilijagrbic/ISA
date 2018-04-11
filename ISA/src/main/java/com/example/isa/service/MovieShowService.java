package com.example.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.Glumac;
import com.example.isa.model.MovieShow;
import com.example.isa.model.Repertoire;
import com.example.isa.repository.BioskopPozoristeRepository;
import com.example.isa.repository.GlumacRepository;
import com.example.isa.repository.MovieShowRepository;
import com.example.isa.repository.RepertoireRepository;

@Service
public class MovieShowService {

	@Autowired
	private RepertoireRepository repertoarRepository;
	
	@Autowired
	private MovieShowRepository movieRepository;
	
	@Autowired
	private GlumacRepository glumacRepository;
		
	public List<MovieShow> getAll(){
		return movieRepository.findAll();
	}

	public List<MovieShow> getFromCinema(Long id){
		Repertoire retVal = repertoarRepository.findByBioskopId(id);
		if(retVal!=null) {
			return movieRepository.findByRepertoarId(retVal.getId());
		}else {
			return null;
		}
	}
	
	public MovieShow getOneMovie(Long id) {
		return movieRepository.findById(id);
	}
	
	public MovieShow update(MovieShow updated, long movId) {
		MovieShow toUpdate = movieRepository.findById(updated.getId());
		if(toUpdate!=null) {
			for(Glumac g:updated.getGlumci()) {
				System.out.println(g.getId()+"--"+g.getIme()+"--"+g.getPrezime());
				if(glumacRepository.findById(g.getId())==null||g.getId()!=null) { 
					
					glumacRepository.save(g);
				}
			}
			return movieRepository.save(toUpdate.update(updated));
		}
		
		return null;
	}
	
	public MovieShow create(MovieShow newMovie, long cinId) {
		Repertoire parent = repertoarRepository.findByBioskopId(cinId);

		if(parent!=null) {
			newMovie.setRepertoar(parent);
			for(Glumac g:newMovie.getGlumci()) {
				if(glumacRepository.findById(g.getId())==null) {
					glumacRepository.save(g);
				}
			}
			return movieRepository.save(newMovie);
		}else {
			return null;
		}
	}
	
	public Long delete(long id) {
		MovieShow toDelete = movieRepository.findById(id);
		if(toDelete!=null) {
			movieRepository.delete(id);
			return id;
		}
		return null;
	}
}
