package com.example.taguirregabiria2016.loc44.location;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.taguirregabiria2016.loc44.utils.Tools;

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
    private static Location location = null;
    private static Vehicule vehicule = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Bundle bundle = getIntent().getExtras();
        if (bundle.get("location") != null) {
            location = LocationDAO.getLocation((int) bundle.get("location"));
            vehicule = location.getVehicule();
        }
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
        if (vehicule != null) {
            spVehicule.setSelection(vAdapter.getPosition(vehicule.toSpinnerItem()));
        }

        /**
         * Hydratation du nom du client
         */
        tvClient = (TextView) findViewById(R.id.client);
        startDate = (EditText) findViewById(R.id.startDate);
        startTime = (EditText) findViewById(R.id.startTime);
        endDate = (EditText) findViewById(R.id.endDate);
        endTime = (EditText) findViewById(R.id.endTime);

//        Bundle bundle = getIntent().getExtras();
        if (location != null) {
            client = location.getClient();
        } else {
            int id = (int) bundle.get("client");
            client = ClientDAO.getClient(id);
        }
        tvClient.setText(client.getPrenom() + " " + client.getNom().toUpperCase());

        /**
         * Hydratation des champs date et heure
         */

        if (location != null) {

            String[] debut = location.getDebut().split(" ");
            String[] fin = location.getFin().split(" ");

            startDate.setText(debut[0]);
            startTime.setText(debut[1]);
            endDate.setText(fin[0]);
            endTime.setText(fin[1]);
        } else {

            Calendar cal = Calendar.getInstance();
            int today_day = cal.get(Calendar.DAY_OF_MONTH);
            int today_mounth = cal.get(Calendar.MONTH) + 1;
            String today = (today_day < 10 ? "0" : "") + today_day + "/" + (today_mounth < 10 ? "0" : "") + today_mounth + "/" + cal.get(Calendar.YEAR);

            startDate.setText(today);
            endDate.setText(today);
            startTime.setText("09:00");
            endTime.setText("18:00");
        }

        if (location != null) {
            Button validate = (Button) findViewById(R.id.validate);
            validate.setText("Mettre à jour");
//            getIntent().putExtra("client", client.getId());
        }

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
                        if (hour.length() < 2) hour = "0" + hour;
                        if (minute.length() < 2) minute = "0" + minute;
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
                        if (hour.length() < 2) hour = "0" + hour;
                        if (minute.length() < 2) minute = "0" + minute;
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

    public void ajouterUneLocationn(View view) {

        Client localClient = null;
        Bundle bundle = getIntent().getExtras();

        if (location == null) {
            int id = (int) bundle.get("client");
            localClient = ClientDAO.getClient(id);
        }
        Vehicule vehicule = vehiculeList.get(spVehicule.getSelectedItemPosition());
        String debutDate = startDate.getText().toString();
        String debutTime = startTime.getText().toString();
        String finDate = endDate.getText().toString();
        String finTime = endTime.getText().toString();

        if (Tools.convertDateOnly(debutDate).compareTo(Tools.convertDateOnly(finDate)) <= 0) {

            Calendar today = Calendar.getInstance();
            int rendu = (Tools.Cal2StrBDD(today).compareTo(debutDate + " " + debutTime) <= 0) ? 0 : 1;

            if (location != null) {

                rendu = (Tools.Cal2StrBDD(today).compareTo(finDate + " " + finTime) <= 0) ? 1 : 0;
                location.setVehicule(vehicule);
                location.setDebut(debutDate + " " + debutTime);
                location.setFin(finDate + " " + finTime);
                location.setRendu(rendu);
                LocationDAO.updateLocation(location);
            } else {

                Location location = new Location(vehicule, debutDate + " " + debutTime, finDate + " " + finTime, localClient, new ArrayList<String>(), rendu);
                location.setId((int) LocationDAO.insertLocation(location));
            }

            Intent intent = new Intent(LocationFormActivity.this, ResumeLocationActivity.class);
            intent.putExtra("resume", location.getId());
            startActivity(intent);

            finish();
        } else {
            endDate.requestFocus();
        }
    }
}
