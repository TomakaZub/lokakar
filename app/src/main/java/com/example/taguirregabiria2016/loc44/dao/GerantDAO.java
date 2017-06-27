package com.example.taguirregabiria2016.loc44.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.taguirregabiria2016.loc44.model.Adresse;
import com.example.taguirregabiria2016.loc44.model.Gerant;
import com.example.taguirregabiria2016.loc44.model.Personne;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class GerantDAO {

    private final static String QUERY_CREATE_TABLE_GERANT = "create table if not exists "
            + "gerants ("
            + "id integer primary key autoincrement, "
            + "nom text, "
            + "prenom text, "
            + "telephone text, "
            + "email text, "
            + "adresse_id integer, "
            + "tel_pro text, "
            + "email_pro text, "
            + "mot_de_passe text)";

    private final static String TABLE_NAME = "gerants";

    public static void createTable(SQLiteDatabase db) {

        db.execSQL(QUERY_CREATE_TABLE_GERANT);
    }

    public static long insertGerant(Gerant g) {

        ContentValues values = new ContentValues();

        values.put("nom", g.getNom());
        values.put("prenom", g.getNom());
        values.put("telephone", g.getNom());
        values.put("email", g.getNom());
        values.put("adresse_id", g.getAdresse().getId());//AdresseDAO.insertAdresse(g.getAdresse()));
        values.put("tel_pro", g.getNom());
        values.put("email_pro", g.getNom());
        values.put("mot_de_passe", g.getNom());

        return BaseDAO.getDB().insert(TABLE_NAME, null, values);
    }

    public static long updateGerant(Gerant g) {

        String[] args = new String[1];
        ContentValues values = new ContentValues();

        args[0] = String.valueOf(g.getId());

        values.put("nom", g.getNom());
        values.put("prenom", g.getNom());
        values.put("telephone", g.getNom());
        values.put("email", g.getNom());
        values.put("adresse_id", g.getAdresse().getId());//AdresseDAO.updateAdresse(g.getAdresse()));
        values.put("tel_pro", g.getNom());
        values.put("email_pro", g.getNom());
        values.put("mot_de_passe", g.getNom());

        return BaseDAO.getDB().update(TABLE_NAME, values, "id=?", args);
    }


    public static long removeGerant(Gerant g) {

        return BaseDAO.getDB().delete(TABLE_NAME, "id=" + g.getId(), null);
    }

    public static Gerant getGerant(int id) {

        Cursor c = BaseDAO.getDB().query(TABLE_NAME,
                new String[]{"id", "nom", "prenom", "telephone", "email", "adresse_id", "tel_pro", "email_pro", "mot_de_passe"},
                "id=" + id, null, null, null, null);
        Gerant g;

        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();

        String nom = c.getString(1);
        String prenom = c.getString(2);
        String email = c.getString(3);
        String telephone = c.getString(4);
        int adresseId = c.getInt(5);
        String telPro = c.getString(6);
        String emailPro = c.getString(7);
        String motDePasse = c.getString(8);

        Adresse adresse = AdresseDAO.getAdresse(adresseId);
        Personne p = new Personne(id, nom, prenom, telephone, email, adresse);
        g = new Gerant(p, telPro, emailPro, motDePasse);

        c.close();

        return g;
    }

    public static Gerant verifGerant(String emailPro, String motDePasse) {

        //query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)

        SQLiteDatabase db = BaseDAO.getDB();

        Cursor c = db.query(TABLE_NAME,
                new String[]{"id", "nom", "prenom", "telephone", "email", "adresse_id", "tel_pro", "email_pro", "mot_de_passe"},
                "email_pro like ? and mot_de_passe like ?",
                new String[]{emailPro, motDePasse}, null, null, null);
        Gerant g;

        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();

        int id = c.getInt(0);
        String nom = c.getString(1);
        String prenom = c.getString(2);
        String email = c.getString(3);
        String telephone = c.getString(4);
        int adresseId = c.getInt(5);
        String telPro = c.getString(6);

        Adresse adresse = AdresseDAO.getAdresse(adresseId);
        Personne p = new Personne(id, nom, prenom, telephone, email, adresse);
        g = new Gerant(p, telPro, emailPro, motDePasse);

        c.close();

        return g;
    }

}
