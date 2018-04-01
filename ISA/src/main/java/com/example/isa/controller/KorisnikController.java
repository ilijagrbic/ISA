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

import com.example.isa.model.users.AdmUser;
import com.example.isa.model.users.RegUser;
import com.example.isa.model.users.User;
import com.example.isa.service.AdminService;
import com.example.isa.service.KorisnikService;

@RestController
public class KorisnikController {
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private AdminService adminService;
	
	// Vracanje registrovanog korisnika
	@RequestMapping(
			value = "/api/users/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity findRegKorisnik(@PathVariable("id") Long id){
		RegUser regUser = korisnikService.findById(id);
		
		if(regUser==null) {
			AdmUser admUser = adminService.findById(id);
			return new ResponseEntity(admUser, HttpStatus.OK);
		}
		else {
			return new ResponseEntity(regUser, HttpStatus.OK);
		}
	}
	
	// Azuriranje podataka korisnika
	@RequestMapping(
			value = "/api/users/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateRegKorsinik(@PathVariable long id, @RequestBody User user) {
		
		RegUser updatedUser = korisnikService.updateUser(id, user);
		if(updatedUser == null) {
			return new ResponseEntity<String>("Nije uspeo update", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<RegUser>(updatedUser, HttpStatus.OK);
		}
	}
	
	// Vracanje svih (obicnih) registrovanih korisnika - mozda ce trebati i admina
	@RequestMapping(
			value = "/api/users",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RegUser>> getRegKorisnici() {
		ArrayList<RegUser> regUsers = (ArrayList<RegUser>)korisnikService.findAll();
		return new ResponseEntity<Collection<RegUser>>(regUsers, HttpStatus.OK);
	}
	
}
