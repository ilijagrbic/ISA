package com.example.isa.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
	public ResponseEntity<?> getProjecions(@PathVariable("id") Long id){
		ArrayList<Projekcija> retVal = (ArrayList<Projekcija>)projService.findAllInCinema(id);
		
		if(retVal!=null) {
			return new ResponseEntity<Collection<Projekcija>>(retVal, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Ne postoji trazeno pozoriste/bioskop.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(
			value = "/api/cinnemas/{id}/movies/{idm}/projections",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProjecionsByMovie(@PathVariable("id") Long id, @PathVariable("idm") Long idMovie){
		ArrayList<Projekcija> retVal = (ArrayList<Projekcija>)projService.findAllInCinemaByMovie(id, idMovie);
		
		if(retVal!=null) {
			return new ResponseEntity<Collection<Projekcija>>(retVal, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Ne postoji trazeno pozoriste/bioskop ili film.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(
			value = "/api/projections/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delProjecions(@PathVariable("id") Long id){
		Projekcija retVal = projService.delete(id);
		
		if(retVal!=null) {
			return new ResponseEntity<Projekcija>(retVal, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Projekcija koju zelite da obrisete ne postoji u bazi.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(
			value = "/api/projections/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProjecion(@PathVariable("id") Long id){
		Projekcija retVal = projService.findById(id);
		if(retVal!=null) {
			return new ResponseEntity<Projekcija>(retVal, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Ne postoji trazena projekcija.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(
			value = "/api/projections/{id}/movie",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> getParent(@PathVariable("id") Long id){
		Long retVal = projService.findParentId(id);
		if(retVal!=null) {
			return new ResponseEntity<Long>(retVal, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Long>(retVal, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(
			value = "/api/projections/",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delProjecions(@RequestBody ProjekcijaDTO createProjekcija){
		Date today = new Date();
		if(createProjekcija.getCena()<1) {
			return new ResponseEntity<String>("Cena ne moze biti negativna.", HttpStatus.BAD_REQUEST);
		}else if(today.after(createProjekcija.getDate())){
			return new ResponseEntity<String>("Nemoguce definisati projekciju u proslosti.", HttpStatus.BAD_REQUEST);
		}else if(createProjekcija.getSala().getId()==null&&(createProjekcija.getSala().getVisina()<1||createProjekcija.getSala().getDuzina()<1)) {
			return new ResponseEntity<String>("Nova sala ne moze imati negativnu dimenziju.", HttpStatus.BAD_REQUEST);
		}
		else if(createProjekcija.getSala().getNazivBroj()==null||createProjekcija.getSala().getNazivBroj().isEmpty()||createProjekcija.getSala().getNazivBroj().equals("")) {
			return new ResponseEntity<String>("Naziv sale ne sme biti prazan", HttpStatus.BAD_REQUEST);
		}
		else {
			Projekcija retVal = projService.create(createProjekcija.getProjekcija(), createProjekcija.getFilm());
			
			if(retVal!=null) {
				return new ResponseEntity<Projekcija>(retVal, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("Ne postoji film za projekciju koju zelite.", HttpStatus.BAD_REQUEST);
			}
		}
	}

	@RequestMapping(
			value = "/api/projections/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updProjecions(@RequestBody ProjekcijaDTO createProjekcija, @PathVariable("id") Long id){
		Date today = new Date();
		if(createProjekcija.getCena()<1) {
			return new ResponseEntity<String>("Cena ne moze biti negativna.", HttpStatus.BAD_REQUEST);
		}else if(today.after(createProjekcija.getDate())){
			return new ResponseEntity<String>("Nemoguce definisati projekciju u proslosti.", HttpStatus.BAD_REQUEST);
		}else if(createProjekcija.getSala().getId()==null&&(createProjekcija.getSala().getVisina()<1||createProjekcija.getSala().getDuzina()<1)) {
			return new ResponseEntity<String>("Nova sala ne moze imati negativnu dimenziju.", HttpStatus.BAD_REQUEST);
		}
		else if(createProjekcija.getSala().getNazivBroj()==null||createProjekcija.getSala().getNazivBroj().isEmpty()||createProjekcija.getSala().getNazivBroj().equals("")) {
			return new ResponseEntity<String>("Naziv sale ne sme biti prazan", HttpStatus.BAD_REQUEST);
		}
		else {
			Projekcija temp = createProjekcija.getProjekcija();
			temp.setId(id);
			Projekcija retVal = projService.update(temp, createProjekcija.getFilm());
			
			if(retVal!=null) {
				return new ResponseEntity<Projekcija>(retVal, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("Ne postoji film ili projekcija koju zelite da azurirate.", HttpStatus.BAD_REQUEST);
			}
		}
	}
}
