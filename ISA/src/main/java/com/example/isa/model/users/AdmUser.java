package com.example.isa.model.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

import com.example.isa.model.BioskopPozoriste;

public class AdmUser extends User{

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AdminType type;
	
	@ManyToMany
	private List<BioskopPozoriste> bioskopi;
	
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
