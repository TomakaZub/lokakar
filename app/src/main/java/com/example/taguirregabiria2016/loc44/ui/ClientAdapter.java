package com.example.taguirregabiria2016.loc44.ui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.AdresseDAO;
import com.example.taguirregabiria2016.loc44.model.Adresse;
import com.example.taguirregabiria2016.loc44.model.Client;

import java.util.List;

/**
 * Created by ojeanmarie2016 on 27/06/2017.
 */

public class ClientAdapter extends ArrayAdapter<Client> {

    public ClientAdapter(@NonNull Context context, @NonNull List<Client> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_list_item, parent, false);
        }

        ClientHolder viewHolder = (ClientHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ClientHolder();
            viewHolder.ligne = (LinearLayout) convertView.findViewById(R.id.fond);
            viewHolder.nom = (TextView) convertView.findViewById(R.id.userName);
            viewHolder.prenom = (TextView) convertView.findViewById(R.id.userForname);
            viewHolder.voie = (TextView) convertView.findViewById(R.id.addressLane);
            viewHolder.ville = (TextView) convertView.findViewById(R.id.addressCityCountry);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Client client = getItem(position);
        Adresse adresse = client.getAdresse();

        int color = ((position % 2 == 0) ? R.color.userList1 : R.color.userList2);
        viewHolder.ligne.setBackgroundColor(convertView.getResources().getColor(color));

        viewHolder.nom.setText(client.getNom().toUpperCase());
        viewHolder.prenom.setText(client.getPrenom());
        viewHolder.voie.setText(adresse.getNumero()+" "+adresse.getType()+" "+adresse.getVoie());
        viewHolder.ville.setText(adresse.getVille()+" "+adresse.getPays());

        return convertView;
    }


    private class ClientHolder {
        public LinearLayout ligne;
        public TextView nom;
        public TextView prenom;
        public TextView voie;
        public TextView ville;
    }


}
