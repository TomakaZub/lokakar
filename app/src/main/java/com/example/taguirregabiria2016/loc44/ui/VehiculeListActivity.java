package com.example.taguirregabiria2016.loc44.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.VehiculeDAO;
import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.util.List;

public class VehiculeListActivity extends AppCompatActivity {

    ListView mListView;
    List<Vehicule> vehicules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicule_list);

        mListView = (ListView) findViewById(R.id.vehiculeList);

        vehicules = VehiculeDAO.getAllVehicules();

        VehiculeAdapter adapter = new VehiculeAdapter(VehiculeListActivity.this, vehicules);
        mListView.setAdapter(adapter);
    }
}
