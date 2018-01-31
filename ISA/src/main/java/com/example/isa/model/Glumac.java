package com.example.isa.model;

import javax.persistence.*;

@Entity
public class Glumac {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String ime;
	
	private String prezime;

	public Glumac() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
