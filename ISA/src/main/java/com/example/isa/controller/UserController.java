package com.example.isa.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.controller.dataTransfer.ChangePasswordDTO;
import com.example.isa.model.users.User;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.FriendshipService;
import com.example.isa.service.UserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private FriendshipService friendshipService;

	// Vracanje registrovanog korisnika
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<User> findUser(@PathVariable Long id) {
		System.out.println("\n FIND USER:  " + id + "\n");
		User regUser = userService.findById(id);

		if (regUser != null) {
			return new ResponseEntity<User>(regUser, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Azuriranje podataka korisnika
	// Da li treba i admina
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		System.out.println("\n UPDATE USER:  " + id + "\n");
		User updatedUser = userService.updateUser(id, user);
		
		if (updatedUser != null) {
			return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<User>> findAllUsers() {
		ArrayList<User> users = (ArrayList<User>) userService.findAll();
		
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}

	// Prijatelji
	@RequestMapping(value = "/friends/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<User>> getFriends(@PathVariable Long id) {
		System.out.println("\n IZLISTAVANJE PRIJATELJA KORISNIKA " + id);
		ArrayList<User> friends = (ArrayList<User>) friendshipService.findFriends(id);
		return new ResponseEntity<Collection<User>>(friends, HttpStatus.OK);
	}

	// Osobe kojima mozemo da posaljemo zahtev
	@RequestMapping(value = "/nonFriends/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<User>> getNonFriends(@PathVariable Long id) {
		System.out.println("\n KOME MOZE DA POSALJE ZAHTEV, KORISNIK JE " + id);
		ArrayList<User> nonFriends = (ArrayList<User>) friendshipService.findNonFriends(id);
		return new ResponseEntity<Collection<User>>(nonFriends, HttpStatus.OK);
	}

	// Pronalazenje dobijenih zahteva za prijateljstvo
	@RequestMapping(value = "/findRequests/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<User>> getRequests(@PathVariable Long id) {
		System.out.println("\n PRONALAZI ZAHTEVE ZA PRIJATELJSTVO " + id);
		ArrayList<User> nonFriends = (ArrayList<User>) friendshipService.findFriendshipRequest(id);
		return new ResponseEntity<Collection<User>>(nonFriends, HttpStatus.OK);
	}
/*
	// Lista rezervacija
	@RequestMapping(value = "/getReservations", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Rezervacija>> getReservations() {
		User user = (User) authenticationService.getCurrentUser();
		ArrayList<Rezervacija> reservations = (ArrayList<Rezervacija>) userService.getReservations(user);
		return new ResponseEntity<Collection<Rezervacija>>(reservations, HttpStatus.OK);
	}

	// Lista karata
	@RequestMapping(value = "/getTickets", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<UserMesto>> getTickets() {
		User user = (User) authenticationService.getCurrentUser();
		ArrayList<UserMesto> tickets = (ArrayList<UserMesto>) userService.getTickets(user);
		return new ResponseEntity<Collection<UserMesto>>(tickets, HttpStatus.OK);
	}

	@RequestMapping(value = "/cancelReservation/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Rezervacija> cancelReservation(@PathVariable Long id) {
		User user = (User) authenticationService.getCurrentUser();
		// Treba da se doda deo za brisanje
		return new ResponseEntity<>(HttpStatus.OK);
	}
*/
	@RequestMapping(value = "/changePassword/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> updatePassword(@RequestBody ChangePasswordDTO changePasswordDTO,
			@PathVariable long id) {
		//User user = (User) SecurityContextHolder.getContext().getAuthentication();
		// Kako da proradi ovo sa getAuthent
		System.out.println("\nIZMENA SIFRE ZA KORISNIKA ID " + id);
		User updatedUser = userService.updatePassword(changePasswordDTO, id);
		if (updatedUser != null) {
			System.out.println("Promenjena je sifra - sada je "+ updatedUser.getPassword());
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
