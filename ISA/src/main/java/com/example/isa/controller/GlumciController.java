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
import com.example.isa.model.Glumac;
import com.example.isa.service.GlumciService;

@RestController
public class GlumciController {

	@Autowired
	GlumciService glumciService;
	
	@RequestMapping(
			value = "/api/actors",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Glumac>> getBioskopPozoriste(){
		ArrayList<Glumac> retVal = (ArrayList<Glumac>)glumciService.getAll();
		
		return new ResponseEntity<Collection<Glumac>>(retVal, HttpStatus.OK);
		
	}
	
	@RequestMapping(
			value = "/api/actors/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Glumac> getBioskopPozoristeById(@PathVariable("id") Long id){
		Glumac retVal = (Glumac)glumciService.getById(id);
		
		return new ResponseEntity<Glumac>(retVal, HttpStatus.OK);
		
	}
	
	@RequestMapping(
			value = "/api/actors",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> newCinnema(@RequestBody Glumac creatingGlumac){
		if(creatingGlumac.getIme()==null||creatingGlumac.getPrezime()==null||creatingGlumac.getIme().isEmpty()||creatingGlumac.getIme().equals("")
				||creatingGlumac.getPrezime().isEmpty()||creatingGlumac.getPrezime().equals("")
				) {
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Ime ili prezime ne smeju biti prazni"), HttpStatus.BAD_REQUEST);
		}
		else {
			Glumac retVal = glumciService.create(new Glumac(creatingGlumac.getIme(), creatingGlumac.getPrezime()));
			
			if(retVal!=null) {
				return new ResponseEntity<Glumac>(retVal, HttpStatus.OK);
			}else {
				return new ResponseEntity<Glumac>(retVal, HttpStatus.BAD_REQUEST);
			}
		}

	}
	
	@RequestMapping(
			value = "/api/actors/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCinnema(@RequestBody Glumac creatingGlumac, @PathVariable("id") Long id){		
		if(creatingGlumac.getIme()==null||creatingGlumac.getPrezime()==null||creatingGlumac.getIme().isEmpty()||creatingGlumac.getIme().equals("")
				||creatingGlumac.getPrezime().isEmpty()||creatingGlumac.getPrezime().equals("")
				) {
			return new ResponseEntity<AlertMessageDTO>(new AlertMessageDTO("Ime ili prezime ne smeju biti prazni"), HttpStatus.BAD_REQUEST);
		}
		else {
			Glumac retVal = glumciService.update(creatingGlumac);
			if(retVal!=null) {
				return new ResponseEntity<Glumac>(retVal, HttpStatus.OK);
			}else {
				return new ResponseEntity<Glumac>(retVal, HttpStatus.BAD_REQUEST);
			}
		}
	}
	
}
