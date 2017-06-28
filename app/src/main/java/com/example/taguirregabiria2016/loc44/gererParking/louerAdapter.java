package com.example.taguirregabiria2016.loc44.gererParking;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.util.List;

/**
 * Created by spezeron2016 on 27/06/2017.
 */

public class louerAdapter extends ArrayAdapter<Location> {
    private List<Location> locations;
    private int layout;
    private Resources res;

    public louerAdapter(Context context, int resource, List<Location> objects) {
        super(context, resource, objects);
        this.locations = objects;
        this.layout = resource;
        this.res = context.getResources();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
        {
            view = inflater.inflate(layout,parent,false);
        }else
        {
            view = convertView;
        }

        Location location = locations.get(position);

        TextView modele_vehicule = (TextView) view.findViewById(R.id.modele_vehicule);
        TextView immatriculation_vehicule = (TextView) view.findViewById(R.id.immatriculation_vehicule);
        TextView infoClient = (TextView) view.findViewById(R.id.infoClient);

        modele_vehicule.setText(location.getVehicule().getMarque()+ " "+location.getVehicule().getModele());
        immatriculation_vehicule.setText(location.getVehicule().getImmatriculation());
        infoClient.setText(location.getClient().getNom() +" "+location.getClient().getPrenom());
        return view;
    }
}
