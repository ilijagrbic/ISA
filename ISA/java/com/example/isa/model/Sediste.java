package com.example.isa.model;

import javax.persistence.*;

@Entity
public class Sediste {

	@Id
	@GeneratedValue
	private long id;
	
	private int visKord;
	
	private int duzKord;
	
	private SedisteType type;
	
	private double deltaCena;

	public Sediste() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVisKord() {
		return visKord;
	}

	public void setVisKord(int visKord) {
		this.visKord = visKord;
	}

	public int getDuzKord() {
		return duzKord;
	}

	public void setDuzKord(int duzKord) {
		this.duzKord = duzKord;
	}

	public SedisteType getType() {
		return type;
	}

	public void setType(SedisteType type) {
		this.type = type;
	}

	public double getDeltaCena() {
		return deltaCena;
	}

	public void setDeltaCena(double deltaCena) {
		this.deltaCena = deltaCena;
	}
	
	
	
	
}
