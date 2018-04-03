package com.example.isa.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.Rezervacija;
import com.example.isa.model.UserMesto;
import com.example.isa.model.users.RegUser;
import com.example.isa.model.users.User;
import com.example.isa.repository.RegUserRepository;
import com.example.isa.repository.RezervacijaRepository;

@Service
public class KorisnikService {
	@Autowired
	private RegUserRepository regUserRepository;
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	public List<RegUser> findAll(){
		return regUserRepository.findAll();
	}
	
	public RegUser findById(long id) {
		return regUserRepository.findById(id);
	}
	
	public RegUser createNewUser(RegUser regUser) {
		return regUserRepository.save(regUser);
	}
	
	public RegUser updateUser(long id, User user) {
		RegUser toUpdate = regUserRepository.findById(id);
		toUpdate.setCity(user.getCity());
		toUpdate.setEmail(user.getEmail());
		toUpdate.setName(user.getName());
		toUpdate.setPassword(user.getPassword());
		toUpdate.setPhone(user.getPhone());;
		toUpdate.setSurname(user.getSurname());
		return toUpdate;
	}
	
	public List<Rezervacija> getReservations(User user){
		RegUser regUser = regUserRepository.findById(user.getId());
		return regUser.getRezervacije();
	}
	
	public List<UserMesto> getTickets(User user){
		RegUser regUser = regUserRepository.findById(user.getId());
		return regUser.getPozivi();
	}
	
	public void cancelReservation(Long id, User user) {
		//RegUser regUser = regUserRepository.findById(user.getId());
		//Rezervacija reservation = rezervacijaRepository.getOne(id);
		// Da li se brise i u jednom i u drugom?	
		rezervacijaRepository.delete(id);
	}
	
	
	
}
