package com.example.isa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.service.FriendshipService;

@RestController
public class FriendshipController {

	@Autowired
	private FriendshipService friendshipService;
	
	
}
