package com.example.isa.controller.dataTransfer;

import java.util.Date;

public class GrafikDTO {

	private Date dan;
	private int value;
	
	public GrafikDTO() {
		super();
	}

	public GrafikDTO(Date dan, int value) {
		super();
		this.dan = dan;
		this.value = value;
	}

	public Date getDan() {
		return dan;
	}

	public void setDan(Date dan) {
		this.dan = dan;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	
	
	
}
