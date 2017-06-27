package com.example.taguirregabiria2016.loc44.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.ClientDAO;
import com.example.taguirregabiria2016.loc44.model.Client;

import java.util.List;

public class UserListActivity extends AppCompatActivity {

    ListView mListView;
    List<Client> clients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        mListView = (ListView) findViewById(R.id.userList);

        clients = ClientDAO.getAllClients();

       UserAdapter adapter = new UserAdapter(UserListActivity.this, clients);
        mListView.setAdapter(adapter);
    }
}
