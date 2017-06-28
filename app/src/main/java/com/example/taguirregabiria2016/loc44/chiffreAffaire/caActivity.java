package com.example.taguirregabiria2016.loc44.chiffreAffaire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.LocationDAO;

public class caActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca);

        LocationDAO dao = new LocationDAO();
        EditText debut = (EditText) findViewById(R.id.dateDebut);
        EditText fin = (EditText) findViewById(R.id.dateFin);

        String dateDebut = debut.getText().toString();
        String dateFin = fin.getText().toString();






        double total = dao.calculerCA(dateDebut, dateFin);
    }



}
