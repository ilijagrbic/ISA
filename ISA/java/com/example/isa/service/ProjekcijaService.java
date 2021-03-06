package com.example.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.MovieShow;
import com.example.isa.model.Projekcija;
import com.example.isa.model.Repertoire;
import com.example.isa.repository.MovieShowRepository;
import com.example.isa.repository.ProjekcijaRepository;
import com.example.isa.repository.RepertoireRepository;

@Service
public class ProjekcijaService {

	@Autowired
	private ProjekcijaRepository projekcijaRepository;
	
	@Autowired
	private RepertoireRepository repertoarRepository;
	
	@Autowired
	private MovieShowRepository movieRepository;
	
	public Projekcija findById(long id) {
		return projekcijaRepository.findById(id);
	}
	
	public List<Projekcija> findAllInCinema(long id){
		Repertoire retVal1 = repertoarRepository.findByBioskopId(id);
		if(retVal1==null) return null;
		ArrayList<MovieShow> filmovi = (ArrayList<MovieShow>)movieRepository.findByRepertoarId(retVal1.getId());

		ArrayList<Projekcija> retVal = new ArrayList<Projekcija>();
		for(MovieShow mov:filmovi) {
			retVal.addAll((ArrayList<Projekcija>)(projekcijaRepository.findByFilmId(mov.getId()))); 
			
		}
		
		return retVal;
	}
	
	public List<Projekcija> findAllInCinemaByMovie(long id, long idMovie) {
		Repertoire retVal1 = repertoarRepository.findByBioskopId(id);
		MovieShow retVal2 = movieRepository.findById(idMovie);
		
		if(retVal1==null||retVal2==null) return null;
		ArrayList<MovieShow> filmovi = (ArrayList<MovieShow>)movieRepository.findByRepertoarId(retVal1.getId());

		ArrayList<Projekcija> retVal = new ArrayList<Projekcija>();
		for(MovieShow mov:filmovi) {
			if(mov.getId()==idMovie) {
				retVal.addAll((ArrayList<Projekcija>)(projekcijaRepository.findByFilmId(mov.getId()))); 
				break;
			}
			
		}
		
		return retVal;
	}
	
	public Projekcija delete(long id) {
		Projekcija temp = projekcijaRepository.findById(id);
		if(temp==null) {
			return null;
		}else {
			projekcijaRepository.delete(id);
			return temp;
		}
	}
	
	public Projekcija create(Projekcija newProj) {
		/*if(projekcijaRepository.exists(newProj.getId())) {
			return null;
		}else {
			
		}*/
		return projekcijaRepository.save(newProj);
	}
	
	public Projekcija update(Projekcija newProj) {
		Projekcija toUpdate = projekcijaRepository.findById(newProj.getId());
		if(toUpdate==null) {
			return null;
		}
		else {
			return projekcijaRepository.save(newProj);
		}
	}
	
}
