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
	public ResponseEntity<Invite> sendFriendshipRequest(@PathVariable("userId") long userId, @PathVariable("friendId") long friendId) {
		System.out.println("\n\nSlanje zahteva");
		Invite friendshipInvite = friendshipService.createFriendshipRequest(userId,friendId);
		
		return new ResponseEntity<>(friendshipInvite, HttpStatus.CREATED);
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

	// Brisanje prijatelja
	@RequestMapping(value="/{userId}/delete/{friendId}",method=RequestMethod.DELETE)
	public ResponseEntity<Collection<User>> deleteFriend(@PathVariable("userId") long userId, @PathVariable("friendId") long friendId) {
		System.out.println("\n*** Brisanje prijatelja *** \n");
		ArrayList<User> friends = (ArrayList<User>)friendshipService.deleteFriend(friendId, userId);
		return new ResponseEntity<Collection<User>>(friends, HttpStatus.OK);
	}

}
