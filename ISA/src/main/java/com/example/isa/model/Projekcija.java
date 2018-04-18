package com.example.isa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Projekcija {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
    private Date date;
	
	@ManyToOne
	private Sala sala;
	
	private double cena;
	
	@OneToMany(fetch = FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Sediste> sedista;
	
	@ManyToOne
	@JsonBackReference
	private MovieShow film;

	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Rezervacija> rezervacije;
	
	public Projekcija() {
		super();
	}

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public List<Sediste> getSedista() {
		return sedista;
	}

	public void setSedista(List<Sediste> sedista) {
		this.sedista = sedista;
	}

	public MovieShow getFilm() {
		return film;
	}

	public void setFilm(MovieShow film) {
		this.film = film;
	}
		
	
	
	
}
