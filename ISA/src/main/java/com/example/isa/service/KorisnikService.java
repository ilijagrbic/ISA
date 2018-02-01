package com.example.isa.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.isa.model.users.RegUser;
import com.example.isa.model.users.User;
import com.example.isa.repository.RegUserRepository;

@Service
public class KorisnikService {
	@Autowired
	private RegUserRepository regUserRepository;
	
	
	public List<RegUser> findAll(){
		return regUserRepository.findAll();
	}
	
	public RegUser findById(long id) {
		return regUserRepository.findById(id);
	}
	
	public RegUser createNewUser(RegUser regUser) {
		return regUserRepository.save(regUser);
	}
	
}
