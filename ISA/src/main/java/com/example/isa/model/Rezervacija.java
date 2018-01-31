package com.example.isa.model;

import java.util.List;

import javax.persistence.*;

import com.example.isa.model.users.RegUser;

@Entity
public class Rezervacija {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Projekcija projekcija;
	
	@OneToMany
	private List<UserMesto> sviPozvani;
	
	@ManyToOne
	private RegUser host;

	public Rezervacija() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Projekcija getProjekcija() {
		return projekcija;
	}

	public void setProjekcija(Projekcija projekcija) {
		this.projekcija = projekcija;
	}

	public List<UserMesto> getSviPozvani() {
		return sviPozvani;
	}

	public void setSviPozvani(List<UserMesto> sviPozvani) {
		this.sviPozvani = sviPozvani;
	}

	public RegUser getHost() {
		return host;
	}

	public void setHost(RegUser host) {
		this.host = host;
	}
	
	
	
}
