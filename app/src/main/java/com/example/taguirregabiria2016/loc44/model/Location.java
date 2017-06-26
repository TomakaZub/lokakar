package com.example.taguirregabiria2016.loc44.model;

import java.util.List;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class Location {

    private int id;
    private Vehicule vehicule;
    private String debut;
    private String fin;
    private Client client;
    private List<String> albumEdL;

    public Location(Vehicule vehicule, String debut, String fin, Client client, List<String> albumEdL) {
        this.vehicule = vehicule;
        this.debut = debut;
        this.fin = fin;
        this.client = client;
        this.albumEdL = albumEdL;
    }

    public Location(int id, Vehicule vehicule, String debut, String fin, Client client, List<String> albumEdL) {
        this.id = id;
        this.vehicule = vehicule;
        this.debut = debut;
        this.fin = fin;
        this.client = client;
        this.albumEdL = albumEdL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<String> getAlbum() {
        return albumEdL;
    }

    public void setAlbum(List<String> album) {
        this.albumEdL = album;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", vehicule=" + vehicule +
                ", debut='" + debut + '\'' +
                ", fin='" + fin + '\'' +
                ", client=" + client +
                ", album=" + albumEdL +
                '}';
    }
}
