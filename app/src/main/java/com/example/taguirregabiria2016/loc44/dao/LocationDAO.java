package com.example.taguirregabiria2016.loc44.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.taguirregabiria2016.loc44.model.Client;
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class LocationDAO {

//    private int id;
//    private Vehicule vehicule;
//    private String debut;
//    private String fin;
//    private Client client;
//    private List<String> albumEdL;

    private final static String QUERY_CREATE_TABLE_VEHICULE = "create table if not exists "
            + "locations ("
            + "id integer primary key autoincrement, "
            + "vehicule_id integer, "
            + "debut text, "
            + "fin text, "
            + "client_id integer, "
            + "album text)";

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
        values.put("debut", l.getDebut());
        values.put("fin", l.getFin());
        values.put("client_id", l.getClient().getId());
        values.put("album", album);

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
        values.put("debut", l.getDebut());
        values.put("fin", l.getFin());
        values.put("client_id", l.getClient().getId());
        values.put("album", album);

        return BaseDAO.getDB().update(TABLE_NAME, values, "id=?", args);
    }
    public static long removeLocation(Location l) {

        return BaseDAO.getDB().delete(TABLE_NAME, "id=" + l.getId(), null);
    }
    public static Location getLocation(int id) {

        Cursor c = BaseDAO.getDB().query(TABLE_NAME,
                new String[]{"id", "vehicule_id", "debut", "fin", "client_id", "album"},
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

        String[] tmp = photos.split(";");
        List<String> album= new ArrayList<>();
        for (String item:tmp) {
            album.add(item);
        }

        Vehicule vehicule = VehiculeDAO.getVehicule(vehiculeId);
        Client client = ClientDAO.getClient(clientID);

        return new Location(id, vehicule, debut, fin, client, album);
    }

    public List<Location> isRenting()
    {
        SQLiteDatabase db = BaseDAO.getDB();
        // recuperer la liste de toutes les location qui on le statut rendu

        Cursor c = db.query(TABLE_NAME,
                new String[]{"id"," vehicule"," debut"," fin"," client"},
                null, null, null, null, null);
        List<Location> locations = new ArrayList<>();

        if (c.getCount() == 0) {
            c.close();
            return locations;
        }

        while (c.moveToNext()) {

            int id = c.getInt(0);
            String marque = c.getString(1);
            String modele = c.getString(2);
            String immatriculation = c.getString(3);
            int utilisation = c.getInt(4);
            String album = c.getString(5);
            Double prixJour = c.getDouble(6);

            List<String>photos = new ArrayList<>();
            String[]dummy = album.split(";");
            for (String item:dummy) {
                photos.add(item);
            }
//            locations.add(new Location(id, marque, modele, immatriculation, utilisation, photos, prixJour));
        }
        c.close();

    }

}
