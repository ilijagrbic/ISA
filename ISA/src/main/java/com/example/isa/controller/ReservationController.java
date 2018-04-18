package com.example.isa.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.controller.dataTransfer.RezervacijaDTO;
import com.example.isa.model.MovieShow;
import com.example.isa.model.Rezervacija;
import com.example.isa.service.ReservationService;

@RestController
public class ReservationController {
	
	@Autowired
	private ReservationService resevationService;

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
			value = "/api/projections/{id}/reservations",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rezervacija> editRez(@RequestBody RezervacijaDTO createMovie, @PathVariable("id") Long id){
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
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rezervacija> deleteReserv(@PathVariable("id") Long id){
		Rezervacija retVal = (Rezervacija)resevationService.delete(id);
		if(retVal!=null)
			return new ResponseEntity<Rezervacija>(retVal, HttpStatus.OK);
		else
			return new ResponseEntity<Rezervacija>(retVal, HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(
			value = "/api/user/{id}/reservations",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Rezervacija>> getMoviesFromCinema(@PathVariable("id") Long id){
		ArrayList<Rezervacija> retVal = (ArrayList<Rezervacija>)resevationService.getResFromUser(id);
		if(retVal!=null)
			return new ResponseEntity<Collection<Rezervacija>>(retVal, HttpStatus.OK);
		else
			return new ResponseEntity<Collection<Rezervacija>>(retVal, HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(
			value = "/api/projection/{id}/reservations",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Rezervacija>> getMoviesFromProj(@PathVariable("id") Long id){
		ArrayList<Rezervacija> retVal = (ArrayList<Rezervacija>)resevationService.getFromProj(id);
		if(retVal!=null)
			return new ResponseEntity<Collection<Rezervacija>>(retVal, HttpStatus.OK);
		else
			return new ResponseEntity<Collection<Rezervacija>>(retVal, HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(
			value = "/api/cinnema/{id}/reservations/oneClick",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Rezervacija>> getRezFromCinema(@PathVariable("id") Long id){
		ArrayList<Rezervacija> retVal = (ArrayList<Rezervacija>)resevationService.getOneClick(id);
		if(retVal!=null)
			return new ResponseEntity<Collection<Rezervacija>>(retVal, HttpStatus.OK);
		else
			return new ResponseEntity<Collection<Rezervacija>>(retVal, HttpStatus.BAD_REQUEST);
		
	}

}
