package com.example.isa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.controller.dataTransfer.LoginDTO;
import com.example.isa.model.users.RegUser;
import com.example.isa.model.users.User;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.KorisnikService;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private KorisnikService korisnikService;

	@RequestMapping(
			value = "/api/signin",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> signin(@RequestBody LoginDTO loginDTO) {
		User user = authenticationService.findRegUser(loginDTO);

		if (!user.isActiaved()) {
			return new ResponseEntity<User>(user, HttpStatus.UNAUTHORIZED);
		}

		// Ovde mi mozda nedostaje neki deo!
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	// U nekom delu treba dodati za verifikaciju maila
	
	
	// Odjavljivanje
	@RequestMapping(
			value = "/api/signout",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> signout() {
		SecurityContextHolder.clearContext();
        return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	// Registracija korisnika
	// Polazim kroz obicne korisnike
	// Registracija admina se ovde ne radi - to radi predefinisani ADMIN
	@RequestMapping(
			value = "api/regin",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity regIn(@RequestBody RegUser regUser){
		// Mora da proveri u adminima i u obicnim korisnicima
		User foundUser = authenticationService.findUser(regUser);
		
		if(foundUser==null) {
			RegUser addedUser = korisnikService.createNewUser(regUser);
			return new ResponseEntity<RegUser>(addedUser, HttpStatus.OK);
		}
		
		// Bolje da baca mozda exception
		return new ResponseEntity<String>("Korisnik vec postoji", HttpStatus.OK);
	}

}
