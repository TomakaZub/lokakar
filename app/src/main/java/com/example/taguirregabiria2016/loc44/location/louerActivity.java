package com.example.taguirregabiria2016.loc44.location;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.LocationDAO;
import com.example.taguirregabiria2016.loc44.model.Location;

import java.util.List;

public class louerActivity extends AppCompatActivity {

    ArrayAdapter adapter;
    LocationDAO daoLocation;
    private List<Location> locations;
    ListView listView;

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
            listView = (ListView) findViewById(R.id.list_louer);
            listView.setAdapter(adapter);
        }



    }

    /**
     * mettre a jour le statut rendu a 0
     * @param view
     */
    public void rendu(View view) {
        Button buttonRendu = (Button)view.findViewById(R.id.buttonRendu);
        Location location = (Location) buttonRendu.getTag();
        location.setRendu(1);

        LocationDAO.updateLocation(location);

        Toast.makeText(louerActivity.this,"Vehicule rendu !",Toast.LENGTH_LONG).show();
        adapter = (louerAdapter) listView.getAdapter();
        adapter.remove(location);
        listView.setAdapter(adapter);
    }

}
