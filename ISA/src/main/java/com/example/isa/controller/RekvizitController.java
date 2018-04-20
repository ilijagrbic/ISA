package com.example.isa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa.model.Licitacija;
import com.example.isa.model.LicitacijaStatus;
import com.example.isa.model.RekvizitState;
import com.example.isa.model.Rekviziti;
import com.example.isa.model.VrstaRekvizita;
import com.example.isa.model.users.User;
import com.example.isa.service.LicitacijaService;
import com.example.isa.service.RekvizitiService;
import com.example.isa.service.UserService;


@RestController
public class RekvizitController {
	
	@Autowired
	RekvizitiService rekvizitiService;
	
	@Autowired
	LicitacijaService licitacijaService;
	
	@Autowired
	UserService userService;

	@RequestMapping(method=RequestMethod.GET, value="api/rekvizit/zvanicni")
	public ResponseEntity<List<Rekviziti>> getRekvizitiZvanicni(){
		List<Rekviziti> rekviziti = rekvizitiService.getAllZvanicna();
		return new ResponseEntity<>(rekviziti, HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="api/rekvizit/polovni")
	public ResponseEntity<List<Rekviziti>> getRekvizitiPolovni(){
		System.out.println("Dobijanje polovnih rekvizita");
		List<Rekviziti> rekviziti = rekvizitiService.getOdobreni();
		List<Rekviziti> rekviziti1 = new ArrayList<>();
		for (Rekviziti rekvizit : rekviziti) {
			if(rekvizit.getVrsta().equals(VrstaRekvizita.POLOVNI)) {
				rekviziti1.add(rekvizit);
			}
		}
		return new ResponseEntity<>(rekviziti1, HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="api/rekvizit/zvanicni")
	public ResponseEntity<List<Rekviziti>> saveRekvizitZvanicni(@RequestBody Rekviziti rekvizit){
		System.out.println("Cuvanje rekvizita");
		rekvizit.setVrsta(VrstaRekvizita.ZVANICNI);
		rekvizit.setOdobren(RekvizitState.PRIHVACEN);
		rekvizitiService.save(rekvizit);
		List<Rekviziti> rekviziti = rekvizitiService.getAllZvanicna();
		return new ResponseEntity<>(rekviziti, HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="api/rekvizit/polovni")
	public ResponseEntity<List<Rekviziti>> saveRekvizitPolovni(@RequestBody Rekviziti rekvizit){
		rekvizit.setVrsta(VrstaRekvizita.POLOVNI);
		rekvizit.setOdobren(RekvizitState.NEOBRADJEN);
		rekvizitiService.save(rekvizit);
		List<Rekviziti> rekviziti = rekvizitiService.getOdobreni();
		return new ResponseEntity<>(rekviziti, HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="api/rekvizit/licitacija")
	public ResponseEntity<List<Rekviziti>> savePonuda(@RequestBody Licitacija licitacija){
		licitacija.setStatus(LicitacijaStatus.NEW);
		licitacijaService.save(licitacija);
		List<Rekviziti> rekviziti = rekvizitiService.getAllPolovna();
		return new ResponseEntity<>(rekviziti, HttpStatus.OK);
		
	}
	@RequestMapping(method=RequestMethod.POST, value="api/rekvizit/licitacija/zvanicni")
	public ResponseEntity<List<Rekviziti>> savePonudaZvanicni(@RequestBody Licitacija licitacija){
		licitacijaService.save(licitacija);
		List<Rekviziti> rekviziti = rekvizitiService.getAllZvanicna();
		return new ResponseEntity<>(rekviziti, HttpStatus.OK);
		
	}

	
	@RequestMapping(method=RequestMethod.GET, value="api/rekvizit/{id}")
	public ResponseEntity<List<Rekviziti>> getUserRekviziti(@PathVariable long id){
		
		User user = userService.findById(id);

		List<Rekviziti> rekviziti = rekvizitiService.getUserRekviziti(user);
		return new ResponseEntity<>(rekviziti, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="api/rekvizit/ponude/{id}")
	public ResponseEntity<List<Licitacija>> getPonude(@PathVariable long id){
		
		Rekviziti rekvizit = rekvizitiService.findOne(id);

		
		List<Licitacija> licitacije = licitacijaService.findAll(rekvizit);
		return new ResponseEntity<>(licitacije, HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="api/rekvizit/ponude/neobradjeni")
	public ResponseEntity<List<Rekviziti>> getPonudeNeobradjene(){
		List<Rekviziti> rekviziti = rekvizitiService.getNeobredjeni();
		return new ResponseEntity<>(rekviziti, HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="api/rekvizit/licitacije/{id}")
	public ResponseEntity<List<Licitacija>> getPonudeNeobradjene(@PathVariable long id){
		User user = userService.findById(id);
		
		List<Licitacija> licitacije = licitacijaService.findAll(user);
		return new ResponseEntity<>(licitacije, HttpStatus.OK);
		
	}

	@RequestMapping(method=RequestMethod.POST, value="api/rekvizit/ponude/odobrenje/{id}")
	public ResponseEntity<List<Rekviziti>> odobrenje(@PathVariable long id){
		Rekviziti rekvizit = rekvizitiService.findOne(id);
		rekvizit.setOdobren(RekvizitState.PRIHVACEN);
		rekvizitiService.save(rekvizit);
		List<Rekviziti> rekviziti = rekvizitiService.getNeobredjeni();
		return new ResponseEntity<>(rekviziti, HttpStatus.OK);
		
	}
	@RequestMapping(method=RequestMethod.POST, value="api/rekvizit/ponude/odbijanje/{id}")
	public ResponseEntity<List<Rekviziti>> odbijanje(@PathVariable long id){
		Rekviziti rekvizit = rekvizitiService.findOne(id);
		rekvizit.setOdobren(RekvizitState.ODBIJEN);
		rekvizitiService.save(rekvizit);
		List<Rekviziti> rekviziti = rekvizitiService.getNeobredjeni();
		return new ResponseEntity<>(rekviziti, HttpStatus.OK);
		
	}
	@RequestMapping(method=RequestMethod.POST, value="api/rekvizit/ponude/prihvati")
	public ResponseEntity<List<Rekviziti>> prihvatiPonudu(@RequestBody Licitacija licitacija){
		List<Licitacija> licitacijeZaRekvizit = licitacijaService.findAll(licitacija.getRekvizit());
		for (Licitacija licitacija2 : licitacijeZaRekvizit) {
			if(licitacija2.getId() == licitacija.getId()) {
				System.out.println(licitacija.getId() + licitacija2.getId());
				licitacija.setStatus(LicitacijaStatus.ACCEPTED);
				licitacijaService.save(licitacija);
		}
			else {
				licitacija2.setStatus(LicitacijaStatus.DECLINED);
				licitacijaService.save(licitacija2);
			}

		
	}
		return new ResponseEntity<>( HttpStatus.OK);
	}
}


