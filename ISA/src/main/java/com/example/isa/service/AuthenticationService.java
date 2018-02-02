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
	
	public User findRegUser(LoginDTO loginDTO) {
		if((regUserRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword())==null)){
			return admUserRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
		}
		else {
			return regUserRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());	
		}
	}
	
	public User findUser(RegDTO regDTO) {
		if((regUserRepository.findByEmailAndPassword(regDTO.getEmail(), regDTO.getPassword())==null)){
			return admUserRepository.findByEmail(regDTO.getEmail());
		}
		else {
			return regUserRepository.findByEmail(regDTO.getEmail());	
		}
		
	}
	

}
