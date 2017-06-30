package com.example.taguirregabiria2016.loc44.location;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.model.Utilisation;
import com.example.taguirregabiria2016.loc44.model.Vehicule;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by spezeron2016 on 27/06/2017.
 */

public class alouerAdapter extends ArrayAdapter<Vehicule> {
    private List<Vehicule> listeVehicule;
    private int layout;
    private Resources res;
    private Context context;
    private static final String TAG = "Picasso";

    public alouerAdapter(Context context, int resource, List<Vehicule> objects) {
        super(context, resource, objects);
        this.listeVehicule = objects;
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

        Vehicule vehicule = listeVehicule.get(position);
//
//        Button buttonModify = (Button)view.findViewById(R.id.buttonModify);
//        Button buttonDelete = (Button)view.findViewById(R.id.buttonDelete);
//        buttonModify.setTag(vehicule);
//        buttonDelete.setTag(vehicule);

        TextView modele_vehicule = (TextView) view.findViewById(R.id.modele_vehicule);
        TextView immatriculation_vehicule = (TextView) view.findViewById(R.id.immatriculation_vehicule);
        TextView tarif_vehicule = (TextView) view.findViewById(R.id.tarif_vehicule);
        TextView utilsation_vehicule = (TextView) view.findViewById(R.id.vehiculeUtilisation);
        final ImageView thumbnail_vehicule = (ImageView) view.findViewById(R.id.thumbnail);

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

        final String filePath = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"/"+vehicule.getId()+".jpg";
        if (vehicule.getAlbum().size()>0) {
            Uri uri = Uri.fromFile(new File(filePath));

            Picasso.with(getContext()).load(uri).into(thumbnail_vehicule, new Callback() {
                @Override
                public void onSuccess() {
                    Log.e(TAG, "OK "+filePath);
                }

                @Override
                public void onError() {
                    Log.e(TAG, "ERROR "+filePath);
                    thumbnail_vehicule.setImageResource(R.drawable.car);

                }
            });
        }


        LinearLayout ligne = (LinearLayout)view.findViewById(R.id.fond);
        int color = ((position % 2 == 0) ? R.color.userList1 : R.color.userList2);
        ligne.setBackgroundColor(view.getResources().getColor(color));


        return view;
    }

}
