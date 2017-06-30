package com.example.taguirregabiria2016.loc44.location;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.utils.Tools;

import java.util.List;
import java.util.Locale;

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
            viewHolder.ligne = (SwipeLayout) convertView.findViewById(R.id.fond);
            viewHolder.debut = (TextView) convertView.findViewById(R.id.debut);
            viewHolder.fin = (TextView) convertView.findViewById(R.id.fin);
            viewHolder.vehicule = (TextView) convertView.findViewById(R.id.vehicule);
            viewHolder.client = (TextView) convertView.findViewById(R.id.client);
            viewHolder.rendu = (TextView) convertView.findViewById(R.id.rendu);
            viewHolder.registration = (TextView) convertView.findViewById(R.id.registration);
            viewHolder.prix = (TextView) convertView.findViewById(R.id.prix);
            convertView.setTag(viewHolder);
        }

        Location location = getItem(position);

        int color = ((position % 2 == 0) ? R.color.userList1 : R.color.userList2);
        viewHolder.ligne.setBackgroundColor(convertView.getResources().getColor(color));

        viewHolder.debut.setText(Tools.humanDate(location.getDebut()));
        viewHolder.fin.setText(Tools.humanDate(location.getFin()));
        viewHolder.vehicule.setText(location.getVehicule().getMarque() + " " + location.getVehicule().getModele());
        viewHolder.registration.setText(location.getVehicule().getImmatriculation());
        viewHolder.client.setText(location.getClient().getPrenom() + " " + location.getClient().getNom().toUpperCase());
        viewHolder.rendu.setText(location.getRendu()==1?"Archivé":("futur".equals(Tools.daysOfFuturePast(location.getDebut()))?"À venir":("futur".equals(Tools.daysOfFuturePast(location.getFin())))?"En cours":"Passé!!"));
        viewHolder.rendu.setTextColor(convertView.getResources().getColor(R.color.white));
        viewHolder.rendu.setBackground(convertView.getResources().getDrawable(location.getRendu()==1?R.drawable.pellet_orange:("futur".equals(Tools.daysOfFuturePast(location.getDebut()))?R.drawable.pellet_green:("futur".equals(Tools.daysOfFuturePast(location.getFin())))?R.drawable.pellet_blue:R.drawable.pellet_red)));

        viewHolder.prix.setText(String.format(Locale.FRANCE, "%.00f€", Tools.getPrice(location)));

        Button buttonResume = (Button) convertView.findViewById(R.id.buttonResume);
        Button buttonModify = (Button) convertView.findViewById(R.id.buttonModify);
        Button buttonDelete = (Button) convertView.findViewById(R.id.buttonDelete);

        buttonResume.setTag(location);
        buttonModify.setTag(location);
        buttonDelete.setTag(location);

        return convertView;
    }


    private class LocationHolder {
        public SwipeLayout ligne;
        public TextView debut;
        public TextView fin;
        public TextView vehicule;
        public TextView registration;
        public TextView client;
        public TextView rendu;
        public TextView prix;
    }


}
