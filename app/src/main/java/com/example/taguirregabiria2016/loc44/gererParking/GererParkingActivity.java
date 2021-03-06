package com.example.taguirregabiria2016.loc44.gererParking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.LocationDAO;
import com.example.taguirregabiria2016.loc44.dao.VehiculeDAO;
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.model.Vehicule;
import com.example.taguirregabiria2016.loc44.ui.VehiculeFormActivity;

import java.util.List;

public class GererParkingActivity extends AppCompatActivity {

    ArrayAdapter adapter;
    private List<Vehicule> vehiculeList;
    private List<Location> locationList;
    ListView listView;
    VehiculeDAO dao;
    Vehicule vehicule;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerer_parking);

        this.dao = new VehiculeDAO();
        vehiculeList = dao.getAllVehicules();

        locationList = LocationDAO.getAllOpenLocations();

        for (Location item: locationList) {

            if (vehiculeList.contains(item)) {
                vehiculeList.remove(item);
            }
        }

        int count = vehiculeList.size();
        TextView tvInfos = (TextView)findViewById(R.id.infos);
        tvInfos.setText(count+" véhicule" + ((count>1)?"s":""));
        adapter = new VehiculeAdapter(this, R.layout.template_list_vehicule, vehiculeList);
        listView = (ListView) findViewById(R.id.listeVehicule);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        vehiculeList = dao.getAllVehicules();
        adapter = new VehiculeAdapter(this, R.layout.template_list_vehicule, vehiculeList);
        final ListView listView = (ListView) findViewById(R.id.listeVehicule);
        listView.setAdapter(adapter);
    }


    public void edit(View view) {

        Toast.makeText(GererParkingActivity.this, "Edit", Toast.LENGTH_SHORT);
    }

    public void delete(final View view) {

        vehicule = (Vehicule) view.getTag();

//        Log.e(TAG, "vehicule: "+vehicule.getId());
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Souhaiter vraimment supprimer cette voiture !");
        alertDialogBuilder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                dao.removeVehicule(vehicule);
                Toast.makeText(GererParkingActivity.this, "Véhicule supprimé", Toast.LENGTH_LONG).show();
                VehiculeAdapter adapter = (VehiculeAdapter)listView.getAdapter();
                adapter.remove(vehicule);

            }
        });
        alertDialogBuilder.setNegativeButton("NON",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_liste, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_Ajouter:
                Intent intent = new Intent(GererParkingActivity.this, VehiculeFormActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
