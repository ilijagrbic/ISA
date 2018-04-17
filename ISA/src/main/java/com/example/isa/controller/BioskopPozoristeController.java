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
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopPozoriste> newCinnema(@RequestBody BioskopDTO creatingCinema){
		
		BioskopPozoriste retVal = bioskopService.create(creatingCinema.getBioskop());
		if(retVal!=null) {
			return new ResponseEntity<BioskopPozoriste>(retVal, HttpStatus.OK);
		}else {
			return new ResponseEntity<BioskopPozoriste>(retVal, HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(
			value = "/api/cinnemas/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopPozoriste> updateCinnema(@RequestBody BioskopDTO updateCinema, @PathVariable("id") Long id){		
		updateCinema.setId(id);
		
		BioskopPozoriste temp = updateCinema.getBioskop();
		temp.setId(id);
		BioskopPozoriste retVal = bioskopService.update(temp);
		if(retVal!=null) {
			return new ResponseEntity<BioskopPozoriste>(retVal, HttpStatus.OK);
		}else {
			return new ResponseEntity<BioskopPozoriste>(retVal, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(
			value = "/api/cinnemas/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopPozoriste> getBioskopPozoristee(@PathVariable("id") Long id){
		
		BioskopPozoriste retVal = bioskopService.getById(id);
		
		return new ResponseEntity<BioskopPozoriste>(retVal, HttpStatus.OK);
		
	}
	
	// Pretraga po imenu
		@RequestMapping(value="/api/findCinemaTheatreByName/{toFind}", method=RequestMethod.GET, produces="application/json")
		public ResponseEntity<Collection<BioskopPozoriste>> findBioskopPozoriste(@PathVariable("toFind") String toFind){
			System.out.println("\n***Pretraga po imenu \n" + toFind);
			ArrayList<BioskopPozoriste> bioskopPozoriste = null;
			if(toFind.equals("undefined")) {
				bioskopPozoriste = (ArrayList<BioskopPozoriste>)bioskopService.getAll();
			}
			else {
				bioskopPozoriste = (ArrayList<BioskopPozoriste>) bioskopService.findCinemaTheatreByName(toFind); 
			}
			return new ResponseEntity<Collection<BioskopPozoriste>>(bioskopPozoriste, HttpStatus.OK);
		}
		

	
	
	
	/*
	 * DELETE metoda nije uradjena za bioskop, meni jos nije trebalo, ako nekom treba, nek doda dole ili nek mi javi
	 */
}
