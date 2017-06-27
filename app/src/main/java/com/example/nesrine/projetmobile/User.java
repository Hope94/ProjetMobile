package com.example.nesrine.projetmobile;

import java.io.Serializable;

/**
 * Created by Nesrine on 16/06/2017.
 */

public class User implements Serializable {
    private String email;
    private String mot_de_passe;
    private String status;


    public void setEmail(String email) {
        this.email = email;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getEmail() {
        return email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
