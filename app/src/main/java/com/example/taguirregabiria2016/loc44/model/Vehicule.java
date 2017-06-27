package com.example.taguirregabiria2016.loc44.model;

import java.util.List;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class Vehicule {

    private int id;
    private String marque;
    private String modele;
    private String immatriculation;
    private int utilisation;
    private List<String> album;
    private double prixJour;

    public Vehicule(String marque, String modele, String immatriculation, int utilisation, List<String> album, double prixJour) {
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.utilisation = utilisation;
        this.album = album;
        this.prixJour = prixJour;
    }

    public Vehicule(int id, String marque, String modele, String immatriculation, int utilisation, List<String> album, double prixJour) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.utilisation = utilisation;
        this.album = album;
        this.prixJour = prixJour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getUtilisation() {
        return utilisation;
    }

    public void setUtilisation(int utilisation) {
        this.utilisation = utilisation;
    }

    public List<String> getAlbum() {
        return album;
    }

    public void setAlbum(List<String> album) {
        this.album = album;
    }

    public double getPrixJour() {
        return prixJour;
    }

    public void setPrixJour(double prixJour) {
        this.prixJour = prixJour;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", immatriculation='" + immatriculation + '\'' +
                ", utilisation=" + utilisation +
                ", album=" + album +
                ", prixJour=" + prixJour +
                '}';
    }

    public String toSpinnerItem () {

        return marque + " " + modele + " (" + immatriculation + ") - " + String.valueOf(prixJour) + "€/jour";
    }
}
