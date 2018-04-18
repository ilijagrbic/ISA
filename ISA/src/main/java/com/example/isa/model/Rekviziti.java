package com.example.isa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	
	private String naziv;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private VrstaRekvizita vrsta;
	
	@ManyToOne
	private BioskopPozoriste proadjeu;
	
	@ManyToOne
	private User postavljac;
	
	private Date finalDate;

	public Rekviziti() {
		super();
	}
	
	

	public Rekviziti(long id, boolean odobren, String naziv, VrstaRekvizita vrsta, BioskopPozoriste proadjeu,
			User postavljac, Date finalDate) {
		super();
		this.id = id;
		this.odobren = odobren;
		this.naziv = naziv;
		this.vrsta = vrsta;
		this.proadjeu = proadjeu;
		this.postavljac = postavljac;
		this.finalDate = finalDate;
	}



	public Rekviziti(long id, boolean odobren, String naziv, BioskopPozoriste proadjeu, User postavljac,
			Date finalDate) {
		super();
		this.id = id;
		this.odobren = odobren;
		this.naziv = naziv;
		this.proadjeu = proadjeu;
		this.postavljac = postavljac;
		this.finalDate = finalDate;
	}

	
	


	public VrstaRekvizita getVrsta() {
		return vrsta;
	}



	public void setVrsta(VrstaRekvizita vrsta) {
		this.vrsta = vrsta;
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



	public String getNaziv() {
		return naziv;
	}



	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	
	
	
}
