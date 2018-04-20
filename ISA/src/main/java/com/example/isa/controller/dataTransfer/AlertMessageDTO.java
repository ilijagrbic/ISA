package com.example.isa.controller.dataTransfer;

public class AlertMessageDTO {
	private String err;

	public AlertMessageDTO() {
		super();
	}

	public AlertMessageDTO(String err) {
		super();
		this.err = err;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}
	
	
}
