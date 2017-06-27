package com.example.taguirregabiria2016.loc44.gererParking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.VehiculeDAO;
import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.util.List;

public class GererParkingActivity extends AppCompatActivity {

    ArrayAdapter adapter;
    private  List<Vehicule> liste_vehicule;
    VehiculeDAO dao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerer_parking);

        this.dao = new VehiculeDAO();
        liste_vehicule = dao.getAllVehicules();

        adapter = new VehiculeAdapter(this, R.layout.template_list_vehicule, liste_vehicule);
        final ListView listView = (ListView) findViewById(R.id.listeVehicule);

        listView.setAdapter(adapter);
    }
}
