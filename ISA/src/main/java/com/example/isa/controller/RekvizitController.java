package com.example.isa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.model.Rekviziti;
import com.example.isa.model.VrstaRekvizita;
import com.example.isa.service.RekvizitiService;


@RestController
public class RekvizitController {
	
	@Autowired
	RekvizitiService rekvizitiService;

	@RequestMapping(method=RequestMethod.GET, value="api/rekvizit/zvanicni")
	public ResponseEntity<List<Rekviziti>> getRekvizitiZvanicni(){
		List<Rekviziti> rekviziti = rekvizitiService.getAllZvanicna();
		return new ResponseEntity<>(rekviziti, HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="api/rekvizit/polovni")
	public ResponseEntity<List<Rekviziti>> getRekvizitiPolovni(){
		List<Rekviziti> rekviziti = rekvizitiService.getAllPolovna();
		return new ResponseEntity<>(rekviziti, HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="api/rekvizit/zvanicni")
	public ResponseEntity<List<Rekviziti>> saveRekvizitZvanicni(@RequestBody Rekviziti rekvizit){
		rekvizit.setVrsta(VrstaRekvizita.ZVANICNI);
		rekvizitiService.save(rekvizit);
		List<Rekviziti> rekviziti = rekvizitiService.getAllZvanicna();
		return new ResponseEntity<>(rekviziti, HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="api/rekvizit/polovni")
	public ResponseEntity<List<Rekviziti>> saveRekvizitPolovni(@RequestBody Rekviziti rekvizit){
		rekvizit.setVrsta(VrstaRekvizita.POLOVNI);
		rekvizitiService.save(rekvizit);
		List<Rekviziti> rekviziti = rekvizitiService.getAllPolovna();
		return new ResponseEntity<>(rekviziti, HttpStatus.OK);
		
	}
}
