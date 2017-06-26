package com.example.taguirregabiria2016.loc44.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.taguirregabiria2016.loc44.model.Adresse;
import com.example.taguirregabiria2016.loc44.model.Agence;
import com.example.taguirregabiria2016.loc44.model.Gerant;
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.util.List;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class AgenceDAO {

    private final static String QUERY_CREATE_TABLE_AGENCE = "create table if not exists "
            + "agences ("
            + "id integer primary key autoincrement, "
            + "gerant_id integer"
            + "adresse_id integer)";

    private final static String TABLE_NAME = "agences";

    public static void createTable(SQLiteDatabase db) {

        db.execSQL(QUERY_CREATE_TABLE_AGENCE);
    }

    public static long insertAgence(Agence a) {

        ContentValues values = new ContentValues();

        values.put("gerant_id", a.getGerant().getId());
        values.put("adresse_id", a.getAdresse().getId());

        return BaseDAO.getDB().insert(TABLE_NAME, null, values);
    }

    public static long updateArticle (Agence a) {

        String[]args = new String[1];
        ContentValues values = new ContentValues();

        args[0] = String.valueOf(a.getId());

        values.put("gerant_id", a.getGerant().getId());
        values.put("adresse_id", a.getAdresse().getId());

        return BaseDAO.getDB().update(TABLE_NAME, values, "id=?", args);
    }


    public static long removeArticle (Agence a) {

        return BaseDAO.getDB().delete(TABLE_NAME, "id=" + a.getId(), null);
    }

    public static Agence getAgence(int id) {

        Cursor c = BaseDAO.getDB().query(TABLE_NAME,
                new String[]{"id", "gerant_id","adresse_id"},
                "id="+id, null, null, null, null);
        Agence a=null;

        if (c.getCount()==0) {
            c.close();
            return null;
        }
        c.moveToFirst();
        int gerantId = c.getInt(1);
        int adresseId = c.getInt(2);

        Gerant gerant = GerantDAO.getGerant(gerantId);
        Adresse adresse = AdresseDAO.getAdresse(adresseId);
        //TODO: Récupérer la liste des véhicules et des locations
        List<Vehicule> vehiculeList = null;
        List<Location>locationList = null;

        a = new Agence(id, gerant, vehiculeList, locationList, adresse);
        c.close();

        return a;
    }

}

