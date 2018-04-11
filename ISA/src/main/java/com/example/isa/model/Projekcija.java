package com.example.isa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Sediste> sedista;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonBackReference
	private MovieShow film;

	//mozda i rezervacija
	
	public Projekcija() {
		super();
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
