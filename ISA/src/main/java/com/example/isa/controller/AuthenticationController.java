package com.example.isa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.isa.controller.dataTransfer.LoginDTO;
import com.example.isa.controller.dataTransfer.RegDTO;
import com.example.isa.model.users.RegUser;
import com.example.isa.model.users.User;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.KorisnikService;
import com.example.isa.service.MailService;

@RestController
@RequestMapping(value = "/api/")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private  MailService mailService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	// Proveri da li treba jos nesto kod slanja maila
	//private HttpServletRequest request;
	    

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
				//mailService.sendVerificationMail(request.getRequestURL().toString(), addedUser.getVerificationCode(), addedUser.getEmail());
				return new ResponseEntity<RegUser>(addedUser, HttpStatus.CREATED);
			}
		}
		
		return new ResponseEntity<String>("Korisnik vec postoji", HttpStatus.OK);
	}
	
	// Proveri za front
	@RequestMapping(value="signup/{verificationCode}")
    public ModelAndView verify(@PathVariable String verificationCode) {
		authenticationService.verifyUser(verificationCode);
        return new ModelAndView(new RedirectView("/", true));
    }

}
