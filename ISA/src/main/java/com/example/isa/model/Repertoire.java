package com.example.isa.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Repertoire {

	@Id
    @GeneratedValue
    private long id;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "repertoar")
	private List<MovieShow> movies;
	
	@OneToOne(fetch = FetchType.EAGER, optional = false)
	private BioskopPozoriste bioskop;

	public Repertoire() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<MovieShow> getMovies() {
		return movies;
	}

	public void setMovies(List<MovieShow> movies) {
		this.movies = movies;
	}

	public BioskopPozoriste getBioskop() {
		return bioskop;
	}

	public void setBioskop(BioskopPozoriste bioskop) {
		this.bioskop = bioskop;
	}


	
	
	
}
