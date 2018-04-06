package com.example.isa.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.isa.model.Sala;

public class SalaController {

	@RequestMapping(
			value = "/api/cinnemas/{id}/sale",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Sala>> getMoviesFromCinema(@PathVariable("id") Long id){
		ArrayList<Sala> retVal = null;
		if(retVal!=null)
			return new ResponseEntity<Collection<Sala>>(retVal, HttpStatus.OK);
		else
			return new ResponseEntity<Collection<Sala>>(retVal, HttpStatus.BAD_REQUEST);
		
	}
	
}
