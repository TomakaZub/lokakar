package com.example.taguirregabiria2016.loc44.chiffreAffaire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.location.LocationFormActivity;
import com.example.taguirregabiria2016.loc44.location.LocationListActivity;
import com.example.taguirregabiria2016.loc44.ui.UserFormActivity;
import com.example.taguirregabiria2016.loc44.ui.UserListActivity;
import com.example.taguirregabiria2016.loc44.ui.VehiculeFormActivity;
import com.example.taguirregabiria2016.loc44.ui.VehiculeListActivity;

public class caActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca);
    }

    public void ajouterUnClient(View view) {
        Intent intent = new Intent(caActivity.this, UserFormActivity.class);
        startActivity(intent);
    }

    public void listerLesClients(View view) {
        Intent intent = new Intent(caActivity.this, UserListActivity.class);
        startActivity(intent);
    }

    public void ajouterUnVehicule(View view) {
        Intent intent = new Intent(caActivity.this, VehiculeFormActivity.class);
        startActivity(intent);
    }

    public void listerLesVehicules(View view) {
        Intent intent = new Intent(caActivity.this, VehiculeListActivity.class);
        startActivity(intent);
    }

    public void ajouterUneLocation(View view) {
        Intent intent = new Intent(caActivity.this, LocationFormActivity.class);
        startActivity(intent);
    }

    public void listerLesLocations(View view) {
        Intent intent = new Intent(caActivity.this, LocationListActivity.class);
        startActivity(intent);
    }
}
