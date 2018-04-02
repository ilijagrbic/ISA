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
import com.example.isa.controller.dataTransfer.RegDTO;
import com.example.isa.model.users.RegUser;
import com.example.isa.model.users.User;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.KorisnikService;

@RestController
@RequestMapping(value = "/api/")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private KorisnikService korisnikService;

	@RequestMapping(value="signin", method=RequestMethod.POST, consumes="application/json", produces="application/json") 
	public ResponseEntity<User> signin(@RequestBody LoginDTO loginDTO) {
		User user = authenticationService.findUser(loginDTO.getUserFromLogin(loginDTO));

		if(user!=null) {
			if (!user.isActiaved()) {
				return new ResponseEntity<User>(user, HttpStatus.UNAUTHORIZED);
			}
			else {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	// Odjavljivanje
	@RequestMapping(value="signout", method=RequestMethod.POST, consumes="application/json", produces="application/json") 
	public ResponseEntity<String> signout() {
		SecurityContextHolder.clearContext();
        return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	// Registracija korisni
	// Registracija admina se ovde ne radi - to radi predefinisani ADMIN
	@RequestMapping(value="regin", method=RequestMethod.POST, consumes="application/json", produces="application/json") 
	public ResponseEntity regIn(@RequestBody RegDTO regDTO){
		
		User user = authenticationService.findUser(regDTO.createRegUser(regDTO));
		
		if(user==null) {
			if(!regDTO.populatedFields()) {
				return new ResponseEntity<String>("Nisu popunjena polja!", HttpStatus.BAD_REQUEST); 
			}
			if(!regDTO.passwordMatch()){
				return new ResponseEntity<String>("Sifre se ne poklapaju!", HttpStatus.BAD_REQUEST);
			}
			RegUser addedUser = korisnikService.createNewUser(regDTO.createRegUser(regDTO));
			
			if(addedUser==null) {
				return new ResponseEntity<RegUser>(addedUser, HttpStatus.BAD_REQUEST);	
			}
			else {
				return new ResponseEntity<RegUser>(addedUser, HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<String>("Korisnik vec postoji", HttpStatus.OK);
	}

}
