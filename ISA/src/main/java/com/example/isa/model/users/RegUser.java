package com.example.isa.model.users;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import com.example.isa.model.Rezervacija;
import com.example.isa.model.UserMesto;

@Entity
@Table(name="RegUser")
public class RegUser extends User{
	
	@OneToMany
	private List<UserMesto> pozivi;
	
	@OneToMany
	private List<Rezervacija> rezervacije;
	
	// Za verifikaciju
	private String verificationCode;
	
	private int bodovi;
	
	//rekviziti
	
	//rezervacije
	
	public RegUser() {
		super();
		this.verificationCode = UUID.randomUUID().toString();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<UserMesto> getPozivi() {
		return pozivi;
	}

	public void setPozivi(List<UserMesto> pozivi) {
		this.pozivi = pozivi;
	}

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	
	
	
}
