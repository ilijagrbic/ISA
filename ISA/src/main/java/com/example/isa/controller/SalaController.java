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

import com.example.isa.controller.dataTransfer.MovieShowDTO;
import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.MovieShow;
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
			value = "/api/cinnemas/{id}/sale",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sala> addMovie(@RequestBody Sala newSala, @PathVariable("id") Long id){
		BioskopPozoriste parent = bioskopService.getById(id);
		System.out.println(newSala.getDuzina());
		Sala nova = new Sala(newSala.getNazivBroj(), newSala.getVisina(), newSala.getDuzina(), parent);
		
		Sala retVal = salaService.create(nova);
		
		if(retVal!=null) {
			return new ResponseEntity<Sala>(retVal, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Sala>(retVal, HttpStatus.BAD_REQUEST);
		}
	
	}
	
}
