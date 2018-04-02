package com.example.isa.controller.dataTransfer;

import com.example.isa.model.users.RegUser;
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
    public RegUser getUserFromLogin(LoginDTO source) {
    	RegUser ru = new RegUser();
    	ru.setPassword(source.getPassword());
    	ru.setEmail(source.getEmail());
    	return ru;
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
