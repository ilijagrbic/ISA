package com.example.isa;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IsaApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(IsaApplication.class, args);
		System.out.println("Hello GIT :D"); //commit testing
	}
}
