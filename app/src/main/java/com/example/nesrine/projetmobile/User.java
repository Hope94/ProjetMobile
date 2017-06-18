package com.example.nesrine.projetmobile;

/**
 * Created by Nesrine on 16/06/2017.
 */

public class User {
    private String email;
    private String mot_de_passe;
    private String username;


    public void setEmail(String email) {
        this.email = email;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public String getUsername() {
        return username;
    }
}
