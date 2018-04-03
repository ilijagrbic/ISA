package com.example.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa.model.users.AdmUser;
import com.example.isa.repository.AdmUserRepository;

@Service
public class AdminService {

	@Autowired
	private AdmUserRepository admUserRepository;
	
	
	public List<AdmUser> findAll(){
		return admUserRepository.findAll();
	}
	
	public AdmUser findById(long id) {
		return admUserRepository.findById(id);
	}
	
}
