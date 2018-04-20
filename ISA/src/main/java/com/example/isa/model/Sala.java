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
	private Long id;
	
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



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		if (duzina != other.duzina)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nazivBroj == null) {
			if (other.nazivBroj != null)
				return false;
		} else if (!nazivBroj.equals(other.nazivBroj))
			return false;
		if (visina != other.visina)
			return false;
		return true;
	}

	
	
	
	
}
