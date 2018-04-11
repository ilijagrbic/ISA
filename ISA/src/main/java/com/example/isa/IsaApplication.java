package com.example.isa;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.isa.model.users.Role;
import com.example.isa.model.users.User;
import com.example.isa.service.KorisnikService;
import com.example.isa.service.StorageService;

@SpringBootApplication
public class IsaApplication implements CommandLineRunner{
	
	@Resource
	StorageService storageService;

	
	public static void main(String[] args) {
		SpringApplication.run(IsaApplication.class, args);
		System.out.println("Hello GIT :D"); //commit testing
	}
	
	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
		
	}
}