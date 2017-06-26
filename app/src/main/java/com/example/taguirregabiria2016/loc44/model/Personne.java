package com.example.taguirregabiria2016.loc44.model;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class Personne {

    private int id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private Adresse adresse;

    public Personne(String nom, String prenom, String telephone, String email, Adresse adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    public Personne(int id, String nom, String prenom, String telephone, String email, Adresse adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    public Personne(Personne p) {

        this.id = p.id;
        this.nom = p.nom;
        this.prenom = p.prenom;
        this.telephone = p.telephone;
        this.email = p.email;
        this.adresse = p.adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", adresse=" + adresse +
                '}';
    }
}
