package com.example.isa.controller.dataTransfer;

import com.example.isa.model.Rezervacija;
import com.example.isa.model.RezervacijaStatus;

public class RezervacijaDTO {
	
	private long projId;
	
	private Long userId;
	
	private long rezSedisteId;
	
	private RezervacijaStatus status;

	private int ocenaFilm;
	
	private int ocenaAmbijent;
		
	private Boolean isHost;
	
	private Long hostId;
	
	public Rezervacija getRezervacija() {
		Rezervacija rez = new Rezervacija();
		rez.setStatus(status);
		rez.setOcenaFilm(ocenaFilm);
		rez.setOcenaAmbijent(ocenaAmbijent);
		rez.setIsHost(isHost);
		rez.setHostId(hostId);
		
		return rez;
	}

	public RezervacijaDTO() {
		super();
	}

	public RezervacijaDTO(long projId, Long userId, long rezSedisteId, RezervacijaStatus status, int ocenaFilm,
			int ocenaAmbijent, Boolean isHost, Long hostId) {
		super();
		this.projId = projId;
		this.userId = userId;
		this.rezSedisteId = rezSedisteId;
		this.status = status;
		this.ocenaFilm = ocenaFilm;
		this.ocenaAmbijent = ocenaAmbijent;
		this.isHost = isHost;
		this.hostId = hostId;
	}

	public long getProjId() {
		return projId;
	}

	public void setProjId(long projId) {
		this.projId = projId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public long getRezSedisteId() {
		return rezSedisteId;
	}

	public void setRezSedisteId(long rezSedisteId) {
		this.rezSedisteId = rezSedisteId;
	}

	public RezervacijaStatus getStatus() {
		return status;
	}

	public void setStatus(RezervacijaStatus status) {
		this.status = status;
	}

	public int getOcenaFilm() {
		return ocenaFilm;
	}

	public void setOcenaFilm(int ocenaFilm) {
		this.ocenaFilm = ocenaFilm;
	}

	public int getOcenaAmbijent() {
		return ocenaAmbijent;
	}

	public void setOcenaAmbijent(int ocenaAmbijent) {
		this.ocenaAmbijent = ocenaAmbijent;
	}

	public Boolean getIsHost() {
		return isHost;
	}

	public void setIsHost(Boolean isHost) {
		this.isHost = isHost;
	}

	public Long getHostId() {
		return hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}

	

	
	
	
	
}
