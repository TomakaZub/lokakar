package com.example.taguirregabiria2016.loc44.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.model.Utilisation;
import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.util.List;

/**
 * Created by ojeanmarie2016 on 27/06/2017.
 */

public class VehiculeAdapter extends ArrayAdapter<Vehicule> {

    public VehiculeAdapter(@NonNull Context context, @NonNull List<Vehicule> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vehicule_list_item, parent, false);
        }

        VehiculeHolder viewHolder = (VehiculeHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new VehiculeHolder();
            viewHolder.ligne = (LinearLayout) convertView.findViewById(R.id.fond);
            viewHolder.brand = (TextView) convertView.findViewById(R.id.vehiculeBrand);
            viewHolder.model = (TextView) convertView.findViewById(R.id.vehiculeModel);
            viewHolder.registration = (TextView) convertView.findViewById(R.id.vehiculeRegistration);
            viewHolder.utilisation = (TextView) convertView.findViewById(R.id.vehiculeUtilisation);
            convertView.setTag(viewHolder);
        }

        Vehicule vehicule = getItem(position);

        int color = ((position % 2 == 0) ? R.color.userList1 : R.color.userList2);
        viewHolder.ligne.setBackgroundColor(convertView.getResources().getColor(color));

        String utilisation="";
        utilisation += (vehicule.getUtilisation()& Utilisation.CITADINE)==Utilisation.CITADINE ?"C":"-";
        utilisation += (vehicule.getUtilisation()& Utilisation.FAMILIALE)==Utilisation.FAMILIALE ?"F":"-";
        utilisation += (vehicule.getUtilisation()& Utilisation.MONOSPACE)==Utilisation.MONOSPACE ?"M":"-";
        utilisation += (vehicule.getUtilisation()& Utilisation.ROUTIERE)==Utilisation.ROUTIERE ?"R":"-";
        utilisation += (vehicule.getUtilisation()& Utilisation.SPORTIVE)==Utilisation.SPORTIVE ?"S":"-";
        utilisation += (vehicule.getUtilisation()& Utilisation.UTILITAIRE)==Utilisation.UTILITAIRE ?"U":"-";

        viewHolder.brand.setText(vehicule.getMarque());
        viewHolder.model.setText(vehicule.getModele());
        viewHolder.registration.setText(vehicule.getImmatriculation());
        viewHolder.utilisation.setText(utilisation);

        return convertView;
    }


    private class VehiculeHolder {
        public LinearLayout ligne;
        public TextView brand;
        public TextView model;
        public TextView registration;
        public TextView utilisation;
    }


}
