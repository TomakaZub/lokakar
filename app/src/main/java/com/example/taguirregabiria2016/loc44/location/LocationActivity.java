package com.example.taguirregabiria2016.loc44.location;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.chiffreAffaire.caActivity;
import com.example.taguirregabiria2016.loc44.dao.ClientDAO;
import com.example.taguirregabiria2016.loc44.dao.LocationDAO;
import com.example.taguirregabiria2016.loc44.dao.VehiculeDAO;
import com.example.taguirregabiria2016.loc44.model.Client;
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.model.Vehicule;
import com.example.taguirregabiria2016.loc44.ui.UserFormActivity;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {

    private static EditText startDate;
    private static EditText startTime;
    private static EditText endDate;
    private static EditText endTime;

    private static Spinner spVehicule;
    private static Spinner spClient;

    private static List<Vehicule> vehiculeList;
    private static List<Client> clientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        spVehicule = (Spinner) findViewById(R.id.vehiculeList);
        vehiculeList = VehiculeDAO.getAllVehicules();
        List<String> spVehiculeItems = new ArrayList<>();

        for (Vehicule item : vehiculeList) {
            spVehiculeItems.add(item.toSpinnerItem());
        }
        ArrayAdapter<String> vAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spVehiculeItems);
        vAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spVehicule.setAdapter(vAdapter);

        spClient = (Spinner) findViewById(R.id.clientList);
        clientList = ClientDAO.getAllClients();
        List<String> spClientItems = new ArrayList<>();

        for (Client item : clientList) {
            spClientItems.add(item.toSpinnerItem());
        }
        ArrayAdapter<String> cAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spClientItems);
        cAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spClient.setAdapter(cAdapter);

        startDate = (EditText) findViewById(R.id.startDate);
        startTime = (EditText) findViewById(R.id.startTime);
        endDate = (EditText) findViewById(R.id.endDate);
        endTime = (EditText) findViewById(R.id.endTime);

    }

    @Override
    protected void onResume() {
        super.onResume();

        clientList = ClientDAO.getAllClients();
        List<String> spClientItems = new ArrayList<>();

        for (Client item : clientList) {
            spClientItems.add(item.toSpinnerItem());
        }
        ArrayAdapter<String> cAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spClientItems);
        cAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spClient.setAdapter(cAdapter);
    }

    public void pickStart(View view) {

        Toast.makeText(LocationActivity.this, "Pick Start", Toast.LENGTH_LONG).show();
    }

    public void pickEnd(View view) {

        Toast.makeText(LocationActivity.this, "Pick End", Toast.LENGTH_LONG).show();
    }

    public void ajouterUneLocationn(View view) {

        Vehicule vehicule = vehiculeList.get(spVehicule.getSelectedItemPosition());
        Client client = clientList.get(spClient.getSelectedItemPosition());
        String debutDate = startDate.getText().toString();
        String debutTime = startTime.getText().toString();
        String finDate = endDate.getText().toString();
        String finTime = endTime.getText().toString();

        Log.d("Ajout Location", "\t" + client.toSpinnerItem() + " :\n\t\tdu " + debutDate + " à " + debutTime + "\n\t\tau " + finDate + " à " + finTime + "\n\t\t" + vehicule.toSpinnerItem());

        Location location = new Location(vehicule, debutDate + " " + debutTime, finDate + " " + finTime, client, new ArrayList<String>(), 0);
        location.setId((int)LocationDAO.insertLocation(location));

        Log.d("Location ajoutée", location.toString());
        finish();
    }

    public void addClient(View view) {

        Intent intent = new Intent(LocationActivity.this, UserFormActivity.class);
        startActivity(intent);
    }
}
