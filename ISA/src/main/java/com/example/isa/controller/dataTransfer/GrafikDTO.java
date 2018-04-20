package com.example.isa.controller.dataTransfer;

public class GrafikDTO {

	private int dan;
	private int value;
	public GrafikDTO() {
		super();
	}
	public GrafikDTO(int dan, int value) {
		super();
		this.dan = dan;
		this.value = value;
	}
	public int getDan() {
		return dan;
	}
	public void setDan(int dan) {
		this.dan = dan;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
	
}
