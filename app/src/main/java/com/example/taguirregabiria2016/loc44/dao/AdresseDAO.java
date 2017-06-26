package com.example.taguirregabiria2016.loc44.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.taguirregabiria2016.loc44.model.Adresse;
import com.example.taguirregabiria2016.loc44.model.Gerant;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class AdresseDAO {

    private final static String QUERY_CREATE_TABLE_ADRESSE = "create table if not exists "
            + "adresses ("
            + "id integer primary key autoincrement, "
            + "numero text"
            + "type text"
            + "voie text"
            + "supplement text"
            + "code_postal text"
            + "ville text"
            + "pays text)";

    private final static String TABLE_NAME = "gerants";

    public static void createTable(SQLiteDatabase db) {

        db.execSQL(QUERY_CREATE_TABLE_ADRESSE);
    }

    public static long insertAdresse(Adresse a) {

        ContentValues values = new ContentValues();

        values.put("numero", a.getNumero());
        values.put("type", a.getType());
        values.put("voie", a.getVoie());
        values.put("supplement", a.getSupplement());
        values.put("code_postal", a.getCodePostal());
        values.put("ville", a.getVille());
        values.put("pays", a.getPays());

        return BaseDAO.getDB().insert(TABLE_NAME, null, values);
    }

    public static long updateAdresse(Adresse a) {

        String[] args = new String[1];
        ContentValues values = new ContentValues();

        args[0] = String.valueOf(a.getId());

        values.put("numero", a.getNumero());
        values.put("type", a.getType());
        values.put("voie", a.getVoie());
        values.put("supplement", a.getSupplement());
        values.put("code_postal", a.getCodePostal());
        values.put("ville", a.getVille());
        values.put("pays", a.getPays());

        return BaseDAO.getDB().update(TABLE_NAME, values, "id=?", args);
    }

    public static long removeAdresse(Adresse a) {

        return BaseDAO.getDB().delete(TABLE_NAME, "id=" + a.getId(), null);
    }

    public static Adresse getAdresse(int id) {

        Cursor c = BaseDAO.getDB().query(TABLE_NAME,
                new String[]{"id", "numero", "type", "voie", "supplement", "code_postal", "ville", "pays"},
                "id=" + id, null, null, null, null);

        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();

        String numero = c.getString(1);
        String type = c.getString(2);
        String voie = c.getString(3);
        String supplement = c.getString(4);
        String codePostal = c.getString(5);
        String ville = c.getString(6);
        String pays = c.getString(7);

        return new Adresse(id, numero, type, voie, supplement, codePostal, ville, pays);
    }
}
