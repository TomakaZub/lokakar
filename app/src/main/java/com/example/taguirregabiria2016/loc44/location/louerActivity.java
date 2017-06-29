package com.example.taguirregabiria2016.loc44.location;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.LocationDAO;
import com.example.taguirregabiria2016.loc44.gererParking.VehiculeAdapter;
import com.example.taguirregabiria2016.loc44.gererParking.louerAdapter;
import com.example.taguirregabiria2016.loc44.model.Client;
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.util.List;
import java.util.ListIterator;

public class louerActivity extends AppCompatActivity {

    ArrayAdapter adapter;
    LocationDAO daoLocation;
    private List<Location> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_louer);

        this.daoLocation = new LocationDAO();
        locations = daoLocation.getLocations();
        if(locations.isEmpty())
        {
            TextView tv = (TextView) findViewById(R.id.msg);
            tv.setVisibility(View.VISIBLE);
        }
        else {
            adapter = new louerAdapter(louerActivity.this, R.layout.template_list_louer, locations);
            final ListView listView = (ListView) findViewById(R.id.list_louer);
            listView.setAdapter(adapter);
        }



    }
}
