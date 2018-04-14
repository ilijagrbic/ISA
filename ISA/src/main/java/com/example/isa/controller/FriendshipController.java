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

import com.example.isa.controller.dataTransfer.InviteDTO;
import com.example.isa.model.users.Invite;
import com.example.isa.model.users.User;
import com.example.isa.service.AuthenticationService;
import com.example.isa.service.FriendshipService;

@RestController
@RequestMapping(value = "/api/friendships")
public class FriendshipController {

	@Autowired
	private FriendshipService friendshipService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	// Slanje zahteva za prijateljstvo 
	@RequestMapping(value="/{userId}/send/{friendId}",method=RequestMethod.POST)
	public ResponseEntity<Collection<User>> sendFriendshipRequest(@PathVariable("userId") long userId, @PathVariable("friendId") long friendId) {
		System.out.println("\n\n***Slanje zahteva");
		ArrayList<User> friendshipInvite = (ArrayList<User>) friendshipService.createFriendshipRequest(userId,friendId);
		
		return new ResponseEntity<Collection<User>>(friendshipInvite, HttpStatus.CREATED);
	}
	
	
	// Prihvanje zahteva za prijateljstvo
	@RequestMapping(value="/{userId}/accept/{friendId}",method=RequestMethod.PUT)
	public ResponseEntity<Collection<User>> acceptFriendshipRequest(@PathVariable("userId") long userId, @PathVariable("friendId") long friendId) {
		System.out.println("\n Prihvatanje zahteva od strane " + friendId);
		ArrayList<User> invitationFromFriends = (ArrayList<User>)friendshipService.acceptFriendshipRequest(friendId, userId);
		if(invitationFromFriends!=null){
			return new ResponseEntity<Collection<User>>(invitationFromFriends, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

		// Odbijanje zahteva za prijateljstvo 
		@RequestMapping(value="/{userId}/cancel/{friendId}",method=RequestMethod.DELETE)
		public ResponseEntity<Collection<User>> cancelFriendshipRequest(@PathVariable("userId") long userId, @PathVariable("friendId") long friendId) {
			System.out.println("\n Odbijanje zahteva za prijateljstvo  " + friendId);
			ArrayList<User> cancelInvitation = (ArrayList<User>)friendshipService.cancelFriendshipRequest(friendId, userId);
			if(cancelInvitation!=null){
				return new ResponseEntity<Collection<User>>(cancelInvitation, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}

	// Brisanje prijatelja
	@RequestMapping(value="/{userId}/delete/{friendId}",method=RequestMethod.DELETE)
	public ResponseEntity<Collection<User>> deleteFriend(@PathVariable("userId") long userId, @PathVariable("friendId") long friendId) {
		System.out.println("\n*** Brisanje prijatelja *** \n");
		ArrayList<User> friends = (ArrayList<User>)friendshipService.deleteFriend(friendId, userId);
		return new ResponseEntity<Collection<User>>(friends, HttpStatus.OK);
	}

}
