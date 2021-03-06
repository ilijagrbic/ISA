package com.example.isa.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.controller.dataTransfer.AlertMessageDTO;
import com.example.isa.controller.dataTransfer.ReservationDTO;
import com.example.isa.controller.dataTransfer.RezervacijaDTO;
import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.Rezervacija;
import com.example.isa.repository.RezervacijaRepository;
import com.example.isa.service.MailService;
import com.example.isa.service.ReservationService;

@RestController
public class ReservationController {
	
	@Autowired
	private ReservationService resevationService;
	
	@Autowired
	private  MailService mailService;
	
	@Autowired
	private RezervacijaRepository resRpository;
	
	@RequestMapping(
			value = "/api/projections/{id}/reservations",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rezervacija> addMovie(@RequestBody RezervacijaDTO createMovie, @PathVariable("id") Long id){
		Rezervacija rez = createMovie.getRezervacija();
		Long sed = createMovie.getRezSedisteId();
		Long use = createMovie.getUserId();
		Rezervacija retVal = resevationService.postReservation(rez, id, sed, use);
		
		if(retVal!=null) {
			return new ResponseEntity<Rezervacija>(retVal, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Rezervacija>(retVal, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(
			value = "/api/reservations/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editRez(@RequestBody RezervacijaDTO createMovie, @PathVariable("id") Long id){
		try{
			Rezervacija rez = createMovie.getRezervacija();

			Long sed = createMovie.getRezSedisteId();
			Long use = createMovie.getUserId();
			rez.setId(id);
			Rezervacija retVal = resevationService.putRese(rez, sed, use);
			
			if(retVal!=null) {
				return new ResponseEntity<Rezervacija>(retVal, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Rezervacija>(retVal, HttpStatus.BAD_REQUEST);
			}
		}
		catch(OptimisticLockException e) {
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Neko vec rezervisao"), HttpStatus.BAD_REQUEST);
		}
		
	}
	@RequestMapping(
			value = "/api/reservations/{id}/rate",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editRezrate(@RequestBody RezervacijaDTO createMovie, @PathVariable("id") Long id){
		Date today = new Date();
		Date termin = resRpository.findById(id).getProjekcija().getDate();
		
		if(today.after(termin)) {
			Rezervacija rez = createMovie.getRezervacija();
			Long sed = createMovie.getRezSedisteId();
			Long use = createMovie.getUserId();
			rez.setId(id);
			if(rez.getOcenaAmbijent()<1||rez.getOcenaAmbijent()>5||rez.getOcenaFilm()<1||rez.getOcenaFilm()>5) {
				return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Ocena mora biti celobrojna i od 1 do 5."), HttpStatus.BAD_REQUEST);
			}else
			{
				Rezervacija retVal = resevationService.rateRese(rez, sed, use);
				
				if(retVal!=null) {
					return new ResponseEntity<Rezervacija>(retVal, HttpStatus.OK);
				}
				else {
					return new ResponseEntity<Rezervacija>(retVal, HttpStatus.BAD_REQUEST);
				}
			}
		}else {
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Prvo pogledajte film pa ocenite."), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(
			value = "/api/reservations/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteReserv(@PathVariable("id") Long id){
		Rezervacija retVal = (Rezervacija)resevationService.delete(id);
		if(retVal!=null)
			return new ResponseEntity<Rezervacija>(retVal, HttpStatus.OK);
		else
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Greska u brisanju rezervacije."), HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(
			value = "/api/user/{id}/reservations",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMoviesFromCinema(@PathVariable("id") Long id){
		ArrayList<Rezervacija> retVal = (ArrayList<Rezervacija>)resevationService.getResFromUser(id);
		if(retVal!=null)
			return new ResponseEntity<Collection<Rezervacija>>(retVal, HttpStatus.OK);
		else
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Ne postoji korisnik sa trazenim id-em."), HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(
			value = "/api/projection/{id}/reservations",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMoviesFromProj(@PathVariable("id") Long id){
		ArrayList<Rezervacija> retVal = (ArrayList<Rezervacija>)resevationService.getFromProj(id);
		if(retVal!=null)
			return new ResponseEntity<Collection<Rezervacija>>(retVal, HttpStatus.OK);
		else
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Ne postoji projekcija sa trazenim id-em."), HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(
			value = "/api/cinnema/{id}/reservations/oneClick",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRezFromCinema(@PathVariable("id") Long id){
		ArrayList<Rezervacija> retVal = (ArrayList<Rezervacija>)resevationService.getOneClick(id);
		if(retVal!=null)
			return new ResponseEntity<Collection<Rezervacija>>(retVal, HttpStatus.OK);
		else
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Ne postoji pozoriste sa trazenim id-em."), HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(value="/api/reservation", method=RequestMethod.POST, consumes="application/json", produces="application/json") 
	public ResponseEntity<Rezervacija> reservation(@RequestBody ReservationDTO reservationDTO){
		System.out.println("\n*** Rezervacija karte ");
		
		if(reservationDTO.getIsHost()==false) {
			// Treba da posalje mail
			System.out.println("\nGost je treba da salje mail");
			mailService.sendReservationMail("http://localhost:8133/#!/listOfReservations", reservationDTO.getIdRezervant(), reservationDTO.getIdHost());
		}
		Rezervacija reservation = resevationService.reservation(reservationDTO);
		if(reservation!=null) {
			System.out.println("Rezervisana je " + reservation);
			if(reservationDTO.getIdHost()==reservationDTO.getIdRezervant()) {
				mailService.sendReservation(reservation,reservationDTO.getGuests());
			}
			return new ResponseEntity<Rezervacija>(reservation, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Rezervacija>(reservation, HttpStatus.BAD_REQUEST);
		}
	}
	//Rezervacije
	@RequestMapping(value="/api/getReservation/{id}", method=RequestMethod.GET) 
	public ResponseEntity<Collection<Rezervacija>> getReservations(@PathVariable("id") Long id){
		System.out.println("\n\n*** Rezervacije");
		ArrayList<Rezervacija> reservations = new ArrayList<Rezervacija>();
		reservations = (ArrayList<Rezervacija>)resevationService.getReservations(id);
		
		return new ResponseEntity<Collection<Rezervacija>>(reservations, HttpStatus.OK);
	}
	
	private Date setHalfHOurBack(Date d){
		Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.MINUTE, -30);
        return c.getTime();

    }
	
	@RequestMapping(value="/api/reservations/{userId}/cancelReservation/{idReservation}", method=RequestMethod.DELETE) 
	public ResponseEntity<?> cancelReservation(@PathVariable("userId") Long userId, @PathVariable("idReservation") Long idReservation){
		Date today = new Date();
		Date termin = setHalfHOurBack(resRpository.findById(idReservation).getProjekcija().getDate());
		
		if(!today.after(termin)) {
			System.out.println("\n\n*** Otkazivanje rezervacije");
			ArrayList<Rezervacija> reservations = new ArrayList<Rezervacija>();
			reservations = (ArrayList<Rezervacija>)resevationService.cancelReservation(userId, idReservation);
			
			return new ResponseEntity<Collection<Rezervacija>>(reservations, HttpStatus.OK);
		}else {
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Previse kasno za odjavljivanje."), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/api/reservations/{userId}/acceptReservation/{idReservation}", method=RequestMethod.PUT) 
	public ResponseEntity<Collection<Rezervacija>> acceptReservation(@PathVariable("userId") Long userId, @PathVariable("idReservation") Long idReservation){
		System.out.println("\n\n*** Prihvatanje rezervacije");
		ArrayList<Rezervacija> reservations = new ArrayList<Rezervacija>();
		reservations = (ArrayList<Rezervacija>)resevationService.acceptReservation(userId, idReservation);
		
		return new ResponseEntity<Collection<Rezervacija>>(reservations, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/getAllReservations", method=RequestMethod.GET) 
	public ResponseEntity<Collection<Rezervacija>> getAllReservations(){
		System.out.println("\n\n*** Uzimanje svih rezervacija");
		ArrayList<Rezervacija> reservations = new ArrayList<Rezervacija>();
		reservations = (ArrayList<Rezervacija>)resevationService.getAllReservations();
		for(Rezervacija res : reservations) {
			System.out.println("Usao uopste?");
			System.out.println(res.getId()); 
		}
		return new ResponseEntity<Collection<Rezervacija>>(reservations, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/getHistory/{id}", method=RequestMethod.GET) 
	public ResponseEntity<Collection<BioskopPozoriste>> getHistory(@PathVariable("id") Long id){
		System.out.println("\n\n*** Poseceni bioskopi");
		ArrayList<BioskopPozoriste> bioskopi = (ArrayList<BioskopPozoriste>) resevationService.getHistory(id);
		
		for(BioskopPozoriste bp : bioskopi) {
			System.out.println(bp.getId());
		}
		return new ResponseEntity<Collection<BioskopPozoriste>>(bioskopi, HttpStatus.OK);
	}

}
