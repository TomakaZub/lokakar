package com.example.taguirregabiria2016.loc44.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class Client extends Personne implements Serializable {


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
