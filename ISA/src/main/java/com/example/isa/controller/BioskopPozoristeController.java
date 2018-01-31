package com.example.isa.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.controller.dataTransfer.BioskopDTO;
import com.example.isa.model.BioskopPozoriste;
import com.example.isa.service.BioskopPozoristeService;

@RestController
public class BioskopPozoristeController {

	@Autowired
	private BioskopPozoristeService bioskopService;
	
	@RequestMapping(
			value = "/api/cinnemas",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<BioskopPozoriste>> getBioskopPozoriste(){
		ArrayList<BioskopPozoriste> retVal = (ArrayList<BioskopPozoriste>)bioskopService.getAll();
		
		return new ResponseEntity<Collection<BioskopPozoriste>>(retVal, HttpStatus.OK);
		
	}
	
	@RequestMapping(
			value = "/api/onlycinnemas",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<BioskopPozoriste>> getBioskops(){
		ArrayList<BioskopPozoriste> retVal = (ArrayList<BioskopPozoriste>)bioskopService.getAllCinemas();
		
		return new ResponseEntity<Collection<BioskopPozoriste>>(retVal, HttpStatus.OK);
		
	}
	
	@RequestMapping(
			value = "/api/onlytheatres",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<BioskopPozoriste>> getPozoriste(){
		ArrayList<BioskopPozoriste> retVal = (ArrayList<BioskopPozoriste>)bioskopService.getAllTheatres();
		
		return new ResponseEntity<Collection<BioskopPozoriste>>(retVal, HttpStatus.OK);
		
	}
	
	@RequestMapping(
			value = "/api/cinnemas",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopPozoriste> newCinnema(BioskopDTO creatingCinema){
		bioskopService.create(creatingCinema.getBioskop());

		
		
		return null;
	}
}
