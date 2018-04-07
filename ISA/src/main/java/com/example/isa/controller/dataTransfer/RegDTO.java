package com.example.isa.controller.dataTransfer;

import com.example.isa.model.users.Role;
import com.example.isa.model.users.User;

public class RegDTO {
	// Mozda ce mi biti potreban id
	private long id;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String city;
	
	private String phone;
	
	private String password;
	
	private String confPassword;

	private boolean actiaved;
	
	public User createUser(RegDTO source) {
		User user = new User();
		user.setName(source.getName());
		user.setSurname(source.getSurname());
		user.setEmail(source.getEmail());
		user.setCity(source.getCity());
		user.setPhone(source.getPhone());
		user.setPassword(source.getPassword());
		user.setActiaved(false);
		user.setRole(Role.REG_USER);
		return user;
	}
	
	public boolean populatedFields() {
		// Da li da proverim za sva polja?
		return !("".equals(email) || "".equals(password) || "".equals(confPassword));
	}
	
	public boolean passwordMatch() {
		return password.equals(confPassword);
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	public boolean isActiaved() {
		return actiaved;
	}

	public void setActiaved(boolean actiaved) {
		this.actiaved = actiaved;
	}
	
	
	
	
}
