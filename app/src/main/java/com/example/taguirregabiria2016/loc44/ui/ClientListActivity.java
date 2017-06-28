package com.example.taguirregabiria2016.loc44.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.ClientDAO;
import com.example.taguirregabiria2016.loc44.gererParking.GererParkingActivity;
import com.example.taguirregabiria2016.loc44.model.Client;

import java.util.List;

public class ClientListActivity extends AppCompatActivity {

    ListView mListView;
    List<Client> clients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        mListView = (ListView) findViewById(R.id.userList);

        clients = ClientDAO.getAllClients();

       ClientAdapter adapter = new ClientAdapter(ClientListActivity.this, clients);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mListView = (ListView) findViewById(R.id.userList);

        clients = ClientDAO.getAllClients();

       ClientAdapter adapter = new ClientAdapter(ClientListActivity.this, clients);
        mListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_liste, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_Ajouter:
                Intent intent = new Intent(ClientListActivity.this, ClientFormActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
