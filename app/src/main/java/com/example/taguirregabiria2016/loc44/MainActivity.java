package com.example.taguirregabiria2016.loc44;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Action click (permet afficher les véhicule a louer)
     * @param view
     */
    public void Click_alouer(View view) {
        Intent Intent = new Intent(MainActivity.this, alouerActivity.class);
        startActivity(Intent);
    }

    /**
     * Action click (permet de gerer le parcking)
     * @param view
     */
    public void Click_gererParking(View view) {
        Intent intent = new Intent(MainActivity.this, GererParkingActivity.class);
        startActivity(intent);
    }

    /**
     * Action click (permet d'afficher les véhicule louer)
     * @param view
     */
    public void Click_louer(View view) {
        Intent intent = new Intent(MainActivity.this, louerActivity.class);
        startActivity(intent);
    }

    /**
     * permet de rechercher un véhicule
     * @param view
     */
    public void Click_search(View view) {
        Intent intent = new Intent(MainActivity.this, searchActivity.class);
        startActivity(intent);
    }

    /**
     * Action click  Permet d'afficher le chiffre d'affaire
     * @param view
     */
    public void Click_ca(View view) {
        Intent intent = new Intent(MainActivity.this, caActivity.class);
        startActivity(intent);
    }
}
