package com.example.isa.model;

import javax.persistence.*;

@Entity
public class MovieShow {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MovieShowType type;
	
	private String name;

	private int duration;
	
	private String description;
	
	//director
	
	//glumci
	
	//genre
	
	//manymany
	//sale
	
	//manymany
	//termini
	
	private double price;
	
	@ManyToOne()
	@JoinColumn(name = "repertoar")
	private Repertoire repertoar;

	public MovieShow() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MovieShowType getType() {
		return type;
	}

	public void setType(MovieShowType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
}
