package com.example.taguirregabiria2016.loc44.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.chiffreAffaire.caActivity;
import com.example.taguirregabiria2016.loc44.dao.AdresseDAO;
import com.example.taguirregabiria2016.loc44.dao.ClientDAO;
import com.example.taguirregabiria2016.loc44.model.Adresse;
import com.example.taguirregabiria2016.loc44.model.Client;
import com.example.taguirregabiria2016.loc44.model.Personne;

public class UserFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);
    }

    public void ajouterUnClient(View view) {

        EditText userName = (EditText) findViewById(R.id.userName);
        EditText userForname = (EditText) findViewById(R.id.userForname);
        EditText userEmail = (EditText) findViewById(R.id.userEmail);
        EditText userPhoneNumber = (EditText) findViewById(R.id.userPhoneNumber);

        EditText addressNumber = (EditText) findViewById(R.id.addressNumber);
        EditText addressLaneType = (EditText) findViewById(R.id.addressLaneType);
        EditText addressLaneName = (EditText) findViewById(R.id.addressLaneName);
        EditText addressSupplement = (EditText) findViewById(R.id.addressSupplement);
        EditText addressCode = (EditText) findViewById(R.id.addressCode);
        EditText addressCityName = (EditText) findViewById(R.id.addressCityName);
        EditText addressCountry = (EditText) findViewById(R.id.addressCountry);

        Adresse adresse = new Adresse(addressNumber.getText().toString(),
                addressLaneType.getText().toString(),
                addressLaneName.getText().toString(),
                addressSupplement.getText().toString(),
                addressCode.getText().toString(),
                addressCityName.getText().toString(),
                addressCountry.getText().toString());

        int adresseId = (int)AdresseDAO.insertAdresse(adresse);
        adresse.setId(adresseId);

        Personne personne = new Personne(userName.getText().toString(), userForname.getText().toString(), userPhoneNumber.getText().toString(), userEmail.getText().toString(), adresse);
        Client client = new Client(personne, null);

        ClientDAO.insertClient(client);

        finish();
    }
}
