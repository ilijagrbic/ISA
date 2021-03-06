package com.example.isa.model.users;

import javax.persistence.*;

@Entity
@Table(name = "koriscnici")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class User {

	@Id
	@GeneratedValue
	protected long id;
	
	protected String name;
	
	protected String surname;
	
	@Column(name="email", unique = true, nullable = false)
	protected String email;
	
	//City - dodat grad 
	protected String city;
	
	protected String phone;
	
	protected boolean actiaved = false;
	
	@Column(nullable = false)
	protected String password;

	public User() {	}

	public void update(User u) {
		this.name = u.name;
		this.surname = u.surname;
		this.city = u.city;
		this.phone = u.phone;
		
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	
}
