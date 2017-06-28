package com.example.taguirregabiria2016.loc44.location;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.ClientDAO;
import com.example.taguirregabiria2016.loc44.dao.LocationDAO;
import com.example.taguirregabiria2016.loc44.dao.VehiculeDAO;
import com.example.taguirregabiria2016.loc44.model.Client;
import com.example.taguirregabiria2016.loc44.model.Location;
import com.example.taguirregabiria2016.loc44.model.Vehicule;
import com.example.taguirregabiria2016.loc44.ui.ClientFormActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LocationFormActivity extends AppCompatActivity {

    private static EditText startDate;
    private static EditText startTime;
    private static EditText endDate;
    private static EditText endTime;

    private static Spinner spVehicule;
    private static TextView tvClient;

    private static List<Vehicule> vehiculeList;
    private static Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        /**
         * Popupmenu Véhicules
         */
        spVehicule = (Spinner) findViewById(R.id.vehiculeList);
        vehiculeList = VehiculeDAO.getAllVehicules();
        List<String> spVehiculeItems = new ArrayList<>();

        for (Vehicule item : vehiculeList) {
            spVehiculeItems.add(item.toSpinnerItem());
        }
        ArrayAdapter<String> vAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spVehiculeItems);
        vAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spVehicule.setAdapter(vAdapter);

        /**
         * Hydratation du nom du client
         */
        tvClient = (TextView)findViewById(R.id.editText);
        Bundle bundle = getIntent().getExtras();
        int id = (int)bundle.get("client");
        client = ClientDAO.getClient(id);
        tvClient.setText(client.getPrenom()+" "+client.getNom().toUpperCase());

        /**
         * Hydratation des champs date et heure
         */
        startDate = (EditText) findViewById(R.id.startDate);
        startTime = (EditText) findViewById(R.id.startTime);
        endDate = (EditText) findViewById(R.id.endDate);
        endTime = (EditText) findViewById(R.id.endTime);

        Calendar cal = Calendar.getInstance();
        String today = cal.get(Calendar.DAY_OF_MONTH)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.YEAR);
        startDate.setText(today);
        endDate.setText(today);
        startTime.setText("09:00");
        endDate.setText("09:00");

        startDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(LocationFormActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {

                        String day = String.valueOf(selectedday);
                        String mounth = String.valueOf(selectedmonth + 1);

                        if (day.length() < 2) day = "0" + day;
                        if (mounth.length() < 2) mounth = "0" + mounth;
                        String returnedDate = day + "/" + mounth + "/" + String.valueOf(selectedyear);

                        startDate.setText(returnedDate);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });


        endDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(LocationFormActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {

                        String day = String.valueOf(selectedday);
                        String mounth = String.valueOf(selectedmonth + 1);

                        if (day.length() < 2) day = "0" + day;
                        if (mounth.length() < 2) mounth = "0" + mounth;
                        String returnedDate = day + "/" + mounth + "/" + String.valueOf(selectedyear);

                        endDate.setText(returnedDate);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(LocationFormActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        String hour = String.valueOf(selectedHour);
                        String minute = String.valueOf(selectedMinute);
                        if (hour.length()<2) hour = "0" + hour;
                        if (minute.length()<2) minute = "0" + minute;
                        startTime.setText(hour + ":" + minute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(LocationFormActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        String hour = String.valueOf(selectedHour);
                        String minute = String.valueOf(selectedMinute);
                        if (hour.length()<2) hour = "0" + hour;
                        if (minute.length()<2) minute = "0" + minute;
                        endTime.setText(hour + ":" + minute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void pickStart(View view) {

        Toast.makeText(LocationFormActivity.this, "Pick Start", Toast.LENGTH_LONG).show();
    }

    public void pickEnd(View view) {

        Toast.makeText(LocationFormActivity.this, "Pick End", Toast.LENGTH_LONG).show();
    }

    public void ajouterUneLocationn(View view) {

        Bundle bundle = getIntent().getExtras();
        int id = (int)bundle.get("client");

        Vehicule vehicule = vehiculeList.get(spVehicule.getSelectedItemPosition());
        Client client = ClientDAO.getClient(id);
        String debutDate = startDate.getText().toString();
        String debutTime = startTime.getText().toString();
        String finDate = endDate.getText().toString();
        String finTime = endTime.getText().toString();

        Log.d("Ajout Location", "\t" + client.toSpinnerItem() + " :\n\t\tdu " + debutDate + " à " + debutTime + "\n\t\tau " + finDate + " à " + finTime + "\n\t\t" + vehicule.toSpinnerItem());

        Location location = new Location(vehicule, debutDate + " " + debutTime, finDate + " " + finTime, client, new ArrayList<String>(), 0);
        location.setId((int) LocationDAO.insertLocation(location));

        Log.d("Location ajoutée", location.toString());
        finish();
    }

    public void addClient(View view) {

        Intent intent = new Intent(LocationFormActivity.this, ClientFormActivity.class);
        startActivity(intent);
    }
}
