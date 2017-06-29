package com.example.taguirregabiria2016.loc44.chiffreAffaire;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.LocationDAO;
import com.example.taguirregabiria2016.loc44.location.LocationFormActivity;

import java.util.Calendar;

public class caActivity extends AppCompatActivity {

    LocationDAO dao;
    EditText debut;
    EditText fin ;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca);

        debut = (EditText) findViewById(R.id.dateDebut);
        fin = (EditText) findViewById(R.id.dateFin);
        result = (TextView) findViewById(R.id.chiffreAffaire);
        dao = new LocationDAO();

        debut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(caActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {

                        String day = String.valueOf(selectedday);
                        String mounth = String.valueOf(selectedmonth + 1);

                        if (day.length() < 2) day = "0" + day;
                        if (mounth.length() < 2) mounth = "0" + mounth;
                        String returnedDate = day + "/" + mounth + "/" + String.valueOf(selectedyear);

                        debut.setText(returnedDate);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(caActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {

                        String day = String.valueOf(selectedday);
                        String mounth = String.valueOf(selectedmonth + 1);

                        if (day.length() < 2) day = "0" + day;
                        if (mounth.length() < 2) mounth = "0" + mounth;
                        String returnedDate = day + "/" + mounth + "/" + String.valueOf(selectedyear);

                        fin.setText(returnedDate);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
    }

    private static String convertDate (String date) {

        String[] dummies = date.split(" ");
        String[] parts = dummies[0].split("/");

        return parts[2]+"/"+parts[1]+"/"+parts[0]+" "+dummies[1];
    }

    public void calculerCA(View view) {

        String d = debut.getText().toString();
        String f = fin.getText().toString();
        if(d.isEmpty() || f.isEmpty())
        {
            if(d.isEmpty()){
                debut.setError("Veuillez renseigner la date de début !");
            }
            if(f.isEmpty()){
                debut.setError("Veuillez renseigner la date de fin !");
            }
        }
        else
        {
            d = d+" 00:01";
            f = f+" 00:01";
            String dateDebut = convertDate(d);
            String dateFin = convertDate(f);
            double total = dao.calculerCA(dateDebut, dateFin);
            result.setText("Chiffre d'affaire = "+total+" €");
        }


    }
}
