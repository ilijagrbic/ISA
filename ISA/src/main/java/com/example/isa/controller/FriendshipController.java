package com.example.isa.controller;

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
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value="/sendInvitation",method=RequestMethod.POST)
	public ResponseEntity<Invite> sendFriendshipRequest(@RequestBody InviteDTO inviteDTO) {
		Invite friendshipInvite = friendshipService.createFriendshipRequest(inviteDTO.getIdSender(),inviteDTO.getIdReceiver());
		
		return new ResponseEntity<>(friendshipInvite, HttpStatus.CREATED);
	}
	
	
	// Prihvanje zahteva za prijateljstvo
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value="/{senderId}",method=RequestMethod.PUT)
	public ResponseEntity<Invite> acceptFriendshipRequest(@PathVariable long senderId) {
		User receiver = authenticationService.getCurrentUser();
		Invite acceptedInvite = friendshipService.acceptFriendshipRequest(senderId, receiver.getId());
		if(acceptedInvite!=null){
			return new ResponseEntity<Invite>(acceptedInvite, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// Brisanje prijatelja
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value="/{senderId}",method=RequestMethod.DELETE)
	public ResponseEntity<Invite> deleteFriend(@PathVariable long senderId) {
		User receiver = authenticationService.getCurrentUser();
		friendshipService.deleteFriend(senderId, receiver.getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
