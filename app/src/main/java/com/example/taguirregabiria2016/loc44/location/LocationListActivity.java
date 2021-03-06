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
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.LocationDAO;
import com.example.taguirregabiria2016.loc44.model.Client;
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.utils.Tools;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class LocationListActivity extends AppCompatActivity {

    ListView mListView;
    List<Location> locations;
    TextView locationCount;
    CheckBox locationArchived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        mListView = (ListView) findViewById(R.id.locationList);
        locationArchived = (CheckBox) findViewById(R.id.locationArchived);
        locationCount = (TextView) findViewById(R.id.locationCount);

        locations = LocationDAO.getAllLocations("debut DESC");

        LocationAdapter adapter = new LocationAdapter(LocationListActivity.this, locations);
        mListView.setAdapter(adapter);

        locationCount.setText(String.format(Locale.FRANCE, "%d élément%s",locations.size(), (locations.size()>1?"s":"")));

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Location location = locations.get(position);
//                Intent intent = new Intent(LocationListActivity.this, ResumeLocationActivity.class);
//                intent.putExtra("resume", location.getId());
//                startActivity(intent);
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        locations = LocationDAO.getAllLocations();

        LocationAdapter adapter = new LocationAdapter(LocationListActivity.this, locations);
        mListView.setAdapter(adapter);
        if (locationArchived.isChecked()) {
            locations = LocationDAO.getAllOpenLocations();
        } else {
            locations = LocationDAO.getAllLocations();
        }
        locationCount.setText(String.format(Locale.FRANCE, "%d élément%s",locations.size(), (locations.size()>1?"s":"")));
    }

    public void editLocation(View view) {
        final Location editLocation = (Location) view.getTag();

        Intent intent = new Intent(LocationListActivity.this, LocationFormActivity.class);
        intent.putExtra("location", editLocation.getId());
        startActivity(intent);
    }

    public void resumeLocation(View view) {
        final Location resumeLocation = (Location) view.getTag();

        Intent intent = new Intent(LocationListActivity.this, ResumeLocationActivity.class);
        intent.putExtra("resume", resumeLocation.getId());
        startActivity(intent);
    }

    public void deleteLocation(final View view) {

        final Location toBeDeletedLocation = (Location) view.getTag();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Souhaiter Clôturer ce dossier ?");
        alertDialogBuilder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                Calendar today = Calendar.getInstance();
                String fin = Tools.Cal2Str(today);
                toBeDeletedLocation.setRendu(1);
                toBeDeletedLocation.setFin(fin);
                LocationDAO.updateLocation(toBeDeletedLocation);
                LocationAdapter adapter = (LocationAdapter)mListView.getAdapter();
                adapter.notifyDataSetChanged();// .remove(toBeDeletedLocation);
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

/*package com.example.taguirregabiria2016.loc44.location;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.LocationDAO;
import com.example.taguirregabiria2016.loc44.model.Client;
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.utils.Tools;

import java.util.Calendar;
import java.util.List;

public class LocationListActivity extends AppCompatActivity {

    ListView mListView;
    List<Location> locations;
    TextView count;
    CheckBox archived;

    SwipeLayout sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        mListView = (ListView) findViewById(R.id.locationList);
        archived = (CheckBox) findViewById(R.id.archived);
        count = (TextView) findViewById(R.id.count);

        if (archived.isChecked()) {
            locations = LocationDAO.getAllOpenLocations();
        } else {
            locations = LocationDAO.getAllLocations();
        }

        int c = locations.size();
        count.setText(c + " élément" + (c > 1 ? "s" : ""));
        LocationAdapter adapter = new LocationAdapter(LocationListActivity.this, locations);
        mListView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (archived.isChecked()) {
            locations = LocationDAO.getAllOpenLocations();
        } else {
            locations = LocationDAO.getAllLocations();
        }

        int c = locations.size();
        count.setText(c + " élément" + (c > 1 ? "s" : ""));
        LocationAdapter adapter = new LocationAdapter(LocationListActivity.this, locations);
        mListView.setAdapter(adapter);
    }

    public void editLocation(View view) {
        final Location editLocation = (Location) view.getTag();

        Intent intent = new Intent(LocationListActivity.this, LocationFormActivity.class);
        intent.putExtra("location", editLocation.getId());
        startActivity(intent);
    }

    public void resumeLocation(View view) {
        final Location resumeLocation = (Location) view.getTag();

        Intent intent = new Intent(LocationListActivity.this, ResumeLocationActivity.class);
        intent.putExtra("resume", resumeLocation.getId());
        startActivity(intent);
    }

    public void deleteLocation(final View view) {

        final Location toBeDeletedLocation = (Location) view.getTag();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Souhaiter Clôturer ce dossier ?");
        alertDialogBuilder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                Calendar today = Calendar.getInstance();
                String fin = Tools.Cal2Str(today);
                toBeDeletedLocation.setRendu(1);
                toBeDeletedLocation.setFin(fin);
                LocationDAO.updateLocation(toBeDeletedLocation);
//                LocationAdapter adapter = (LocationAdapter)mListView.getAdapter();
//                adapter. .remove(toBeDeletedLocation);
            }
        });
        alertDialogBuilder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
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

    public void reload(View view) {

        if (archived.isChecked()) {
            locations = LocationDAO.getAllOpenLocations();
        } else {
            locations = LocationDAO.getAllLocations();
        }

        int c = locations.size();
        count.setText(c + " élément" + (c > 1 ? "s" : ""));
        LocationAdapter adapter = new LocationAdapter(LocationListActivity.this, locations);
        mListView.setAdapter(adapter);
    }
}
*/