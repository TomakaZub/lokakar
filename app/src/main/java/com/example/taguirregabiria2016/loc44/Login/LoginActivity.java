package com.example.taguirregabiria2016.loc44.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taguirregabiria2016.loc44.accueuil.MainActivity;
import com.example.taguirregabiria2016.loc44.R;
import com.example.taguirregabiria2016.loc44.dao.BaseDAO;
import com.example.taguirregabiria2016.loc44.dao.GerantDAO;
import com.example.taguirregabiria2016.loc44.model.Gerant;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    private BaseDAO dao = null;

    private static final String SELECT_IDS = "select";
    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    // UI references.
    private TextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dao = BaseDAO.getInstance(LoginActivity.this);

        mEmailView = (TextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
    }

    /***
     * Permet de verifier que l'utilisateur existe
     * @param email
     * @param mdp
     * @return
     */
    public boolean login(String email, String mdp) {
        boolean isCorrect = false;
        email = mEmailView.getText().toString();
        mdp = mPasswordView.getText().toString();

        if (BaseDAO.isDBEmpty()) {
            BaseDAO.generateData();
        }

        if (isEmailValid(email) && isPasswordValid(mdp)) {
            Gerant g = GerantDAO.verifGerant(email, mdp);

            if (g != null) {
                isCorrect = true;
                Toast.makeText(LoginActivity.this, "Bonjour " + g.getPrenom() + " " + g.getNom(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
        return isCorrect;
    }

    /***
     * Permet de verifier de Email est valide
     * @param email
     * @return
     */
    private boolean isEmailValid(String email) {
//        boolean result = false;
//        String mEmail = mEmailView.getText().toString();
//        if (mEmail.equals("admin@hotmail.fr")) {
//            result = true;
//        } else {
//            mEmailView.setError("Email invalide !");
//        }
          return email.contains("@");
//        return result;
    }

    /***
     * Permet de verifier que le mot de passe est valide
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {
//        boolean result = false;
//        String mPassword = mPasswordView.getText().toString();
//        if (mPassword.length() > 4 && mPassword.equals("admin")) {
//            result = true;
//        } else {
//            mPasswordView.setError("Mot de passe invalide !");
//        }
//        return result;
        return (password.length()>=4);
    }

    public void login(View view) {
        String email = mEmailView.getText().toString();
        String mdp = mPasswordView.getText().toString();
        login(email, mdp);
    }
}

