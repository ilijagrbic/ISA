package com.example.isa.controller.dataTransfer;

import java.util.ArrayList;
import java.util.List;

import com.example.isa.model.Glumac;
import com.example.isa.model.MovieShow;
import com.example.isa.model.MovieShowType;
import com.example.isa.model.Projekcija;

public class MovieShowDTO {

	private MovieShowType type;
	
	private String name;

	private int duration;
	
	private String description;
	
	private String director;

	private List<Glumac> glumci;
	
	private String genre;
	
	private double price;
	
	private long cinnemaId;
	
	private String image;

	public MovieShow getMovieShow() {
		MovieShow retVal = new MovieShow();
		retVal.setType(type);
		retVal.setName(name);
		retVal.setDuration(duration);
		retVal.setDescription(description);
		retVal.setDirector(director);
		retVal.setGlumci(glumci);
		retVal.setGenre(genre);
		retVal.setPrice(price);
		retVal.setProjekcije(new ArrayList<Projekcija>());
		retVal.setImage(image);
		return retVal;
	}
	
	
	
	public MovieShowDTO() {
		super();
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public MovieShowDTO(MovieShowType type, String name, int duration, String description, String director,
			List<Glumac> glumci, String genre, double price, long cinnemaId) {
		super();
		this.type = type;
		this.name = name;
		this.duration = duration;
		this.description = description;
		this.director = director;
		this.glumci = glumci;
		this.genre = genre;
		this.price = price;
		this.cinnemaId = cinnemaId;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getCinnemaId() {
		return cinnemaId;
	}

	public void setCinnemaId(long cinnemaId) {
		this.cinnemaId = cinnemaId;
	}
	
	
}
