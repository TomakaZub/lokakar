package com.example.taguirregabiria2016.loc44.model;

import java.util.List;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class Client extends Personne {


    public Client(Personne personne) {
        super(personne);
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom=" + getNom() +
                ", prenom=" + getPrenom() +
                ", téléphone=" + getTelephone() +
                ", email=" + getEmail() +
                ", adresse=" + getAdresse() +
                '}';
    }

    public String toSpinnerItem() {

        return getPrenom() + " " + getNom() + " (" + getEmail() + " / " + getTelephone() + ")";
    }
}
