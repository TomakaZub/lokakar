package com.example.taguirregabiria2016.loc44.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.VehiculeDAO;
import com.example.taguirregabiria2016.loc44.model.Utilisation;
import com.example.taguirregabiria2016.loc44.model.Vehicule;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VehiculeFormActivity extends AppCompatActivity {

    ImageView imgView;
    Bitmap bp;
    String chemin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicule_form);
        imgView = (ImageView) findViewById(R.id.imgView);
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

        List<String> album = new ArrayList<>();


        Vehicule vehicule = new Vehicule(
                vehiculeBrand.getText().toString(),
                vehiculeModel.getText().toString(),
                vehiculeRegistration.getText().toString(),
                utilisation,
                album,
                Double.valueOf(vehiculeDailyRate.getText().toString()));

        int id = (int) VehiculeDAO.insertVehicule(vehicule);
        vehicule.setId(id);

        //On ajoute l'image
        savePicture(vehicule.getId());
        vehicule.getAlbum().add(vehicule.getId()+".jpg");
        VehiculeDAO.updateVehicule(vehicule);

        finish();

    }

    /**
     * Prendre une photo
     * @param view
     */
    public void photo(View view) {
        Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        startActivityForResult(intent, 0);
    }

    /**
     * Sélectionner une photo depuis la galerie
     * @param view
     */
    public void galerie(View view) {
    }

    /**
     * result de l'activite
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {



        if (resultCode== Activity.RESULT_OK) {
            //Utilisateur a pris un foto
            Toast.makeText(VehiculeFormActivity.this, "Une photo a été prise !", Toast.LENGTH_LONG).show();
            bp = (Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(bp);

        } else {
            Toast.makeText(VehiculeFormActivity.this, "Annulé !", Toast.LENGTH_LONG).show();
        }
    }

    public void savePicture(int idVehicule)
    {
        // image name
        String file_name = String.valueOf(idVehicule);

        // on compresse image format JPEG
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bp.compress(Bitmap.CompressFormat.JPEG,80,out);

        // enregistrer le l'image dans la memoire du phone
        FileOutputStream fos = null;
        try {
            //TODO: nom de l'image id du vehicule
            fos = new FileOutputStream (new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)+"/"+file_name+".jpg"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Put data in your baos
            baos.write(out.toByteArray());

            baos.writeTo(fos);
        } catch(IOException ioe) {
            // Handle exception here
            ioe.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
