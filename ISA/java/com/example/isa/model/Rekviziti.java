package com.example.isa.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.isa.model.users.User;

@Entity
public class Rekviziti {

	@Id
	@GeneratedValue
	private long id;
	
	private boolean odobren;
	
	@ManyToOne
	private BioskopPozoriste proadjeu;
	
	@ManyToOne
	private User postavljac;
	
	private Date finalDate;

	public Rekviziti() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}

	public BioskopPozoriste getProadjeu() {
		return proadjeu;
	}

	public void setProadjeu(BioskopPozoriste proadjeu) {
		this.proadjeu = proadjeu;
	}

	public User getPostavljac() {
		return postavljac;
	}

	public void setPostavljac(User postavljac) {
		this.postavljac = postavljac;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}
	
	
	
}
