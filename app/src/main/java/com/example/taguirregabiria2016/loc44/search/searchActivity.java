package com.example.taguirregabiria2016.loc44.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.LocationDAO;
import com.example.taguirregabiria2016.loc44.dao.VehiculeDAO;
import com.example.taguirregabiria2016.loc44.location.alouerAdapter;
import com.example.taguirregabiria2016.loc44.model.Utilisation;
import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.util.List;

public class searchActivity extends AppCompatActivity {

    ArrayAdapter adapter;
    private List<Vehicule> liste_vehicule;
    LocationDAO daoLocation;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void search(View view) {

        CheckBox utilisationCity = (CheckBox) findViewById(R.id.utilisationCity);
        CheckBox utilisationRoad = (CheckBox) findViewById(R.id.utilisationRoad);
        CheckBox utilisationFamily = (CheckBox) findViewById(R.id.utilisationFamily);
        CheckBox utilisationUtility = (CheckBox) findViewById(R.id.utilisationUtility);
        CheckBox utilisationSport = (CheckBox) findViewById(R.id.utilisationSport);
        CheckBox utilisationMonospace = (CheckBox) findViewById(R.id.utilisationMonoSpace);

        int filtre = 0;

        filtre |= (utilisationCity.isChecked() ? Utilisation.CITADINE : 0);
        filtre |= (utilisationRoad.isChecked() ? Utilisation.ROUTIERE : 0);
        filtre |= (utilisationFamily.isChecked() ? Utilisation.FAMILIALE : 0);
        filtre |= (utilisationUtility.isChecked() ? Utilisation.UTILITAIRE : 0);
        filtre |= (utilisationSport.isChecked() ? Utilisation.SPORTIVE : 0);
        filtre |= (utilisationMonospace.isChecked() ? Utilisation.MONOSPACE : 0);


        if(filtre == 0)
        {
            Toast.makeText(this, "Veuillez sélection au moins un critère de recherche", Toast.LENGTH_LONG).show();
            TextView tv = (TextView) findViewById(R.id.msg);
            tv.setVisibility(View.VISIBLE);
        }
        else
        {
            liste_vehicule = VehiculeDAO.getVehiculeByUtilisation(filtre);
            adapter = new searchAdapter(this, R.layout.template_list_a_louer, liste_vehicule);
            listView = (ListView) findViewById(R.id.result);
            listView.setAdapter(adapter);
        }

    }
}
