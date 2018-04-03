package com.example.isa.model;

import javax.persistence.*;

@Entity
public class Sala {

	@Id
	@GeneratedValue
	private long id;
	
	private int visina;
	
	private int duzina;

	public Sala() {
		super();
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
