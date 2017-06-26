package com.example.taguirregabiria2016.loc44.model;

import java.util.List;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class Agence {

    private int id;
    private Gerant gerant;
    private List<Vehicule> vehiculeList;
    private List<Location> locationList;
    private Adresse adresse;

    public Agence(Gerant gerant, List<Vehicule> vehiculeList, List<Location> locationList, Adresse adresse) {
        this.gerant = gerant;
        this.vehiculeList = vehiculeList;
        this.locationList = locationList;
        this.adresse = adresse;
    }

    public Agence(int id, Gerant gerant, List<Vehicule> vehiculeList, List<Location> locationList, Adresse adresse) {
        this.id = id;
        this.gerant = gerant;
        this.vehiculeList = vehiculeList;
        this.locationList = locationList;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Gerant getGerant() {
        return gerant;
    }

    public void setGerant(Gerant gerant) {
        this.gerant = gerant;
    }

    public List<Vehicule> getVehiculeList() {
        return vehiculeList;
    }

    public void setVehiculeList(List<Vehicule> vehiculeList) {
        this.vehiculeList = vehiculeList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Agence{" +
                "id=" + id +
                ", gerant=" + gerant +
                ", vehiculeList=" + vehiculeList +
                ", locationList=" + locationList +
                ", adresse=" + adresse +
                '}';
    }
}
