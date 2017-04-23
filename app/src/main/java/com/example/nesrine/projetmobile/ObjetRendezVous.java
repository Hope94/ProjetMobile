package com.example.nesrine.projetmobile;

/**
 * Created by Nesrine on 22/04/2017.
 */

public class ObjetRendezVous  {
    private String userName;
    private String date;
    private  String heure;
    private String status;

    public ObjetRendezVous(String userName, String date, String heure, String status) {
        this.userName = userName;
        this.date = date;
        this.heure = heure;
        this.status = status;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public String getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public String getStatus() {
        return status;
    }
}
