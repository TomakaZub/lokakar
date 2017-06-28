package com.example.taguirregabiria2016.loc44.location;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.ClientDAO;
import com.example.taguirregabiria2016.loc44.model.Client;
import com.example.taguirregabiria2016.loc44.ui.ClientAdapter;

import java.util.List;

public class ClientFormActivity extends AppCompatActivity {

    ListView mListView;
    List<Client> clients;
    EditText editText;
    private ClientAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_form);

        mListView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);

        clients = ClientDAO.getAllClients();

        adapter = new ClientAdapter(ClientFormActivity.this, clients);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Client client = clients.get(position);
                Intent intent = new Intent(ClientFormActivity.this, LocationFormActivity.class);
                intent.putExtra("client", client.getId());
                startActivity(intent);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText = (EditText) findViewById(R.id.editText);
                String recherche = editText.getText().toString();

                mListView = (ListView) findViewById(R.id.listView);

                clients.clear();
                clients.addAll(ClientDAO.getFilteredClients(recherche));

                adapter.notifyDataSetChanged();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void Click_On_Loupe(View view) {

        editText = (EditText) findViewById(R.id.editText);
        String recherche = editText.getText().toString();

        mListView = (ListView) findViewById(R.id.listView);

        clients.clear();
        clients.addAll(ClientDAO.getFilteredClients(recherche));

        adapter.notifyDataSetChanged();
    }
}


