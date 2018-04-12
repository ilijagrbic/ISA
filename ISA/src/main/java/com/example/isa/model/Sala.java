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
	@JoinColumn(name = "bioskop")
	@JsonBackReference
	private BioskopPozoriste bioskop;

	public Sala() {
		super();
	}

	
	
	public Sala(String nazivBroj, int visina, int duzina, BioskopPozoriste bioskop) {
		super();
		this.nazivBroj = nazivBroj;
		this.visina = visina;
		this.duzina = duzina;
		this.bioskop = bioskop;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getNazivBroj() {
		return nazivBroj;
	}



	public void setNazivBroj(String nazivBroj) {
		this.nazivBroj = nazivBroj;
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



	public BioskopPozoriste getBioskop() {
		return bioskop;
	}



	public void setBioskop(BioskopPozoriste bioskop) {
		this.bioskop = bioskop;
	}


	
	
	
}
