package com.example.taguirregabiria2016.loc44.location;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taguirregabiria2016.loc44.R;

import com.example.taguirregabiria2016.loc44.accueil.MainActivity;
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.utils.Tools;

public class ResumeLocationActivity extends AppCompatActivity {

    TextView TextViewNom;
    TextView textViewPrenom;
    TextView textViewAdresse;
    TextView textViewMarque;
    TextView textViewModele;
    TextView textViewImmat;
    TextView textViewDateDebut;
    TextView textViewDateFin;
    TextView textViewHeureDebut;
    TextView textViewHeurefin;
    TextView textViewPrixTotal;

    Location location;
    String[] dateDebut;
    String[] dateFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_location);

        TextViewNom = (TextView) findViewById(R.id.idNom);
        textViewPrenom = (TextView) findViewById(R.id.idPrenom);
        textViewAdresse = (TextView) findViewById(R.id.idAdresse);
        textViewMarque = (TextView) findViewById(R.id.idMarque);
        textViewModele = (TextView) findViewById(R.id.idModele);
        textViewImmat = (TextView) findViewById(R.id.idImmat);
        textViewDateDebut = (TextView) findViewById(R.id.idDateDebut);
        textViewDateFin = (TextView) findViewById(R.id.idDateFin);
        textViewHeureDebut = (TextView) findViewById(R.id.idHeureDebut);
        textViewHeurefin = (TextView) findViewById(R.id.idHeureFin);
        textViewPrixTotal = (TextView) findViewById(R.id.idPrixTotal);

        Bundle bundle = getIntent().getExtras();
        location = (Location) bundle.get("resume");

        TextViewNom.setText(location.getClient().getNom());
        textViewPrenom.setText(location.getClient().getPrenom());
        textViewAdresse.setText(location.getClient().getAdresse().toString());
        textViewMarque.setText(location.getVehicule().getMarque());
        textViewModele.setText(location.getVehicule().getModele());
        textViewImmat.setText(location.getVehicule().getImmatriculation());

        dateDebut = location.getDebut().split(" ");
        dateFin = location.getFin().split(" ");

        textViewDateDebut.setText(dateDebut[0]);
        textViewDateFin.setText(dateFin[0]);
        textViewHeureDebut.setText(dateDebut[0]);
        textViewHeurefin.setText(dateFin[1]);

        textViewPrixTotal.setText(String.valueOf(Tools.getPrice(location)));
    }

    public void onClickBtn(View view) {

        switch (view.getId()) {
            case R.id.idRetour:
                Intent intent = new Intent(ResumeLocationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.idSms:
                Intent Sms = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + location.getClient().getTelephone()));
                Sms.putExtra("sms_body",
                        "Bonjour, \n\n nous vous confirmons la réservation de votre véhicule : "
                                + location.getVehicule()
                                + ". La location est effective à partir de "
                                + dateDebut[0]
                                + dateDebut[1]
                                + "jusqu'au "
                                + dateFin[0]
                                + dateFin[1]
                                + "\n\n. Merci pour votre confiance.\n\nL'équipe Lokakar.");
                startActivity(Sms);
        }

    }
}
