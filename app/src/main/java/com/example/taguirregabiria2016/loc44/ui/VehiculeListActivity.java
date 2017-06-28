package com.example.taguirregabiria2016.loc44.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.VehiculeDAO;
import com.example.taguirregabiria2016.loc44.gererParking.GererParkingActivity;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_liste, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_Ajouter:
                Intent intent = new Intent(VehiculeListActivity.this, VehiculeFormActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
