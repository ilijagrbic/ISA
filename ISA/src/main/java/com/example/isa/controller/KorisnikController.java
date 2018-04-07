package com.example.isa.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.model.Rezervacija;
import com.example.isa.model.UserMesto;
import com.example.isa.model.users.User;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.FriendshipService;
import com.example.isa.service.KorisnikService;

@RestController
@RequestMapping(value = "/api/users")
public class KorisnikController {
	
	@Autowired
	private KorisnikService korisnikService;
	
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private FriendshipService friendshipService;
	
	
	// Vracanje registrovanog korisnika
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity<User> findRegKorisnik(@PathVariable Long id){
		User regUser = korisnikService.findById(id);
		
		// Admini su posebno
		if(regUser!=null) {
			/*AdmUser admUser = adminService.findById(id);
			if(admUser!=null) {
				return new ResponseEntity<User>(admUser, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}*/
			return new ResponseEntity<User>(regUser, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	// Azuriranje podataka korisnika
	// Da li treba i admina
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes="application/json", produces="application/json")
	public ResponseEntity<User> updateRegKorsinik(@PathVariable Long id, @RequestBody User user) {
		
		User updatedUser = korisnikService.updateUser(id, user);
		if(updatedUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
		}
	}
	
	// Vracanje svih (obicnih) registrovanih korisnika - mozda ce trebati i admina
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Collection<User>> getRegKorisnici() {
		ArrayList<User> regUsers = (ArrayList<User>)korisnikService.findAll();
		return new ResponseEntity<Collection<User>>(regUsers, HttpStatus.OK);
	}
	
	// Prijatelji
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value="/friends", method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<User>> getFriends() {
		User user = (User)authenticationService.getCurrentUser();
		ArrayList<User> friends = (ArrayList<User>)friendshipService.findFriends(user);
		return new ResponseEntity<Collection<User>>(friends, HttpStatus.OK);
	}
	
	// Osobe kojima mozemo da posaljemo zahtev
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value="/findNonFriends", method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<User>> getNonFriends() {
		User user = (User)authenticationService.getCurrentUser();
		ArrayList<User> nonFriends = (ArrayList<User>)friendshipService.findNonFriends(user);
		return new ResponseEntity<Collection<User>>(nonFriends, HttpStatus.OK);
	}
	
	
	// Pronalazenje dobijenih zahteva za prijateljstvo
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value="/findRequests", method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<User>> getRequests() {
		User user = (User)authenticationService.getCurrentUser();
		ArrayList<User> nonFriends = (ArrayList<User>)friendshipService.findFriendshipRequest(user);
		return new ResponseEntity<Collection<User>>(nonFriends, HttpStatus.OK);
	}
	
	
	// Lista rezervacija
	@RequestMapping(value="/getReservations", method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Rezervacija>> getReservations() {
		User user = (User)authenticationService.getCurrentUser();
		ArrayList<Rezervacija> reservations = (ArrayList<Rezervacija>)korisnikService.getReservations(user); 
		return new ResponseEntity<Collection<Rezervacija>>(reservations, HttpStatus.OK);
	}
	
	// Lista karata
	@RequestMapping(value="/getTickets", method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<UserMesto>> getTickets() {
		User user = (User)authenticationService.getCurrentUser();
		ArrayList<UserMesto> tickets = (ArrayList<UserMesto>) korisnikService.getTickets(user);
		return new ResponseEntity<Collection<UserMesto>>(tickets, HttpStatus.OK);
	}
	
	@RequestMapping(value="/cancelReservation/{id}", method=RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Rezervacija> cancelReservation(@PathVariable Long id) {
		User user = (User)authenticationService.getCurrentUser();
		 // Treba da se doda deo za brisanje
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}


