package com.example.taguirregabiria2016.loc44.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.taguirregabiria2016.loc44.model.Adresse;
import com.example.taguirregabiria2016.loc44.model.Agence;
import com.example.taguirregabiria2016.loc44.model.Client;
import com.example.taguirregabiria2016.loc44.model.Gerant;
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.model.Personne;
import com.example.taguirregabiria2016.loc44.model.Utilisation;
import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ojeanmarie2016 on 26/06/2017.
 */

public class BaseDAO extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "lokakar.db";
    public final static int DATABASE_VERSION = 1;

    private final static String QUERY_DELETE_TABLE_ADRESSE = "drop table if exists agences";
    private final static String QUERY_DELETE_TABLE_AGENCE = "drop table if exists agences";
    private final static String QUERY_DELETE_TABLE_CLIENT = "drop table if exists agences";
    private final static String QUERY_DELETE_TABLE_GERANT = "drop table if exists agences";
    private final static String QUERY_DELETE_TABLE_LOCATION = "drop table if exists agences";
    //  private final static String QUERY_DELETE_TABLE_PERSONNE = "drop table if exists agences";
//  private final static String QUERY_DELETE_TABLE_UTILISATION = "drop table if exists agences";
    private final static String QUERY_DELETE_TABLE_VEHICULE = "drop table if exists agences";

    private static SQLiteDatabase db;

    //Database.getInstance(AddActivity.this).addOrUpdateMusic(musiques.getContentValues());
    private static BaseDAO mInstance = null;

    public static BaseDAO getInstance(Context context) {

        if (context == null) {
            throw new IllegalArgumentException(
                    "Context is null ! Databases can't be initialized with null context");
        }

        if (mInstance == null) {
            //Log.e("database", "mInstance == null");
            mInstance = new BaseDAO(context.getApplicationContext());
            open();
        }

        return mInstance;
    }

    private BaseDAO(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION); // +1 version to upgrade database
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        AgenceDAO.createTable(db);
        AdresseDAO.createTable(db);
        ClientDAO.createTable(db);
        GerantDAO.createTable(db);
        LocationDAO.createTable(db);
        VehiculeDAO.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < newVersion) {
            db.execSQL(QUERY_DELETE_TABLE_ADRESSE);
            db.execSQL(QUERY_DELETE_TABLE_AGENCE);
            db.execSQL(QUERY_DELETE_TABLE_CLIENT);
            db.execSQL(QUERY_DELETE_TABLE_GERANT);
            db.execSQL(QUERY_DELETE_TABLE_LOCATION);
            db.execSQL(QUERY_DELETE_TABLE_VEHICULE);
            onCreate(db);
        }
    }

    private static void open() {

        if (db == null) {
            db = mInstance.getWritableDatabase();

//            if (isDBEmpty()) {
//                generateData();
//            }
        }
    }

    public synchronized void closeConnecion() {
        if (mInstance != null) {
            mInstance.close();
            db.close();
            mInstance = null;
            db = null;
        }
    }


    public static SQLiteDatabase getDB() {
        return db;
    }

    public static boolean isDBEmpty() {

        Cursor c = db.query("agences",
                new String[]{"id", "gerant_id"}, null, null, null, null, null);

        if (c.getCount() == 0) {

            return true;
        }
        return false;
    }

    public static void generateData() {

        Adresse[] adresses = {
                new Adresse("45", "rue", "Charles de Gaule", "Agence Lokakar", "42000", "Saint-Etienne", "France"),
                new Adresse("3", "rue", "des aciéries", "", "42000", "Saint-Etienne", "France"),
                new Adresse("15", "rue", "Jules Ledin", "4e étage", "42000", "Saint-Etienne", "France"),
                new Adresse("7", "avenue", "François Mitterand", "", "32000", "Auch", "France"),
                new Adresse("2", "rue", "Inkermann", "", "59000", "Lille", "France"),
                new Adresse("1", "rue", "de Clisson", "2e étage", "35000", "Rennes", "France"),
                new Adresse("105", "avenue", "Aristide Briand", "Code 27105", "35000", "Rennes", "France"),
                new Adresse("3", "rue", "Jeannne Malivel", "Appartement 168", "35000", "Rennes", "France"),
                new Adresse("131", "avenue", "Aristide Briand", "9e étage", "35000", "Rennes", "France"),
                new Adresse("1", "rue", "d'Uppsalla", "8e étage", "35200", "Rennes", "France"),
                new Adresse("5", "place", "de l'Église", "", "35230", "Saint-Armel", "France"),
                new Adresse("17", "rue", "de Saint-Malo", "", "35000", "Rennes", "France")};

        String[] photos_2081 = {"peugeot_208_1"};
        String[] photos_3081 = {"peugeot_308_1"};
        String[] photos_3082 = {"peugeot_308_2"};
        String[] photos_3083 = {"peugeot_308_3"};
        String[] photos_polo1 = {"volkswagen_polo_1"};
        String[] photos_polo2 = {"volkswagen_polo_2"};
        String[] photos_polo3 = {"volkswagen_polo_3"};
        String[] photos_golf1 = {"volkswagen_golf_1"};
        String[] photos_golf2 = {"volkswagen_golf_2"};
        String[] photos_golf3 = {"volkswagen_golf_3"};
        String[] photos_golf_71 = {"volkswagen_golf_7_1"};
        String[] photos_c31 = {"citroen_c3_1"};
        String[] photos_galaxy1 = {"ford_galaxy_1"};

        Vehicule[] vehicules = {
                new Vehicule("Peugeot", "208", "BH-638-FY", Utilisation.ROUTIERE, array2List(photos_2081), 45.0),
                new Vehicule("Volkswagen", "Polo", "DS-747-AS", Utilisation.CITADINE+Utilisation.ROUTIERE, array2List(photos_polo1), 50.0),
                new Vehicule("Volkswagen", "Polo", "AQ-050-HG", Utilisation.CITADINE+Utilisation.ROUTIERE, array2List(photos_polo2), 50.0),
                new Vehicule("Volkswagen", "Polo", "AV-458-KS", Utilisation.CITADINE+Utilisation.ROUTIERE, array2List(photos_polo3), 50.0),
                new Vehicule("Volkswagen", "Golf 7", "AC-157-FJ", Utilisation.CITADINE+Utilisation.ROUTIERE+Utilisation.FAMILIALE, array2List(photos_golf_71), 65.0),
                new Vehicule("Volkswagen", "Golf", "AM-520-LS", Utilisation.CITADINE+Utilisation.ROUTIERE, array2List(photos_golf1), 65.0),
                new Vehicule("Volkswagen", "Golf", "AY-648-GT", Utilisation.CITADINE+Utilisation.ROUTIERE, array2List(photos_golf2), 65.0),
                new Vehicule("Volkswagen", "Golf", "DX-209-FE", Utilisation.CITADINE+Utilisation.ROUTIERE+Utilisation.FAMILIALE, array2List(photos_golf3), 65.0),
                new Vehicule("Citroën", "C3", "AQS-359-LR", Utilisation.CITADINE+Utilisation.ROUTIERE+Utilisation.FAMILIALE, array2List(photos_c31), 60.0),
                new Vehicule("Ford", "Galaxy", "BD-754-QS", Utilisation.FAMILIALE, array2List(photos_galaxy1), 60.0),
                new Vehicule("Peugeot", "308", "AZ-302-DS", Utilisation.CITADINE+Utilisation.ROUTIERE+Utilisation.FAMILIALE, array2List(photos_3081), 60.0),
                new Vehicule("Peugeot", "308", "AP-402-VV", Utilisation.CITADINE+Utilisation.ROUTIERE+Utilisation.FAMILIALE, array2List(photos_3082), 60.0),
                new Vehicule("Peugeot", "308", "VH-080-AM", Utilisation.CITADINE+Utilisation.ROUTIERE+Utilisation.FAMILIALE, array2List(photos_3083), 60.0)
        };

        Personne[] p = {
                new Personne("Maujean", "Benoit", "0645782345", "bmaujean@free.fr", adresses[1]),
                new Personne("Lux", "Guy", "0423568912", "glux@laposte.fr", adresses[2]),
                new Personne("Sevestre", "Sébastien", "0614658437", "ssevestre@orange.fr", adresses[3]),
                new Personne("Aubry", "Martine", "0645789412", "maubry@orange.fr", adresses[4]),
                new Personne("Henry", "Marc", "0645789412", "marc.henri@free.fr", adresses[5]),
                new Personne("Stahl", "Robert", "0680452197", "rstahl@orange.fr", adresses[6]),
                new Personne("Remaud", "Pierre", "0650535521", "pieremaud@gmail.com", adresses[7]),
                new Personne("Lebrun", "Béatrice", "0622564772", "bealebrun@gmail.com", adresses[8]),
                new Personne("Mazé", "Benoît", "0687526028", "maze.benoit@yahoo.fr", adresses[9]),
                new Personne("Téhel", "Marie-Josèphe", "0621767583", "mjtehel@free.fr", adresses[10]),
                new Personne("Vergne", "Anne-France", "0662679802", "annefrance.vergne@club-internet.fr", adresses[11])
        };

        Gerant gerant = new Gerant(p[0], "0620547894", "bmaujean@lokakar.fr", "1234");

        Client[] clients = new Client[p.length-1];

        for (int i=0;i<p.length-1;i++) {
            clients[i] = new Client(p[i+1]);
        }

        Location [] locations = {
                new Location(vehicules[0], "05/03/2017 09:00", "03/08/2017 18:00", clients[0], new ArrayList<String>(), 0),
                new Location(vehicules[1], "19/06/2017 09:00", "25/06/2017 18:00", clients[0], new ArrayList<String>(), 1),
                new Location(vehicules[2], "24/06/2017 12:00", "30/06/2017 12:00", clients[1], new ArrayList<String>(), 0)
        };
        // Archivage des locations effectuées et terminées
//        clients[0].getLocationList().add(locations[0]);
//        clients[0].getLocationList().add(locations[1]);

        Agence agence = new Agence(gerant, array2List(vehicules), array2List(locations), adresses[0]);
        Log.d("*** Agence ***", agence.toString());

        long r;
        for (Adresse item : adresses) {
            r = AdresseDAO.insertAdresse(item);
            item.setId((int) r);
            Log.d("*** New Data ***", "Inserted Adresse's id : " + r);
        }
        for (Vehicule item : vehicules) {
            r = VehiculeDAO.insertVehicule(item);
            item.setId((int) r);
            Log.d("*** New Data ***", "Inserted Vehicule's id : " + r);
        }

        r = GerantDAO.insertGerant(gerant);
        gerant.setId((int) r);
        Log.d("*** New Data ***", "Inserted Gerant's id : " + r);
        for (Client item : clients) {
            r = ClientDAO.insertClient(item);
            item.setId((int) r);
            Log.d("*** New Data ***", "Inserted Client's id : " + r);
        }
        for (Location item : locations) {
            r = LocationDAO.insertLocation(item);
            item.setId((int) r);
            Log.d("*** New Data ***", "Inserted Location's id : " + r);
        }

        r = AgenceDAO.insertAgence(agence);
        agence.setId((int) r);
        Log.d("*** New Data ***", "Inserted Agence's id : " + r);
    }

    protected static List<String> array2List(String[] tab) {

        List<String> list = new ArrayList<>();

        for (String item : tab) {
            list.add(item);
        }
        return list;
    }

    protected static List<Vehicule> array2List(Vehicule[] tab) {

        List<Vehicule> list = new ArrayList<>();

        for (Vehicule item : tab) {
            list.add(item);
        }
        return list;
    }

    protected static List<Location> array2List(Location[] tab) {

        List<Location> list = new ArrayList<>();

        for (Location item : tab) {
            list.add(item);
        }
        return list;
    }
}

