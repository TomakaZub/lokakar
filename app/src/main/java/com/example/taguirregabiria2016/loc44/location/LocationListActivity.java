package com.example.taguirregabiria2016.loc44.location;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

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
}
