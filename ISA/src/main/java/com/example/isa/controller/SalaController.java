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

import com.example.isa.controller.dataTransfer.AlertMessageDTO;
import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.Sala;
import com.example.isa.service.BioskopPozoristeService;
import com.example.isa.service.SalaService;

@RestController
public class SalaController {
	
	@Autowired
	SalaService salaService;
	
	@Autowired
	BioskopPozoristeService bioskopService;

	@RequestMapping(
			value = "/api/cinnemas/{id}/sale",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Sala>> getMoviesFromCinema(@PathVariable("id") Long id){
		ArrayList<Sala> retVal = (ArrayList<Sala>)salaService.getAllinCinema(id);
		if(retVal!=null)
			return new ResponseEntity<Collection<Sala>>(retVal, HttpStatus.OK);
		else
			return new ResponseEntity<Collection<Sala>>(retVal, HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(
			value = "/api/movies/{id}/sale",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Sala>> getMoviesFromMovie(@PathVariable("id") Long id){
		ArrayList<Sala> retVal = (ArrayList<Sala>)salaService.getAllInMovie(id);
		if(retVal!=null)
			return new ResponseEntity<Collection<Sala>>(retVal, HttpStatus.OK);
		else
			return new ResponseEntity<Collection<Sala>>(retVal, HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(
			value = "/api/cinnemas/{id}/sale",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addMovie(@RequestBody Sala newSala, @PathVariable("id") Long id){
		if(newSala.getVisina()<1||newSala.getDuzina()<1) {
			BioskopPozoriste parent = bioskopService.getById(id);
			Sala nova = new Sala(newSala.getNazivBroj(), newSala.getVisina(), newSala.getDuzina(), parent);
			
			Sala retVal = salaService.create(nova);
			
			if(retVal!=null) {
				return new ResponseEntity<Sala>(retVal, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Greska pri kreiranju sale"), HttpStatus.BAD_REQUEST);
			} 
		}
		else {
			return new ResponseEntity<AlertMessageDTO>( new AlertMessageDTO("Sala ne moze imate negativnu duzinu ili visinu"), HttpStatus.BAD_REQUEST);
		}
	
	}
	
}
