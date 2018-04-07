package com.example.isa.controller.dataTransfer;

import com.example.isa.model.users.User;

public class LoginDTO {

    private String email;

    private String password;

    public LoginDTO() {
    }
    
    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    // konvertujemo u registrovanog korisnika
    public User getUserFromLogin(LoginDTO source) {
    	User user = new User();
    	user.setPassword(source.getPassword());
    	user.setEmail(source.getEmail());
    	return user;
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
}
