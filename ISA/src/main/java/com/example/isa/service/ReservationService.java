package com.example.isa.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.controller.dataTransfer.GrafikDTO;
import com.example.isa.controller.dataTransfer.IncomeReportDTO;
import com.example.isa.controller.dataTransfer.ReservationDTO;
import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.MovieShow;
import com.example.isa.model.Projekcija;
import com.example.isa.model.Repertoire;
import com.example.isa.model.Rezervacija;
import com.example.isa.model.RezervacijaStatus;
import com.example.isa.model.Sediste;
import com.example.isa.model.users.User;
import com.example.isa.repository.BioskopPozoristeRepository;
import com.example.isa.repository.ProjekcijaRepository;
import com.example.isa.repository.RepertoireRepository;
import com.example.isa.repository.RezervacijaRepository;
import com.example.isa.repository.SedisteRepository;
import com.example.isa.repository.UserRepository;

@Service
public class ReservationService {
	
	@Autowired
	private BioskopPozoristeRepository bioskoRepository;
	
	@Autowired
	private  RepertoireRepository repertoireRepository;
	
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
	
	@Transactional
	public Rezervacija putRese(Rezervacija newr, Long idSediste, Long idUser) {
		Rezervacija toUpdate = reservationRepository.findById(newr.getId());
		/*Projekcija pro = projRepository.findById(idProj);
		Sediste sed = sedisteRepository.findById(idSediste);
		*/
		
		/*newr.setProjekcija(pro);
		newr.setFilm(pro.getFilm());
		newr.setRezervisanoMesto(sed);*/
		/*if(us==null) {
			newr.setRezervant(null);
		}else {
			newr.setRezervant(us);
		}*/
		toUpdate.setOcenaAmbijent(newr.getOcenaAmbijent());
		toUpdate.setStatus(newr.getStatus());
		toUpdate.setOcenaFilm(newr.getOcenaFilm());
		toUpdate.setHostId(newr.getHostId());
		
		User us = userRepository.findById(idUser);
		toUpdate.setRezervant(us);
		
		
		return reservationRepository.save(toUpdate);
	}
	
	public Rezervacija rateRese(Rezervacija newr, Long idSediste, Long idUser) {
		Rezervacija toUpdate = reservationRepository.findById(newr.getId());
		/*Projekcija pro = projRepository.findById(idProj);
		Sediste sed = sedisteRepository.findById(idSediste);
		*/
		
		/*newr.setProjekcija(pro);
		newr.setFilm(pro.getFilm());
		newr.setRezervisanoMesto(sed);*/
		/*if(us==null) {
			newr.setRezervant(null);
		}else {
			newr.setRezervant(us);
		}*/
		toUpdate.setOcenaAmbijent(newr.getOcenaAmbijent());
		toUpdate.setStatus(newr.getStatus());
		toUpdate.setOcenaFilm(newr.getOcenaFilm());
		//toUpdate.setHostId(newr.getHostId());
		
		/*User us = userRepository.findById(idUser);
		toUpdate.setRezervant(us);*/
		
		
		return reservationRepository.save(toUpdate);
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
	
	public double getIncome(long id, IncomeReportDTO datumi) {
		ArrayList<Rezervacija> retVal = (ArrayList<Rezervacija>)getInCinnema(id);
		
		double sum = 0;
		
		for(Rezervacija r:retVal) {
			if(datumi.getOd().before(r.getProjekcija().getDate())&&datumi.getDoo().after(r.getProjekcija().getDate())) {
				sum+=r.getProjekcija().getCena();
			}
		}
		
		return sum;
		
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
	
	public List<GrafikDTO> getPosete(long id, IncomeReportDTO datumi){
		//GrafikDTO pocetak = new GrafikDTO(datumi.getOd().getDate()-1, datumi.getOd().getMonth()+1, datumi.getOd().getYear()+1900, 0);
		//System.out.println(pocetak);
		/*ArrayList<Rezervacija> retVal = (ArrayList<Rezervacija>)getInCinnema(id);
		HashMap<Date, Integer> rv = new HashMap<Date, Integer>();
		
		double sum = 0;
		
		for(Rezervacija r:retVal) {
			if(datumi.getOd().before(r.getProjekcija().getDate())&&datumi.getDoo().after(r.getProjekcija().getDate())) {
				rv.put(r.getProjekcija().getDate(), rv.get(r.getProjekcija().getDate())+1);
			}
		}
		
		return rv;*/
		
		GrafikDTO pocetak = new GrafikDTO(0,0);
		ArrayList<GrafikDTO> retVal = new ArrayList<GrafikDTO>();
		
		
		return null;
	}
	
	public Rezervacija reservation(ReservationDTO reservationDTO) {
		System.out.println("Rezervant " + reservationDTO.getIdRezervant());
		Rezervacija reservation = new Rezervacija();
		reservation.setProjekcija(reservationDTO.getProjekcija());
		reservation.setHostId(reservationDTO.getIdHost());
		reservation.setIsHost(reservationDTO.getIsHost());
		if(reservationDTO.getIsHost()==false) {
			reservation.setStatus(RezervacijaStatus.WAITING);
		}
		else {
			reservation.setStatus(RezervacijaStatus.ACCEPTED);
		}
		reservation.setFilm(reservationDTO.getMovie());

		reservation.setRezervant(userRepository.findById(reservationDTO.getIdRezervant()));
		reservation.setRezervisanoMesto(sedisteRepository.findById(reservationDTO.getRezSedisteId()));
		
		reservationRepository.save(reservation);
		
		return reservation;
	}
	
	//Rezervacije
	public List<Rezervacija> getReservations(Long id){
		List<Rezervacija> reservations = new ArrayList<Rezervacija>();
		for(Rezervacija r : reservationRepository.findAll()) {
			if(r.getRezervant()!=null) {
				if(r.getRezervant().getId() == id) {
					reservations.add(r);
					System.out.println(r.getId());
				}
			}
			
		}
		return reservations;
	}
	
	public List<Rezervacija> cancelReservation(Long userId, Long reservationId){
		List<Rezervacija> deletedReservations = new ArrayList<Rezervacija>();
	
		for(Rezervacija r : reservationRepository.findAll()) {
			if(r.getRezervant()!=null&&r.getHostId()!=null) {
				if(r.getRezervant().getId() == userId && r.getId() == reservationId) {
					deletedReservations.add(r);
					System.out.println(r.getId());
					reservationRepository.delete(r);
				}	
				
			}
		}
		return getReservations(userId);
	}
	
	
	public List<Rezervacija> acceptReservation(Long userId, Long reservationId){
		Rezervacija reservation = reservationRepository.findById(reservationId);
		reservation = reservationRepository.findById(reservationId);
		reservation.setStatus(RezervacijaStatus.ACCEPTED);
		reservationRepository.save(reservation);
		return getReservations(userId);
	}
	
	
	public List<Rezervacija> getAllReservations(){
		System.out.println("Usao da pronadje");
		return reservationRepository.findAll();
	}
	
	public List<BioskopPozoriste> getHistory(Long id){
		ArrayList<BioskopPozoriste> bioskopi = new ArrayList<BioskopPozoriste>(); 
		
		for(Rezervacija r : reservationRepository.findByRezervantId(id)) {
			for (Repertoire rep : repertoireRepository.findAll()) {
				if(rep.getMovies().contains(r.getFilm())) {
					bioskopi.add(rep.getBioskop());
				}
			}
		}
		return bioskopi;
	}
}
