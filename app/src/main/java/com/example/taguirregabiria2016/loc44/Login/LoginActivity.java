package com.example.taguirregabiria2016.loc44.Login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taguirregabiria2016.loc44.accueil.MainActivity;
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
    private Button mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dao = BaseDAO.getInstance(LoginActivity.this);

        mEmailView = (TextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mSignInButton = (Button)findViewById(R.id.email_sign_in_button);

        mEmailView.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    mSignInButton.performClick();
                    return true;
                }
                return false;
            }
        });

        mPasswordView.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    mSignInButton.performClick();
                    return true;
                }
                return false;
            }
        });

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

//        Log.d("*** Login email ***", email);
//        Log.d("*** Login mdp ***", mdp);
//
//        Log.d("*** Login gérants ***", GerantDAO.getAllGerants().toString());

        if (BaseDAO.isDBEmpty()) {
//            Log.d("*** Login ***", "Génération BDD");
            BaseDAO.generateData();
        }

        if (isEmailValid(email) && isPasswordValid(mdp)) {
            Gerant g = GerantDAO.verifGerant(email, mdp);

            if (g != null) {
                Log.d("*** Login Gerant ***", g.toString());
                isCorrect = true;

                showToast(LoginActivity.this, "Bienvenue\n" + g.getPrenom() + " " + g.getNom());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, "Identifiants incorrects !", Toast.LENGTH_SHORT).show();
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

//        mEmailView.setError("Email non valide");
        return email.contains("@");
    }

    /***
     * Permet de verifier que le mot de passe est valide
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {

//        mEmailView.setError("Mot de passe non valide");
        return (password.length() >= 4);
    }

    public void login(View view) {
        String email = mEmailView.getText().toString();
        String mdp = mPasswordView.getText().toString();
        login(email, mdp);
    }

    private void showToast(Context context, String message) {

        // inflater
        LayoutInflater inflater = LayoutInflater.from(context);

        // view
        View view = inflater.inflate(R.layout.accueil, null);

        TextView tvMessage = (TextView) view.findViewById(R.id.message);
        tvMessage.setText("« " + message + " »");

        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.show();
    }

}

