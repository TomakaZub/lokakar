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
    TextView message;
    CheckBox utilisationCity;
    CheckBox utilisationRoad;
    CheckBox utilisationFamily;
    CheckBox utilisationUtility;
    CheckBox utilisationSport;
    CheckBox utilisationMonospace;
    int filtre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        message = (TextView) findViewById(R.id.msg);
        utilisationCity = (CheckBox) findViewById(R.id.utilisationCity);
        utilisationRoad = (CheckBox) findViewById(R.id.utilisationRoad);
        utilisationFamily = (CheckBox) findViewById(R.id.utilisationFamily);
        utilisationUtility = (CheckBox) findViewById(R.id.utilisationUtility);
        utilisationSport = (CheckBox) findViewById(R.id.utilisationSport);
        utilisationMonospace = (CheckBox) findViewById(R.id.utilisationMonoSpace);

        filtre = 0;
        liste_vehicule = VehiculeDAO.getVehiculeByUtilisation(filtre);
        adapter = new searchAdapter(this, R.layout.template_list_a_louer, liste_vehicule);
        listView = (ListView) findViewById(R.id.result);
        listView.setAdapter(adapter);
    }

    public void search(View view) {


        filtre = 0;

        filtre |= (utilisationCity.isChecked() ? Utilisation.CITADINE : 0);
        filtre |= (utilisationRoad.isChecked() ? Utilisation.ROUTIERE : 0);
        filtre |= (utilisationFamily.isChecked() ? Utilisation.FAMILIALE : 0);
        filtre |= (utilisationUtility.isChecked() ? Utilisation.UTILITAIRE : 0);
        filtre |= (utilisationSport.isChecked() ? Utilisation.SPORTIVE : 0);
        filtre |= (utilisationMonospace.isChecked() ? Utilisation.MONOSPACE : 0);

        liste_vehicule = VehiculeDAO.getVehiculeByUtilisation(filtre);
        adapter = (ArrayAdapter) listView.getAdapter();
        adapter.clear();
        adapter = new searchAdapter(this, R.layout.template_list_a_louer, liste_vehicule);
        listView.setAdapter(adapter);

        if (liste_vehicule.size() == 0) {

            //    Toast.makeText(this, "Veuillez sélection au moins un critère de recherche", Toast.LENGTH_LONG).show();
            message.setText("Aucun résultat.");
            message.setVisibility(View.VISIBLE);
        } else {

            int n = liste_vehicule.size();
            message.setText(n + " résultat" + (n > 1 ? "s" : ""));
//            message.setVisibility(View.GONE);
        }

    }
}
