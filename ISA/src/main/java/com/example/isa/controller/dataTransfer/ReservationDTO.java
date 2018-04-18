package com.example.isa.controller.dataTransfer;

import com.example.isa.model.Projekcija;
import com.example.isa.model.Rezervacija;
import com.example.isa.model.RezervacijaStatus;

public class ReservationDTO {
	private Projekcija projekcija;
	
	private Long idRezervant;

	private long rezSedisteId;
	
	private Boolean isHost;

	private Long idHost;

	
	public long getRezSedisteId() {
		return rezSedisteId;
	}


	public void setRezSedisteId(long rezSedisteId) {
		this.rezSedisteId = rezSedisteId;
	}


	public Long getIdRezervant() {
		return idRezervant;
	}


	public void setIdRezervant(Long idRezervant) {
		this.idRezervant = idRezervant;
	}


	public Long getIdHost() {
		return idHost;
	}


	public void setIdHost(Long idHost) {
		this.idHost = idHost;
	}


	public Projekcija getProjekcija() {
		return projekcija;
	}

	public void setProjekcija(Projekcija projekcija) {
		this.projekcija = projekcija;
	}


	public Boolean getIsHost() {
		return isHost;
	}

	public void setIsHost(Boolean isHost) {
		this.isHost = isHost;
	}
	
	

}
