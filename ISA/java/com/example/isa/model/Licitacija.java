package com.example.isa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.isa.model.users.User;

@Entity
public class Licitacija {

	@Id
	@GeneratedValue
	private long id;
	
	private double price;
	
	private LicitacijaStatus status;
	
	private boolean notified;
	
	@ManyToOne
	private Rekviziti licitirano;
	
	@ManyToOne
	private User ponudjac;

	public Licitacija() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LicitacijaStatus getStatus() {
		return status;
	}

	public void setStatus(LicitacijaStatus status) {
		this.status = status;
	}

	public boolean isNotified() {
		return notified;
	}

	public void setNotified(boolean notified) {
		this.notified = notified;
	}

	public Rekviziti getLicitirano() {
		return licitirano;
	}

	public void setLicitirano(Rekviziti licitirano) {
		this.licitirano = licitirano;
	}

	public User getPonudjac() {
		return ponudjac;
	}

	public void setPonudjac(User ponudjac) {
		this.ponudjac = ponudjac;
	}
	
	
	
	
}
