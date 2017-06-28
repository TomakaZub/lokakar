package com.example.taguirregabiria2016.loc44.gererParking;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.util.List;

/**
 * Created by spezeron2016 on 27/06/2017.
 */

public class VehiculeAdapter extends ArrayAdapter<Vehicule> {
    private List<Vehicule> listeVehicule;
    private int layout;
    private Resources res;

    public VehiculeAdapter(Context context, int resource, List<Vehicule> objects) {
        super(context, resource, objects);
        this.listeVehicule = objects;
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

        Vehicule vehicule = listeVehicule.get(position);

        Button buttonModify = (Button)view.findViewById(R.id.buttonModify);
        Button buttonDelete = (Button)view.findViewById(R.id.buttonDelete);

        buttonModify.setTag(vehicule);
        buttonDelete.setTag(vehicule);

        TextView modele_vehicule = (TextView) view.findViewById(R.id.modele_vehicule);
        TextView immatriculation_vehicule = (TextView) view.findViewById(R.id.immatriculation_vehicule);
        TextView tarif_vehicule = (TextView) view.findViewById(R.id.tarif_vehicule);
        modele_vehicule.setText(vehicule.getModele());


        Button buttonModify = (Button) view.findViewById(R.id.buttonModify);
        buttonModify.setTag(vehicule);

        Button buttonDelete = (Button) view.findViewById(R.id.buttonDelete);
        buttonDelete.setTag(vehicule);

        modele_vehicule.setText(vehicule.getMarque()+ " "+vehicule.getModele());
        immatriculation_vehicule.setText(vehicule.getImmatriculation());
        tarif_vehicule.setText(String.valueOf(vehicule.getPrixJour() + "0€/Jours"));


        return view;
    }
}
