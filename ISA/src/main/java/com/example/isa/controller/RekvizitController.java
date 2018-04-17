package com.example.isa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.model.Rekviziti;
import com.example.isa.service.RekvizitiService;


@RestController
public class RekvizitController {
	
	@Autowired
	RekvizitiService rekvizitiService;

	@RequestMapping(method=RequestMethod.GET, value="api/rekvizit")
	public ResponseEntity<List<Rekviziti>> getRekviziti(){
		List<Rekviziti> rekviziti = rekvizitiService.findAll();
		return new ResponseEntity<>(rekviziti, HttpStatus.OK);
		
	}
}
