package com.example.isa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.isa.model.users.User;


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
	private User host;
	
	private int status;

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

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}
	
	
	
}
