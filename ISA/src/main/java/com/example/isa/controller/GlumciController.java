package com.example.isa.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.model.BioskopPozoriste;
import com.example.isa.model.Glumac;

@RestController
public class GlumciController {

	@RequestMapping(
			value = "/api/actors",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Glumac>> getBioskopPozoriste(){
		ArrayList<Glumac> retVal = null;
		
		return new ResponseEntity<Collection<Glumac>>(retVal, HttpStatus.OK);
		
	}
	
}
