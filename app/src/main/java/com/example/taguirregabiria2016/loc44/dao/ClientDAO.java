package com.example.taguirregabiria2016.loc44.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.taguirregabiria2016.loc44.model.Adresse;
import com.example.taguirregabiria2016.loc44.model.Client;
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.model.Personne;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class ClientDAO {

    private final static String QUERY_CREATE_TABLE_CLIENT = "create table if not exists "
            + "clients ("
            + "id integer primary key autoincrement, "
            + "nom text, "
            + "prenom text, "
            + "telephone text, "
            + "email text, "
            + "adresse_id integer)";

    private final static String TABLE_NAME = "clients";

    public static void createTable(SQLiteDatabase db) {

        db.execSQL(QUERY_CREATE_TABLE_CLIENT);
    }

    public static long insertClient(Client c) {

        ContentValues values = new ContentValues();

        values.put("nom", c.getNom());
        values.put("prenom", c.getPrenom());
        values.put("telephone", c.getTelephone());
        values.put("email", c.getEmail());
        values.put("adresse_id", c.getAdresse().getId());

        return BaseDAO.getDB().insert(TABLE_NAME, null, values);
    }

    public static long updateClient(Client c) {

        String[] args = new String[1];
        ContentValues values = new ContentValues();

        args[0] = String.valueOf(c.getId());

        values.put("nom", c.getNom());
        values.put("prenom", c.getPrenom());
        values.put("telephone", c.getTelephone());
        values.put("email", c.getEmail());
        values.put("adresse_id", AdresseDAO.updateAdresse(c.getAdresse()));

        return BaseDAO.getDB().update(TABLE_NAME, values, "id=?", args);
    }


    public static long removeClient(Client c) {

        return BaseDAO.getDB().delete(TABLE_NAME, "id=" + c.getId(), null);
    }

    public static Client getClient(int id) {

        Cursor c = BaseDAO.getDB().query(TABLE_NAME,
                new String[]{"id", "nom", "prenom", "telephone", "email", "adresse_id"},
                "id=" + id, null, null, null, null);
        Client client = null;

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

        Personne p = new Personne(id, nom, prenom, telephone, email, AdresseDAO.getAdresse(adresseId));

        client = new Client(p);

        c.close();

        return client;
    }

    public static List<Client> getAllClients() {

        SQLiteDatabase db = BaseDAO.getDB();

        Cursor c = db.query(TABLE_NAME,
                new String[]{"id", "nom", "prenom", "telephone", "email", "adresse_id"},
                null, null, null, null, null);
        List<Client> clients = new ArrayList<>();

        if (c.getCount() == 0) {
            c.close();
            return clients;
        }

        while (c.moveToNext()) {

            int id = c.getInt(0);
            String nom = c.getString(1);
            String prenom = c.getString(2);
            String telephone = c.getString(3);
            String email = c.getString(4);
            int adresseId = c.getInt(5);

            Personne p = new Personne(id, nom, prenom, telephone, email, AdresseDAO.getAdresse(adresseId));

            clients.add(new Client(p));
        }
        c.close();

        return clients;
    }

    public static List<Client> getFilteredClients(String filter) {

        SQLiteDatabase db = BaseDAO.getDB();

        Cursor c = db.query(TABLE_NAME,
                new String[]{"id", "nom", "prenom", "telephone", "email", "adresse_id"},
                null, null, null, null, null);
        List<Client> clients = new ArrayList<>();

        if (c.getCount() == 0) {
            c.close();
            return clients;
        }

        while (c.moveToNext()) {

            String nom = c.getString(1);

            if (nom.toLowerCase().contains(filter.toLowerCase())) {

                int id = c.getInt(0);
                String prenom = c.getString(2);
                String telephone = c.getString(3);
                String email = c.getString(4);
                int adresseId = c.getInt(5);

                Adresse adresse = AdresseDAO.getAdresse(adresseId);
                clients.add(new Client(new Personne(id, nom, prenom, telephone, email, adresse)));
            }
        }
        c.close();

        return clients;
    }

}
