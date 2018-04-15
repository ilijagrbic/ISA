package com.example.isa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.example.isa.model.users.User;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.MailService;
import com.example.isa.service.UserService;


@RestController
@RequestMapping(value = "/api/authentication")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private  MailService mailService;
	
	@Autowired
	private UserService korisnikService;
	
	@Value("${server.port}")
	private String port;
	// Aktivacija admina?
	@RequestMapping(value="signin", method=RequestMethod.POST, consumes="application/json", produces="application/json") 
	public ResponseEntity<User> signin(@RequestBody LoginDTO loginDTO) {
		System.out.println("Logovanje korisnika " + loginDTO.getEmail() + "," + loginDTO.getPassword());
		User user = authenticationService.findUser(loginDTO.getUserFromLogin(loginDTO));

		if(user!=null) {
			if (!user.isActiaved()) {
				System.out.println("Nije aktiviran");
				return new ResponseEntity<User>(user, HttpStatus.UNAUTHORIZED);
				
			}
			else {
				System.out.println("Aktiviran je");
				
				//authenticationService.setCurrentUser(user);
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		}
		System.out.println("Nije uspelo");
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
	@RequestMapping(value="signup", method=RequestMethod.POST, consumes="application/json", produces="application/json") 
	public ResponseEntity signup(@RequestBody RegDTO regDTO){
		
		User user = authenticationService.findUser(regDTO.createUser(regDTO));
		
		if(user==null) {
			if(!regDTO.populatedFields()) {
				return new ResponseEntity<String>("Nisu popunjena polja!", HttpStatus.BAD_REQUEST); 
			}
			if(!regDTO.passwordMatch()){
				return new ResponseEntity<String>("Sifre se ne poklapaju!", HttpStatus.BAD_REQUEST);
			}
			User addedUser = korisnikService.createNewUser(regDTO.createUser(regDTO));
			
			if(addedUser==null) {
				return new ResponseEntity<User>(addedUser, HttpStatus.BAD_REQUEST);	
			}
			else {
			
				System.out.println("**PORT\n"+port);
				mailService.sendVerificationMail("http://localhost:"+port+"/api/authentication/signup", addedUser.getVerificationCode(), addedUser.getEmail());
				return new ResponseEntity<User>(addedUser, HttpStatus.CREATED);
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
