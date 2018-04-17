package com.example.isa.controller.dataTransfer;

import java.util.Date;

public class IncomeReportDTO {

	private Date od;
	private Date doo;
	private double income;
	
	public IncomeReportDTO() {
		super();
	}
	public Date getOd() {
		return od;
	}
	public void setOd(Date od) {
		this.od = od;
	}
	public Date getDoo() {
		return doo;
	}
	public void setDoo(Date doo) {
		this.doo = doo;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	
	
	
}
