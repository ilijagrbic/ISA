package com.example.isa.controller.dataTransfer;

public class ReportDTO {
	
	private double avgOcena;

	public ReportDTO() {
		super();
	}
	
	

	public ReportDTO(double avgOcena) {
		super();
		this.avgOcena = avgOcena;
	}



	public double getAvgOcena() {
		return avgOcena;
	}

	public void setAvgOcena(double avgOcena) {
		this.avgOcena = avgOcena;
	}
	
	
	
}
