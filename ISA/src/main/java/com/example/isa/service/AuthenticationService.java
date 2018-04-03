package com.example.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.controller.dataTransfer.LoginDTO;
import com.example.isa.controller.dataTransfer.RegDTO;
import com.example.isa.model.users.RegUser;
import com.example.isa.model.users.User;
import com.example.isa.repository.AdmUserRepository;
import com.example.isa.repository.RegUserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private RegUserRepository regUserRepository;
	
	@Autowired
	private AdmUserRepository admUserRepository;
	
	public User findUser(RegUser regUser) {
		if((regUserRepository.findByEmailAndPassword(regUser.getEmail(), regUser.getPassword())==null)){
			return admUserRepository.findByEmailAndPassword(regUser.getEmail(), regUser.getPassword());
		}
		else {
			return regUserRepository.findByEmailAndPassword(regUser.getEmail(), regUser.getPassword());	
		}
	}

	// Verifikacija na osnovu maila
	 public User verifyUser(String verificationCode) {
	        RegUser regUser = regUserRepository.findByVerificationCode(verificationCode);
	        regUser.setActiaved(true);
	        regUserRepository.save(regUser);
	        return regUser;
	    }
	

}
