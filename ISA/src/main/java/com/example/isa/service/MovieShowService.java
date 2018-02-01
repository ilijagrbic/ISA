package com.example.isa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.MovieShow;
import com.example.isa.model.Repertoire;
import com.example.isa.repository.MovieShowRepository;
import com.example.isa.repository.RepertoireRepository;

@Service
public class MovieShowService {

	@Autowired
	private RepertoireRepository repertoarRepository;
	
	@Autowired
	private MovieShowRepository movieRepository;
		
	public List<MovieShow> getAll(){
		return movieRepository.findAll();
	}
	
	/*
	 * metoda ne radi jos uvek, testiranje potrebno
	 */
	public List<MovieShow> getFromCinema(Long id){
		Optional<Repertoire> retVal = repertoarRepository.findByBioskopId(id);
		if(retVal.isPresent()) {
			return movieRepository.findByRepertoarId(retVal.get().getId());
		}else {
			return null;
		}
	}
	
	public MovieShow getOneMovie(Long id) {
		return movieRepository.findById(id);
	}
	
	public MovieShow create(MovieShow newMovie, long cinId) {
		Optional<Repertoire> parent = repertoarRepository.findByBioskopId(cinId);
		if(parent.isPresent()) {
			newMovie.setRepertoar(parent.get());
			return movieRepository.save(newMovie);
		}else {
			return null;
		}
	}
}
