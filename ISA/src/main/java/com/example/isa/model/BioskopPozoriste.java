package com.example.isa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.example.isa.model.users.AdmUser;

@Entity
public class BioskopPozoriste {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	private String description;
	
	private String address;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private BioskopPozoristeType type;
	
	@OneToOne
	private Repertoire repertoire;
	
	//@OneToMany
	//Promotivne karte
	
	@ManyToMany
	private List<AdmUser> admini;
	
	public BioskopPozoriste() {
		
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
	
	
	
}
