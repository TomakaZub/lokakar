package com.example.taguirregabiria2016.loc44.location;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.model.Utilisation;
import com.example.taguirregabiria2016.loc44.model.Location;

import java.util.List;

/**
 * Created by ojeanmarie2016 on 27/06/2017.
 */

public class LocationAdapter extends ArrayAdapter<Location> {

    public LocationAdapter(@NonNull Context context, @NonNull List<Location> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.location_list_item, parent, false);
        }

        LocationHolder viewHolder = (LocationHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new LocationHolder();
            viewHolder.ligne = (LinearLayout) convertView.findViewById(R.id.fond);
            viewHolder.debut = (TextView) convertView.findViewById(R.id.debut);
            viewHolder.fin = (TextView) convertView.findViewById(R.id.fin);
            viewHolder.vehicule = (TextView) convertView.findViewById(R.id.vehicule);
            viewHolder.client = (TextView) convertView.findViewById(R.id.client);
            convertView.setTag(viewHolder);
        }

        Location Location = getItem(position);

        int color = ((position % 2 == 0) ? R.color.userList1 : R.color.userList2);
        viewHolder.ligne.setBackgroundColor(convertView.getResources().getColor(color));

        viewHolder.debut.setText(Location.getDebut());
        viewHolder.fin.setText(Location.getFin());
        viewHolder.vehicule.setText(Location.getVehicule().getMarque()+" "+Location.getVehicule().getModele());
        viewHolder.client.setText(Location.getClient().getPrenom()+" "+Location.getClient().getNom());

        return convertView;
    }


    private class LocationHolder {
        public LinearLayout ligne;
        public TextView debut;
        public TextView fin;
        public TextView vehicule;
        public TextView client;
    }


}
