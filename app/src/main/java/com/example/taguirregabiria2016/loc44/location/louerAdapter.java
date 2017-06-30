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
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.model.Vehicule;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by spezeron2016 on 27/06/2017.
 */

public class louerAdapter extends ArrayAdapter<Location> {
    private List<Location> locations;
    private int layout;
    private Resources res;
    private Context context;

    public louerAdapter(Context context, int resource, List<Location> objects) {
        super(context, resource, objects);
        this.locations = objects;
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

        Location location = locations.get(position);

        Button buttonRendu = (Button)view.findViewById(R.id.buttonRendu);
        buttonRendu.setTag(location);

        TextView modele_vehicule = (TextView) view.findViewById(R.id.modele_vehicule);
        TextView immatriculation_vehicule = (TextView) view.findViewById(R.id.immatriculation_vehicule);
        TextView infoClient = (TextView) view.findViewById(R.id.infoClient);
        final ImageView thumbnail_vehicule = (ImageView) view.findViewById(R.id.thumbnail);

        modele_vehicule.setText(location.getVehicule().getMarque()+ " "+location.getVehicule().getModele());
        immatriculation_vehicule.setText(location.getVehicule().getImmatriculation());
        infoClient.setText(location.getClient().getNom() +" "+location.getClient().getPrenom());

        final String filePath = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"/"+location.getVehicule().getId()+".jpg";
        if (location.getVehicule().getAlbum().size()>0) {
            Uri uri = Uri.fromFile(new File(filePath));

            Picasso.with(getContext()).load(uri).into(thumbnail_vehicule, new Callback() {
                @Override
                public void onSuccess() {}

                @Override
                public void onError() {
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
