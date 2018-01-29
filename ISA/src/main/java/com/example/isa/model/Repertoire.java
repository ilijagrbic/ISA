package com.example.isa.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Repertoire {

	@Id
    @GeneratedValue
    private long id;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "repertoar")
	private List<MovieShow> projections;
	
	@OneToOne(fetch = FetchType.EAGER, optional = false)
	private BioskopPozoriste restaurant;
	
	
}
