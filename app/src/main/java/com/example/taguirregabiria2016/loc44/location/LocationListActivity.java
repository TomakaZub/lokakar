package com.example.taguirregabiria2016.loc44.location;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.LocationDAO;
import com.example.taguirregabiria2016.loc44.model.Location;

import java.util.List;

public class LocationListActivity extends AppCompatActivity {

    ListView mListView;
    List<Location> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        mListView = (ListView) findViewById(R.id.locationList);

        locations = LocationDAO.getAllLocations();

        Log.d("*** loc activity ***", locations.toString());

        LocationAdapter adapter = new LocationAdapter(LocationListActivity.this, locations);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_location_list);

        mListView = (ListView) findViewById(R.id.locationList);
        locations = LocationDAO.getAllLocations();

        LocationAdapter adapter = new LocationAdapter(LocationListActivity.this, locations);
        mListView.setAdapter(adapter);
    }

    public void editLocation(View view) {

    }

    public void deleteLocation(View view) {

        final Location location = (Location) view.getTag();

//        Log.e(TAG, "vehicule: "+vehicule.getId());
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Souhaiter vraimment supprimer cette voiture !");
        alertDialogBuilder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                LocationDAO.removeLocation(location);
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
        int id = item.getItemId();

        switch (id) {
            case R.id.action_Ajouter:
                Intent intent = new Intent(LocationListActivity.this, ClientFormActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
