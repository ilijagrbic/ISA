package com.example.isa.controller.dataTransfer;

import java.util.ArrayList;
import java.util.Date;

import com.example.isa.model.MovieShow;
import com.example.isa.model.Projekcija;
import com.example.isa.model.Sala;
import com.example.isa.model.Sediste;

public class ProjekcijaDTO {

    private Date date;

	private Sala sala;
	
	private double cena;
	
	//private List<Sediste> sedista;

	private Long film;
	
	public Projekcija getProjekcija() {
		Projekcija retVal = new Projekcija();
		retVal.setDate(date);
		retVal.setSala(sala);
		retVal.setCena(cena);
		retVal.setSedista(new ArrayList<Sediste>());
		
		return retVal;
	}
	
	

	public ProjekcijaDTO() {
		super();
	}
	
	

	public ProjekcijaDTO(Date date, Sala sala, double cena, Long film) {
		super();
		this.date = date;
		this.sala = sala;
		this.cena = cena;
		this.film = film;
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

	public Long getFilm() {
		return film;
	}

	public void setFilm(Long film) {
		this.film = film;
	}
	
	
	
	
}
