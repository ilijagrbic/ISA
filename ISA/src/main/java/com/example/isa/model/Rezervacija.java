package com.example.isa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.example.isa.model.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@org.hibernate.annotations.OptimisticLocking
public class Rezervacija {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Projekcija projekcija;
	
	@ManyToOne
	private User rezervant;
	
	private RezervacijaStatus status;

	@OneToOne
	private Sediste rezervisanoMesto;
	
	private int ocenaFilm;
	
	private int ocenaAmbijent;
		
	private Boolean isHost;
	
	private Long hostId;
	
	@Version
	private int version;

	@ManyToOne
	private MovieShow film;

	public Rezervacija(long id, Projekcija projekcija, User rezervant, RezervacijaStatus status,
			Sediste rezervisanoMesto, int ocenaFilm, int ocenaAmbijent, Boolean isHost, Long hostId, int version) {
		super();
		this.id = id;
		this.projekcija = projekcija;
		this.rezervant = rezervant;
		this.status = status;
		this.rezervisanoMesto = rezervisanoMesto;
		this.ocenaFilm = ocenaFilm;
		this.ocenaAmbijent = ocenaAmbijent;
		this.isHost = isHost;
		this.hostId = hostId;
		this.version = version;
	}

	public Rezervacija() {
		super();
	}
	
	

	public MovieShow getFilm() {
		return film;
	}

	public void setFilm(MovieShow film) {
		this.film = film;
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

	public User getRezervant() {
		return rezervant;
	}

	public void setRezervant(User rezervant) {
		this.rezervant = rezervant;
	}

	public RezervacijaStatus getStatus() {
		return status;
	}

	public void setStatus(RezervacijaStatus status) {
		this.status = status;
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

	public Boolean getIsHost() {
		return isHost;
	}

	public void setIsHost(Boolean isHost) {
		this.isHost = isHost;
	}

	public Long getHostId() {
		return hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	

	
	
	
}
