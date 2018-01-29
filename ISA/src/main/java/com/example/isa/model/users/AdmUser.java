package com.example.isa.model.users;

import javax.persistence.*;

public class AdmUser extends User{

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AdminType type;
	
	private boolean firstTime = true;

	public AdmUser() {
		super();
	}

	public AdminType getType() {
		return type;
	}

	public void setType(AdminType type) {
		this.type = type;
	}
	
	
}
