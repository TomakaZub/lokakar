package com.example.taguirregabiria2016.loc44.search;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.model.Utilisation;
import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.util.List;

/**
 * Created by spezeron2016 on 27/06/2017.
 */

public class searchAdapter extends ArrayAdapter<Vehicule> {
    private int layout;
    private Resources res;
    private Context context;

    public searchAdapter(Context context, int resource, List<Vehicule> objects) {
        super(context, resource, objects);
        this.layout = resource;
        this.res = context.getResources();
        this.context = context;
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

        Vehicule vehicule = getItem(position);

        TextView modele_vehicule = (TextView) view.findViewById(R.id.modele_vehicule);
        TextView immatriculation_vehicule = (TextView) view.findViewById(R.id.immatriculation_vehicule);
        TextView tarif_vehicule = (TextView) view.findViewById(R.id.tarif_vehicule);
        TextView utilsation_vehicule = (TextView) view.findViewById(R.id.vehiculeUtilisation);
        ImageView thumbnail_vehicule = (ImageView) view.findViewById(R.id.thumbnail);

        modele_vehicule.setText(vehicule.getMarque()+ " "+vehicule.getModele());
        immatriculation_vehicule.setText(vehicule.getImmatriculation());
        tarif_vehicule.setText(String.valueOf(vehicule.getPrixJour() + "0€/Jours"));

        String utilisation="";
        utilisation += (vehicule.getUtilisation()& Utilisation.CITADINE)==Utilisation.CITADINE ?"C":"-";
        utilisation += (vehicule.getUtilisation()& Utilisation.FAMILIALE)==Utilisation.FAMILIALE ?"F":"-";
        utilisation += (vehicule.getUtilisation()& Utilisation.MONOSPACE)==Utilisation.MONOSPACE ?"M":"-";
        utilisation += (vehicule.getUtilisation()& Utilisation.ROUTIERE)==Utilisation.ROUTIERE ?"R":"-";
        utilisation += (vehicule.getUtilisation()& Utilisation.SPORTIVE)==Utilisation.SPORTIVE ?"S":"-";
        utilisation += (vehicule.getUtilisation()& Utilisation.UTILITAIRE)==Utilisation.UTILITAIRE ?"U":"-";
        utilsation_vehicule.setText(utilisation);

        if (vehicule.getAlbum().size()>0) {
            String dummy = vehicule.getAlbum().get(0);
            int resId = res.getIdentifier("ic_" + dummy, "drawable", context.getPackageName());
            thumbnail_vehicule.setImageResource(resId);

        }
        LinearLayout ligne = (LinearLayout)view.findViewById(R.id.fond);
        int color = ((position % 2 == 0) ? R.color.userList1 : R.color.userList2);
        ligne.setBackgroundColor(view.getResources().getColor(color));


        return view;
    }

}
