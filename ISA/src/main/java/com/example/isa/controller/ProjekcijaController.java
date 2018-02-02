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

import com.example.isa.controller.dataTransfer.ProjekcijaDTO;
import com.example.isa.model.Projekcija;
import com.example.isa.service.ProjekcijaService;

@RestController
public class ProjekcijaController {

	@Autowired
	private ProjekcijaService projService;
	
	@RequestMapping(
			value = "/api/cinnemas/{id}/projections",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Projekcija>> getProjecions(@PathVariable("id") Long id){
		ArrayList<Projekcija> retVal = (ArrayList<Projekcija>)projService.findAllInCinema(id);
		
		if(retVal!=null) {
			return new ResponseEntity<Collection<Projekcija>>(retVal, HttpStatus.OK);
		}else {
			return new ResponseEntity<Collection<Projekcija>>(retVal, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(
			value = "/api/projections/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Projekcija> delProjecions(@PathVariable("id") Long id){
		Projekcija retVal = projService.delete(id);
		
		if(retVal!=null) {
			return new ResponseEntity<Projekcija>(retVal, HttpStatus.OK);
		}else {
			return new ResponseEntity<Projekcija>(retVal, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(
			value = "/api/projections/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Projekcija> getProjecion(@PathVariable("id") Long id){
		Projekcija retVal = projService.findById(id);
		if(retVal!=null) {
			return new ResponseEntity<Projekcija>(retVal, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Projekcija>(retVal, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(
			value = "/api/projections/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Projekcija> delProjecions(@RequestBody ProjekcijaDTO createProjekcija, @PathVariable("id") Long id){
		Projekcija retVal = projService.create(createProjekcija.getProjekcija());
		
		if(retVal!=null) {
			return new ResponseEntity<Projekcija>(retVal, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Projekcija>(retVal, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(
			value = "/api/projections/",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Projekcija> updProjecions(@RequestBody ProjekcijaDTO createProjekcija, @PathVariable("id") Long id){
		Projekcija retVal = projService.update(createProjekcija.getProjekcija());
		
		if(retVal!=null) {
			return new ResponseEntity<Projekcija>(retVal, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Projekcija>(retVal, HttpStatus.BAD_REQUEST);
		}
	}
}
