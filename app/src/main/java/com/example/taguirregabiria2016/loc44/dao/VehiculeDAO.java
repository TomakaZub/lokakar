package com.example.taguirregabiria2016.loc44.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class VehiculeDAO {

    private final static String QUERY_CREATE_TABLE_VEHICULE = "create table if not exists "
            + "vehicules ("
            + "id integer primary key autoincrement, "
            + "marque text, "
            + "modele text, "
            + "immatriculation text, "
            + "utilisation integer, "
            + "album text, "
            + "prix_jour real)";

    private final static String TABLE_NAME = "vehicules";

    public static void createTable(SQLiteDatabase db) {

        db.execSQL(QUERY_CREATE_TABLE_VEHICULE);
    }

    public static long insertVehicule(Vehicule v) {

        ContentValues values = new ContentValues();

        String album= "";
        for (String item:v.getAlbum()) {
            album += item + ";";
        }
        if (album.length()>0) {
            album = album.substring(0, album.length()-1);
        }

        values.put("marque", v.getMarque());
        values.put("modele", v.getModele());
        values.put("immatriculation", v.getImmatriculation());
        values.put("utilisation", v.getUtilisation());
        values.put("album", album);
        values.put("prix_jour", v.getPrixJour());

        return BaseDAO.getDB().insert(TABLE_NAME, null, values);
    }

    public static long updateVehicule(Vehicule v) {

        String[] args = new String[1];
        ContentValues values = new ContentValues();

        args[0] = String.valueOf(v.getId());

        String album= "";
        for (String item:v.getAlbum()) {
            album += item + ";";
        }
        if (album.length()>0) {
            album = album.substring(0, album.length()-1);
        }

        values.put("marque", v.getMarque());
        values.put("modele", v.getModele());
        values.put("immatriculation", v.getImmatriculation());
        values.put("utilisation", v.getUtilisation());
        values.put("album", album);
        values.put("prix_jour", v.getPrixJour());

        return BaseDAO.getDB().update(TABLE_NAME, values, "id=?", args);
    }

    public static long removeVehicule(Vehicule v) {

        return BaseDAO.getDB().delete(TABLE_NAME, "id=" + v.getId(), null);
    }

    public static Vehicule getVehicule(int id) {

        Cursor c = BaseDAO.getDB().query(TABLE_NAME,
                new String[]{"id", "marque", "modele", "immatriculation", "utilisation", "album", "prix_jour"},
                "id=" + id, null, null, null, null);

        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();

        String marque = c.getString(1);
        String modele = c.getString(2);
        String immatriculation = c.getString(3);
        int utilisation = c.getInt(4);
        String photos = c.getString(5);
        Double prixJour = c.getDouble(6);

        String[] tmp = photos.split(";");
        List<String> album= new ArrayList<>();
        for (String item:tmp) {
            album.add(item);
        }

        return new Vehicule(id, marque, modele, immatriculation, utilisation, album, prixJour);
    }


    public static List<Vehicule> getAllVehicules() {

       SQLiteDatabase db = BaseDAO.getDB();

        Cursor c = db.query(TABLE_NAME,
                new String[]{"id", "marque", "modele", "immatriculation", "utilisation", "album", "prix_jour"},
                null, null, null, null, null);
        List<Vehicule> vehicules = new ArrayList<>();

        if (c.getCount() == 0) {
            c.close();
            return vehicules;
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
            vehicules.add(new Vehicule(id, marque, modele, immatriculation, utilisation, photos, prixJour));
        }
        c.close();

        return vehicules;
    }


}
