package com.example.taguirregabiria2016.loc44.gererParking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.taguirregabiria2016.loc44.R;

import java.util.List;

public class GererParkingActivity extends AppCompatActivity {

    ArrayAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerer_parking);
    }
}
