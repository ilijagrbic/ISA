package com.example.isa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.isa.model.users.RegUser;

@Entity
public class UserMesto {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private RegUser rezervant;
	
	@ManyToOne
	private Sediste rezervisanoMesto;
	
	private int ocenaFilm;
	
	private int ocenaAmbijent;
	
	private boolean accepted;
	
	@ManyToOne
	private  Rezervacija rezervacija;
	
	private int isHost;

	public UserMesto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RegUser getRezervant() {
		return rezervant;
	}

	public void setRezervant(RegUser rezervant) {
		this.rezervant = rezervant;
	}

	public Sediste getRezervisanoMesto() {
		return rezervisanoMesto;
	}

	public void setRezervisanoMesto(Sediste rezervisanoMesto) {
		this.rezervisanoMesto = rezervisanoMesto;
	}

	public int getOcenaFilm() {
		return ocenaFilm;
	}

	public void setOcenaFilm(int ocenaFilm) {
		this.ocenaFilm = ocenaFilm;
	}

	public int getOcenaAmbijent() {
		return ocenaAmbijent;
	}

	public void setOcenaAmbijent(int ocenaAmbijent) {
		this.ocenaAmbijent = ocenaAmbijent;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public Rezervacija getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

	public int getIsHost() {
		return isHost;
	}

	public void setIsHost(int isHost) {
		this.isHost = isHost;
	}
	
	
	
	
}
