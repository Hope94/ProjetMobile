package com.example.nesrine.projetmobile;

/**
 * Created by Nesrine on 23/04/2017.
 */


public class Logement {
    private String id;
    private String type;
    private String region;
    private int prix;
    private String proprietaire;
    private int image;

    public double getPrix() {
        return prix;
    }

    public Logement(String id, String type, String region, int prix, String proprietaire, int image) {
        this.id = id;
        this.type = type;
        this.region = region;
        this.prix = prix;
        this.proprietaire = proprietaire;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Logement(String id, String type, String region, int prix, String proprietaire) {
        this.id = id;
        this.type = type;
        this.region = region;
        this.prix = prix;
        this.proprietaire = proprietaire;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }
}
