package com.example.isa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Sala {

	@Id
	@GeneratedValue
	private long id;
	
	private String nazivBroj;
	
	private int visina;
	
	private int duzina;
	
	@ManyToOne()
	@JoinColumn(name = "sale")
	@JsonBackReference
	private BioskopPozoriste parentCinema;

	public Sala() {
		super();
	}

	
	
	public String getNazivBroj() {
		return nazivBroj;
	}



	public void setNazivBroj(String nazivBroj) {
		this.nazivBroj = nazivBroj;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVisina() {
		return visina;
	}

	public void setVisina(int visina) {
		this.visina = visina;
	}

	public int getDuzina() {
		return duzina;
	}

	public void setDuzina(int duzina) {
		this.duzina = duzina;
	}
	
	
	
}
