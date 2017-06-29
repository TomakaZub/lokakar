package com.example.taguirregabiria2016.loc44.model;

import java.io.Serializable;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class Adresse implements Serializable {

    private int id;
    private String numero;
    private String type;
    private String voie;
    private String supplement;
    private String codePostal;
    private String ville;
    private String pays;

    public Adresse(String numero, String type, String voie, String supplement, String codePostal, String ville, String pays) {
        this.numero = numero;
        this.type = type;
        this.voie = voie;
        this.supplement = supplement;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }

    public Adresse(int id, String numero, String type, String voie, String supplement, String codePostal, String ville, String pays) {
        this.id = id;
        this.numero = numero;
        this.type = type;
        this.voie = voie;
        this.supplement = supplement;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVoie() {
        return voie;
    }

    public void setVoie(String voie) {
        this.voie = voie;
    }

    public String getSupplement() {
        return supplement;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", type='" + type + '\'' +
                ", voie='" + voie + '\'' +
                ", supplement='" + supplement + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }
    public String toResume() {
        return "Adresse : " + numero + ", " + type + '\'' +
                ", voie='" + voie + '\'' +
                ", supplement='" + supplement + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }
}
