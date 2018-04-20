package com.example.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.MovieShow;
import com.example.isa.model.Projekcija;
import com.example.isa.model.Repertoire;
import com.example.isa.model.Rezervacija;
import com.example.isa.model.Sala;
import com.example.isa.repository.MovieShowRepository;
import com.example.isa.repository.ProjekcijaRepository;
import com.example.isa.repository.RepertoireRepository;
import com.example.isa.repository.RezervacijaRepository;
import com.example.isa.repository.SalaRepository;

@Service
public class ProjekcijaService {

	@Autowired
	private ProjekcijaRepository projekcijaRepository;
	
	@Autowired
	private RepertoireRepository repertoarRepository;
	
	@Autowired
	private MovieShowRepository movieRepository;
	
	@Autowired
	private SalaRepository salaPrepository;
	
	@Autowired
	private RezervacijaRepository reserRepository;
	
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
	
	public Long findParentId(long id) {
		return projekcijaRepository.findById(id).getFilm().getId();
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
		
		ArrayList<Rezervacija> rez = (ArrayList<Rezervacija>)reserRepository.findByProjekcijaId(id);
		
		if(rez.isEmpty()) {
		
			if(temp==null) {
				return null;
			}else {
				projekcijaRepository.delete(id);
				return temp;
			}
		
		}else {
			return null;
		}
	}
	
	public Projekcija create(Projekcija newProj, Long movieId) {
		MovieShow movie = movieRepository.findById(movieId);
		Sala temp = salaPrepository.findById(newProj.getSala().getId());

			if(movie != null) {
				newProj.setFilm(movie);
				if(temp==null)
				{
					newProj.getSala().setBioskop(movie.getRepertoar().getBioskop());
					salaPrepository.save(newProj.getSala());
				}
				
				return projekcijaRepository.save(newProj);
			}else {
				return null;
			}
		
	}
	
	public Projekcija update(Projekcija newProj, long id) {
		Sala s = salaPrepository.findById(newProj.getSala().getId());
		Sala nva = null;
		if(s==null) {
			nva = salaPrepository.save(newProj.getSala());
			s = nva;
		}
		Projekcija toUpdate = projekcijaRepository.findById(newProj.getId());
		toUpdate.setSala(s);
		MovieShow mov = movieRepository.findById(id);
		
		ArrayList<Rezervacija> rez = (ArrayList<Rezervacija>)reserRepository.findByProjekcijaId(id);
		
		if(newProj.getSala().getId()!=toUpdate.getSala().getId()&&!rez.isEmpty()) {
			return null;
		}
		
		if(toUpdate==null||mov==null) {
			return null;
		}
		else {
			newProj.setFilm(mov);
			Projekcija updated = projekcijaRepository.save(newProj);
			return updated;
		}
	}
	
}
