package com.example.taguirregabiria2016.loc44.model;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class Gerant extends Personne {

    private String telPro;
    private String emailPro;
    private String motDePasse;


    public Gerant(Personne personne, String telPro, String emailPro, String motDePasse) {
        super(personne);
        this.telPro = telPro;
        this.emailPro = emailPro;
        this.motDePasse = motDePasse;
    }

    public Gerant(Personne personne, int id, String telPro, String emailPro, String motDePasse) {
        super(personne);
        this.telPro = telPro;
        this.emailPro = emailPro;
        this.motDePasse = motDePasse;
    }

    public String getTelPro() {
        return telPro;
    }

    public void setTelPro(String telPro) {
        this.telPro = telPro;
    }

    public String getEmailPro() {
        return emailPro;
    }

    public void setEmailPro(String emailPro) {
        this.emailPro = emailPro;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "Gerant{" +
                "telPro='" + telPro + '\'' +
                ", emailPro='" + emailPro + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }
}
