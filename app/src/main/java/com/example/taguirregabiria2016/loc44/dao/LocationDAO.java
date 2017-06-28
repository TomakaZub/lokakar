package com.example.taguirregabiria2016.loc44.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.taguirregabiria2016.loc44.model.Client;
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class LocationDAO {

    private final static String QUERY_CREATE_TABLE_VEHICULE = "create table if not exists "
            + "locations ("
            + "id integer primary key autoincrement, "
            + "vehicule_id integer, "
            + "debut text, "
            + "fin text, "
            + "client_id integer, "
            + "album text, "
            + "rendu integer)";

    private final static String TABLE_NAME = "locations";

    public static void createTable(SQLiteDatabase db) {

        db.execSQL(QUERY_CREATE_TABLE_VEHICULE);
    }

    public static long insertLocation(Location l) {

        ContentValues values = new ContentValues();

        String album= "";
        for (String item:l.getAlbum()) {
            album += item + ";";
        }
        if (album.length()>0) {
            album = album.substring(0, album.length()-1);
        }

        values.put("vehicule_id", l.getVehicule().getId());
        values.put("debut", convertDate(l.getDebut()));
        values.put("fin", convertDate(l.getFin()));
        values.put("client_id", l.getClient().getId());
        values.put("album", album);
        values.put("rendu", l.getRendu());

        return BaseDAO.getDB().insert(TABLE_NAME, null, values);
    }

    public static long updateLocation(Location l) {

        String[] args = new String[1];
        ContentValues values = new ContentValues();

        args[0] = String.valueOf(l.getId());

        String album= "";
        for (String item:l.getAlbum()) {
            album += item + ";";
        }
        if (album.length()>0) {
            album = album.substring(0, album.length()-1);
        }

        values.put("vehicule_id", l.getVehicule().getId());
        values.put("debut", convertDate(l.getDebut()));
        values.put("fin", convertDate(l.getFin()));
        values.put("client_id", l.getClient().getId());
        values.put("album", album);
        values.put("rendu", l.getRendu());

        return BaseDAO.getDB().update(TABLE_NAME, values, "id=?", args);
    }
    public static long removeLocation(Location l) {

        return BaseDAO.getDB().delete(TABLE_NAME, "id=" + l.getId(), null);
    }
    public static Location getLocation(int id) {

        Cursor c = BaseDAO.getDB().query(TABLE_NAME,
                new String[]{"id", "vehicule_id", "debut", "fin", "client_id", "album", "rendu"},
                "id=" + id, null, null, null, null);

        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();

        int vehiculeId = c.getInt(1);
        String debut = c.getString(2);
        String fin = c.getString(3);
        int clientID = c.getInt(4);
        String photos = c.getString(5);
        int rendu = c.getInt(6);

        String[] tmp = photos.split(";");
        List<String> album= new ArrayList<>();
        for (String item:tmp) {
            album.add(item);
        }

        Vehicule vehicule = VehiculeDAO.getVehicule(vehiculeId);
        Client client = ClientDAO.getClient(clientID);

        return new Location(id, vehicule, convertDate(debut), convertDate(fin), client, album, rendu);
    }

    public static List<Location> getAllLocations()
    {
        SQLiteDatabase db = BaseDAO.getDB();
        // recuperer la liste de toutes les location qui on le statut rendu

        Cursor c = db.query(TABLE_NAME,
                new String[]{"id", "vehicule_id", "debut", "fin", "client_id", "album", "rendu"},
                null, null, null, null, null);
        List<Location> locations = new ArrayList<>();

        if (c.getCount() == 0) {
            c.close();
            return locations;
        }

        while (c.moveToNext()) {

            int id = c.getInt(0);
            int vehiculeId = c.getInt(1);
            String debut = c.getString(2);
            String fin = c.getString(3);
            int clientId = c.getInt(4);
            String album = c.getString(5);
            int rendu = c.getInt(6);

            List<String>photos = new ArrayList<>();
            String[]dummy = album.split(";");
            for (String item:dummy) {
                photos.add(item);
            }
            Vehicule vehicule = VehiculeDAO.getVehicule(vehiculeId);
            Client client = ClientDAO.getClient(clientId);
            Location location = new Location(id, vehicule, convertDate(debut), convertDate(fin), client, photos, rendu);
            locations.add(location);
            Log.d("*** loaded data ***", location.toString());
        }
        c.close();

        Log.d("*** loaded data ***", locations.toString());
        return locations;
    }

    public static List<Location> getAllOpenLocations()
    {
        SQLiteDatabase db = BaseDAO.getDB();
        // recuperer la liste de toutes les location qui on le statut rendu

        Cursor c = db.query(TABLE_NAME,
                new String[]{"id", "vehicule_id", "debut", "fin", "client_id", "album", "rendu"},
                null, null, null, null, null);
        List<Location> locations = new ArrayList<>();

        if (c.getCount() == 0) {
            c.close();
            return locations;
        }

        while (c.moveToNext()) {

            int id = c.getInt(0);
            int vehiculeId = c.getInt(1);
            String debut = c.getString(2);
            String fin = c.getString(3);
            int clientId = c.getInt(4);
            String album = c.getString(5);
            int rendu = c.getInt(6);

            if (rendu==0) {
                List<String> photos = new ArrayList<>();
                String[] dummy = album.split(";");
                for (String item : dummy) {
                    photos.add(item);
                }
                Vehicule vehicule = VehiculeDAO.getVehicule(vehiculeId);
                Client client = ClientDAO.getClient(clientId);
                locations.add(new Location(id, vehicule, convertDate(debut), convertDate(fin), client, photos, rendu));
            }
        }
        c.close();

        return locations;
    }

    public List<Vehicule> getVehicules_ALouer() {
        List<Vehicule> vehicules_ALouer = new ArrayList<>();
        SQLiteDatabase db = BaseDAO.getDB();

        // Recuperer la liste de location avec rendu = true
        String requette = "SELECT id, vehicule_id, debut, fin, client_id, rendu FROM locations WHERE rendu = 1";
        Cursor c = db.rawQuery(requette,null);
        if (c.getCount() == 0) {
            c.close();
            return vehicules_ALouer;
        }

        // Pour chaque élément de la liste recuperer l'ID du vehicule et faire un getVehiculebyid() et on l'ajoute a la liste
        while (c.moveToNext()) {
            int idVehicule = c.getInt(c.getColumnIndex("vehicule_id"));
            Vehicule vehicule = VehiculeDAO.getVehicule(idVehicule);
            vehicules_ALouer.add(vehicule);
        }
        //retourner la liste
        return vehicules_ALouer;
    }


    public List<Vehicule> getVehicules_Louer() {

        List<Vehicule> vehicules_Louer = new ArrayList<>();
        SQLiteDatabase db = BaseDAO.getDB();

        // Recuperer la liste de location avec rendu = true
        String requette = "SELECT id, vehicule_id, debut, fin, client_id, rendu FROM locations WHERE rendu = 0";
        Cursor c = db.rawQuery(requette,null);
        if (c.getCount() == 0) {
            c.close();
            return vehicules_Louer;
        }

        // Pour chaque élément de la liste recuperer l'ID du vehicule et faire un getVehiculebyid() et on l'ajoute a la liste
        while (c.moveToNext()) {
            int idVehicule = c.getColumnIndex("vehicule_id");
            Vehicule vehicule = VehiculeDAO.getVehicule(idVehicule);
//            Client client
            vehicules_Louer.add(vehicule);
        }
        //retourner la liste
        return vehicules_Louer;
    }

    public List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();
        SQLiteDatabase db = BaseDAO.getDB();
        String requette = "SELECT id, vehicule_id, debut, fin, client_id, rendu FROM locations WHERE rendu = 0";
        Cursor c = db.rawQuery(requette,null);
        if (c.getCount() == 0) {
            c.close();
            return locations;
        }
        while (c.moveToNext()) {
            int idLocation = c.getInt(c.getColumnIndex("id"));
            Location location = LocationDAO.getLocation(idLocation);
            locations.add(location);
        }
        return locations;
    }

    private static String convertDate (String date) {

        String[] dummies = date.split(" ");
        String[] parts = dummies[0].split("/");

        return parts[2]+"/"+parts[1]+"/"+parts[0]+" "+dummies[1];
    }
}
