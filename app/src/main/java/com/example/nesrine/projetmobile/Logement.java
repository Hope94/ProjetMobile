package com.example.nesrine.projetmobile;

import java.io.Serializable;

import rx.observers.SerializedObserver;

/**
 * Created by Nesrine on 23/04/2017.
 */


public class Logement implements Serializable{
    private String id;
    private String type;
    private String region;
    private int prix;
    private double lat;
    private double lng;
    private String mainImage;
    private String[] listDetailImages;
    private String descriptif;

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String[] getListDetailImages() {
        return listDetailImages;
    }

    public void setListDetailImages(String[] listDetailImages) {
        this.listDetailImages = listDetailImages;
    }


    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
    public double getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
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

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }
}
