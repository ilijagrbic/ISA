package com.example.isa.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.controller.dataTransfer.ChangePasswordDTO;
import com.example.isa.model.Rezervacija;
import com.example.isa.model.users.User;
import com.example.isa.repository.RezervacijaRepository;
import com.example.isa.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(long id) {
		return userRepository.findById(id);
	}
	
	public User createNewUser(User user) {
		return userRepository.save(user);
	}
	
	public User updateUser(long id, User user) {
		User toUpdate = userRepository.findById(id);
		toUpdate.setCity(user.getCity());
		toUpdate.setEmail(user.getEmail());
		toUpdate.setName(user.getName());
		toUpdate.setPassword(user.getPassword());
		toUpdate.setPhone(user.getPhone());;
		toUpdate.setSurname(user.getSurname());
		userRepository.save(toUpdate);
		return toUpdate;
	}
	
	public List<Rezervacija> getReservations(User user){
		User u = userRepository.findById(user.getId());
		return u.getRezervacije();
	}
	/*
	public List<UserMesto> getTickets(User user){
		User u = userRepository.findById(user.getId());
		return u.getPozivi();
	}*/
	
	public void cancelReservation(Long id, User user) {
		//RegUser regUser = regUserRepository.findById(user.getId());
		//Rezervacija reservation = rezervacijaRepository.getOne(id);
		// Da li se brise i u jednom i u drugom?	
		rezervacijaRepository.delete(id);
	}
	
	public User updatePassword(ChangePasswordDTO changePasswordDTO, Long id) {
		User currentUser = userRepository.findById(id);
		System.out.println("Korisnik kome menjamo sifru je " + currentUser.getName());
        if (!currentUser.getPassword().equals(changePasswordDTO.getOldPassword())) {
        	System.out.println("Nije ista stara sifra");
            return null;
        }
        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
        	System.out.println("Ne poklapaju se nove");
        	return null;
        }
        currentUser.setPassword(changePasswordDTO.getNewPassword());
        currentUser.setFirstTime(false);
 
        return userRepository.save(currentUser);
    }
}
