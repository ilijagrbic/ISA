package com.example.isa.controller.dataTransfer;

public class InviteDTO {
	private long idReceiver;
	private long idSender;
	
	public InviteDTO() {
		super();
	}
	
	public long getIdReceiver() {
		return idReceiver;
	}
	public void setIdReceiver(long idReceiver) {
		this.idReceiver = idReceiver;
	}
	public long getIdSender() {
		return idSender;
	}
	public void setIdSender(long idSender) {
		this.idSender = idSender;
	}
	
}
