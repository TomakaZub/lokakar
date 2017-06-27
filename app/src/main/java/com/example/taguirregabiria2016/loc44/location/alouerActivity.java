package com.example.taguirregabiria2016.loc44.location;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.LocationDAO;
import com.example.taguirregabiria2016.loc44.dao.VehiculeDAO;
import com.example.taguirregabiria2016.loc44.gererParking.VehiculeAdapter;
import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.util.List;

public class alouerActivity extends AppCompatActivity {

    ArrayAdapter adapter;
    private List<Vehicule> liste_vehicule;
    LocationDAO daoLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alouer);

        this.daoLocation = new LocationDAO();


//        liste_vehicule = dao.getALouerVehicules();
        liste_vehicule = daoLocation.getVehicules_ALouer();

        adapter = new VehiculeAdapter(this, R.layout.template_list_a_louer, liste_vehicule);
        final ListView listView = (ListView) findViewById(R.id.list_alouer);
        listView.setAdapter(adapter);

    }
}
