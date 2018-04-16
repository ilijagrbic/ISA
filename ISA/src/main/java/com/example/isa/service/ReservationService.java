package com.example.isa.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.MovieShow;
import com.example.isa.model.Projekcija;
import com.example.isa.model.Rezervacija;
import com.example.isa.model.RezervacijaStatus;
import com.example.isa.model.Sediste;
import com.example.isa.model.users.User;
import com.example.isa.repository.BioskopPozoristeRepository;
import com.example.isa.repository.ProjekcijaRepository;
import com.example.isa.repository.RezervacijaRepository;
import com.example.isa.repository.SedisteRepository;
import com.example.isa.repository.UserRepository;

@Service
public class ReservationService {
	
	@Autowired
	private BioskopPozoristeRepository bioskoRepository;
	
	@Autowired
	private RezervacijaRepository reservationRepository;
	
	@Autowired
	private ProjekcijaRepository projRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SedisteRepository sedisteRepository;
	
	@Autowired
	private MovieShowService movieShowService;
	
	@Transactional
	public Rezervacija postReservation(Rezervacija newr, Long idProj, Long idSediste, Long idUser) {
		Projekcija pro = projRepository.findById(idProj);
		Sediste sed = sedisteRepository.findById(idSediste);
		User us = userRepository.findById(idUser);
		
		newr.setProjekcija(pro);
		newr.setFilm(pro.getFilm());
		newr.setRezervisanoMesto(sed);
		if(us==null) {
			newr.setRezervant(null);
		}else {
			newr.setRezervant(us);
		}
		
		return reservationRepository.save(newr);
		
	}
	
	public Rezervacija delete(long id) {
		Rezervacija toDelete = reservationRepository.findById(id);
		if(toDelete!=null) {
			reservationRepository.delete(id);
			return toDelete;
		}
		return null;
	}
	
	public List<Rezervacija> getResFromUser(long id){
		return reservationRepository.findByRezervantId(id);
	}
	public List<Rezervacija> getFromProj(long id){
		return reservationRepository.findByProjekcijaId(id);
	}
	public List<Rezervacija> getOneClick(long id){
		BioskopPozoriste b = bioskoRepository.findById(id);
		
		ArrayList<Rezervacija> retVal = new ArrayList<Rezervacija>();
		ArrayList<Rezervacija> temp = new ArrayList<Rezervacija>();
		
		for(MovieShow m:movieShowService.getFromCinema(b.getId())) {
			for(Projekcija p:projRepository.findByFilmId(m.getId())) {
				temp = (ArrayList<Rezervacija>)getFromProj(p.getId());
				for(Rezervacija r:temp) {
					if(r.getStatus()==RezervacijaStatus.ONECLICK) {
						retVal.add(r);
					}
				}
			}
		}
		return retVal;
	}
	
	public List<Rezervacija> getInCinnema(long id){
		BioskopPozoriste b = bioskoRepository.findById(id);
		
		ArrayList<Rezervacija> retVal = new ArrayList<Rezervacija>();
		ArrayList<Rezervacija> temp = new ArrayList<Rezervacija>();
		
		for(MovieShow m:movieShowService.getFromCinema(b.getId())) {
			for(Projekcija p:projRepository.findByFilmId(m.getId())) {
				temp = (ArrayList<Rezervacija>)getFromProj(p.getId());
				retVal.addAll(temp);
			}
		}
		return retVal;
	}
	
	public double getAvgAmbijent(long id) {
		ArrayList<Rezervacija> retVal = (ArrayList<Rezervacija>)getInCinnema(id);
		
		double len = 0;
		double sum = 0;
		
		for(Rezervacija r:retVal) {
			if(r.getStatus()==RezervacijaStatus.WATCHED) {
				len++;
				sum+=r.getOcenaAmbijent();
			}
		}
		
		if(sum!=0) {
			return sum/len;
		}else {
			return 0;
		}
		
	}
	
	public double getAvgProjOcena(long id) {
		ArrayList<Rezervacija> retVal = (ArrayList<Rezervacija>)getFromProj(id);
		
		double len = 0;
		double sum = 0;
		
		for(Rezervacija r:retVal) {
			if(r.getStatus()==RezervacijaStatus.WATCHED) {
				len++;
				sum+=r.getOcenaAmbijent();
			}
		}
		
		if(sum!=0) {
			return sum/len;
		}else {
			return 0;
		}
		
	}
	
	public double getAvgMovOcena(long id) {
		
		ArrayList<Rezervacija> retVal = new ArrayList<Rezervacija>();
		ArrayList<Rezervacija> temp = new ArrayList<Rezervacija>();
		
		for(Projekcija p:projRepository.findByFilmId(id)) {
			temp = (ArrayList<Rezervacija>)getFromProj(p.getId());
			retVal.addAll(temp);
		}
		
		
		
		double len = 0;
		double sum = 0;
		
		for(Rezervacija r:retVal) {
			if(r.getStatus()==RezervacijaStatus.WATCHED) {
				len++;
				sum+=r.getOcenaFilm();
			}
		}
		
		if(sum!=0) {
			return sum/len;
		}else {
			return 0;
		}
		
	}
}
