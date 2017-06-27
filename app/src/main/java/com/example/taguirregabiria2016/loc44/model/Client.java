package com.example.taguirregabiria2016.loc44.model;

import java.util.List;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class Client extends Personne {

    private Adresse adresse;
    private List<Location> locationList;

    public Client(Personne personne, List<Location> locationList) {
        super(personne);
        this.locationList = locationList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    @Override
    public String toString() {
        return "Client{" +
                "adresse=" + adresse +
                ", locationList=" + locationList +
                '}';
    }

    public String toSpinnerItem() {

        return getPrenom() + " " + getNom() + " (" + getEmail() + " / " + getTelephone() + ")";
    }
}
