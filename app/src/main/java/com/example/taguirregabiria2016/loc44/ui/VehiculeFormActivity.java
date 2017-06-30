package com.example.taguirregabiria2016.loc44.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.AdresseDAO;
import com.example.taguirregabiria2016.loc44.dao.ClientDAO;
import com.example.taguirregabiria2016.loc44.dao.VehiculeDAO;
import com.example.taguirregabiria2016.loc44.model.Adresse;
import com.example.taguirregabiria2016.loc44.model.Client;
import com.example.taguirregabiria2016.loc44.model.Personne;
import com.example.taguirregabiria2016.loc44.model.Utilisation;
import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.util.ArrayList;
import java.util.List;

public class VehiculeFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicule_form);
    }

    public void ajouterUnVehicule(View view) {

        EditText vehiculeBrand = (EditText) findViewById(R.id.vehiculeBrand);
        EditText vehiculeModel = (EditText) findViewById(R.id.vehiculeModel);
        EditText vehiculeRegistration = (EditText) findViewById(R.id.vehiculeRegistration);
        EditText vehiculeDailyRate = (EditText) findViewById(R.id.vehiculeDailyRate);

        CheckBox utilisationCity = (CheckBox) findViewById(R.id.utilisationCity);
        CheckBox utilisationRoad = (CheckBox) findViewById(R.id.utilisationRoad);
        CheckBox utilisationFamily = (CheckBox) findViewById(R.id.utilisationFamily);
        CheckBox utilisationUtility = (CheckBox) findViewById(R.id.utilisationUtility);
        CheckBox utilisationSport = (CheckBox) findViewById(R.id.utilisationSport);
        CheckBox utilisationMonospace = (CheckBox) findViewById(R.id.utilisationMonoSpace);

        int utilisation = 0;

        utilisation |= (utilisationCity.isChecked() ? Utilisation.CITADINE : 0);
        utilisation |= (utilisationRoad.isChecked() ? Utilisation.ROUTIERE : 0);
        utilisation |= (utilisationFamily.isChecked() ? Utilisation.FAMILIALE : 0);
        utilisation |= (utilisationUtility.isChecked() ? Utilisation.UTILITAIRE : 0);
        utilisation |= (utilisationSport.isChecked() ? Utilisation.SPORTIVE : 0);
        utilisation |= (utilisationMonospace.isChecked() ? Utilisation.MONOSPACE : 0);

        if (vehiculeBrand.getText().length() > 0 &&
                vehiculeModel.getText().length() > 0 &&
                vehiculeRegistration.getText().length() > 0 &&
                utilisation > 0) {

            List<String> album = new ArrayList<>();

            Double dailyRate;
            if (vehiculeDailyRate.getText().toString().length() > 0) {
                dailyRate = Double.valueOf(vehiculeDailyRate.getText().toString());
            } else {
                dailyRate = 0.0;
            }
            Vehicule vehicule = new Vehicule(
                    vehiculeBrand.getText().toString(),
                    vehiculeModel.getText().toString(),
                    vehiculeRegistration.getText().toString(),
                    utilisation,
                    album,
                    dailyRate);

            VehiculeDAO.insertVehicule(vehicule);

            finish();
        } else {
            Toast.makeText(view.getContext(), "Tous les champs sont obligatoires.", Toast.LENGTH_LONG).show();
        }

    }
}
