package com.example.isa.model;

import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.isa.model.users.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class BioskopPozoriste {

	//dodaj sliku ovde
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	private String description;
	
	private String address;
	
	private String gmapsUrl;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private BioskopPozoristeType type;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "repertoar")
	@JsonManagedReference
	private Repertoire repertoire;
	
	private HashMap<Integer, Sediste> promotivneKarte;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bioskop")
	private List<Sala> sale;
	
	@ManyToMany
	private List<User> admini;
	
	private int bronzeTreshold;
	
	private int silverTreshold;
	
	private int goldTreshold;
	
	private int bronzeSale;
	
	private int silverSale;
	
	private int goldSale;

	public BioskopPozoriste() {
		
	}
	
	public BioskopPozoriste update(BioskopPozoriste updated) {
		this.name=updated.getName();
		this.description=updated.getDescription();
		this.address=updated.getAddress();
		this.type=updated.getType();
		this.bronzeTreshold=updated.getBronzeTreshold();
		this.silverTreshold=updated.getSilverTreshold();
		this.goldTreshold=updated.getGoldTreshold();
		
		return this;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BioskopPozoristeType getType() {
		return type;
	}

	public void setType(BioskopPozoristeType type) {
		this.type = type;
	}

	public Repertoire getRepertoire() {
		return repertoire;
	}

	public void setRepertoire(Repertoire repertoire) {
		this.repertoire = repertoire;
	}

	public List<User> getAdmini() {
		return admini;
	}

	public void setAdmini(List<User> admini) {
		this.admini = admini;
	}

	public int getBronzeTreshold() {
		return bronzeTreshold;
	}

	public void setBronzeTreshold(int bronzeTreshold) {
		this.bronzeTreshold = bronzeTreshold;
	}

	public int getSilverTreshold() {
		return silverTreshold;
	}

	public void setSilverTreshold(int silverTreshold) {
		this.silverTreshold = silverTreshold;
	}

	public int getGoldTreshold() {
		return goldTreshold;
	}

	public void setGoldTreshold(int goldTreshold) {
		this.goldTreshold = goldTreshold;
	}

	public String getGmapsUrl() {
		return gmapsUrl;
	}

	public void setGmapsUrl(String gmapsUrl) {
		this.gmapsUrl = gmapsUrl;
	}

	public HashMap<Integer, Sediste> getPromotivneKarte() {
		return promotivneKarte;
	}

	public void setPromotivneKarte(HashMap<Integer, Sediste> promotivneKarte) {
		this.promotivneKarte = promotivneKarte;
	}

	public List<Sala> getSale() {
		return sale;
	}

	public void setSale(List<Sala> sale) {
		this.sale = sale;
	}

	public int getBronzeSale() {
		return bronzeSale;
	}

	public void setBronzeSale(int bronzeSale) {
		this.bronzeSale = bronzeSale;
	}

	public int getSilverSale() {
		return silverSale;
	}

	public void setSilverSale(int silverSale) {
		this.silverSale = silverSale;
	}

	public int getGoldSale() {
		return goldSale;
	}

	public void setGoldSale(int goldSale) {
		this.goldSale = goldSale;
	}
	
	
	
}
