package com.example.nesrine.projetmobile;

/**
 * Created by Nesrine on 22/04/2017.
 */

public class ObjetRendezVous  {
    private String id_rdv;
    private String userName;
    private String date;
    private  String heure;
    private String status;
    private String nomAnn;
    private String region;

    public void setNomAnn(String nomAnn) {
        this.nomAnn = nomAnn;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNomAnn() {
        return nomAnn;
    }

    public String getRegion() {
        return region;
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

    public String getId_rdv() {
        return id_rdv;
    }

    public void setId_rdv(String id_rdv) {
        this.id_rdv = id_rdv;
    }
}
