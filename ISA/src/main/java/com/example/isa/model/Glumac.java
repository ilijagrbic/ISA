package com.example.isa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Glumac {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String ime;
	
	private String prezime;

	public Glumac() {
		super();
	}
	
	

	public Glumac(String ime, String prezime) {
		super();
		this.ime = ime;
		this.prezime = prezime;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	
	
}
