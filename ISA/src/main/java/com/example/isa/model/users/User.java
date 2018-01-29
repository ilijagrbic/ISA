package com.example.isa.model.users;

import javax.persistence.*;

@Entity
@Table(name = "allUsers")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class User {

	@Id
	@GeneratedValue
	protected long id;
	
	protected String name;
	
	protected String surname;
	
	@Column(name="email", unique = true, nullable = false)
	protected String email;
	
	//City
	
	protected String phone;
	
	protected boolean actiaved = false;
	
	@Column(nullable = false)
	protected String password;

	public User() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isActiaved() {
		return actiaved;
	}

	public void setActiaved(boolean actiaved) {
		this.actiaved = actiaved;
	}
	
	
	
	
}
