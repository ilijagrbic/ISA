package com.example.isa.model.users;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import com.example.isa.model.Rezervacija;
import com.example.isa.model.UserMesto;

@Entity
@Table(name = "korisnici")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue
	protected long id;

	protected String name;

	protected String surname;

	@Column(name = "email", unique = true, nullable = false)
	protected String email;

	// City - dodat grad
	protected String city;

	protected String phone;

	protected boolean actiaved = false;

	@Column(nullable = false)
	protected String password;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	//////// RegUser
	@OneToMany
	private List<UserMesto> pozivi;

	@OneToMany
	private List<Rezervacija> rezervacije;

	// Za verifikaciju
	private String verificationCode;

	private int bodovi;
	/////////

	// Admin
	private boolean firstTime = true;

	public User() {
		this.verificationCode = UUID.randomUUID().toString();
	}

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<UserMesto> getPozivi() {
		return pozivi;
	}

	public void setPozivi(List<UserMesto> pozivi) {
		this.pozivi = pozivi;
	}

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public int getBodovi() {
		return bodovi;
	}

	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}

	public boolean isFirstTime() {
		return firstTime;
	}

	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}

}
