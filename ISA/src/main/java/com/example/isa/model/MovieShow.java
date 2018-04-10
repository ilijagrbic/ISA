package com.example.isa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class MovieShow {
	
	
	private String image;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MovieShowType type;
	
	private String name;

	private int duration;
	
	private String description;
	
	private String director;
	
	@ManyToMany
	private List<Glumac> glumci;//proveri kako ide manymany
	
	private String genre;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Projekcija> projekcije;
	
	private double price;
	
	@ManyToOne()
	@JoinColumn(name = "repertoar")
	@JsonBackReference
	private Repertoire repertoar;

	private double avgReview;
		
	public MovieShow() {
		
	}

	public MovieShow update(MovieShow update) {
		this.image = update.getImage();
		this.type = update.getType();
		this.name = update.getName();
		this.duration = update.getDuration();
		this.description = update.getDescription();
		this.director = update.getDirector();
		//this.glumci = update.getGlumci();
		this.genre = update.getGenre();
		//this.projekcije = update.getProjekcije();
		this.price = update.getPrice();
		//this.repertoar = update.getRepertoar();
		return this;
	}

	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public double getAvgReview() {
		return avgReview;
	}



	public void setAvgReview(double avgReview) {
		this.avgReview = avgReview;
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

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public List<Glumac> getGlumci() {
		return glumci;
	}

	public void setGlumci(List<Glumac> glumci) {
		this.glumci = glumci;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<Projekcija> getProjekcije() {
		return projekcije;
	}

	public void setProjekcije(List<Projekcija> projekcije) {
		this.projekcije = projekcije;
	}

	public Repertoire getRepertoar() {
		return repertoar;
	}

	public void setRepertoar(Repertoire repertoar) {
		this.repertoar = repertoar;
	}
	
	
	
	
}
