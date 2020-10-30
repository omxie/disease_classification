package com.example.diseaseclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {
    EditText uname;
    EditText pass;
    Button btnLogin, btnSignup, btnSkip;
    DBHelper DB;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String field_uname = "default";
    public static final String field_hash = "default";
    //private SharedPreferences pref;
    //private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        cookieLogin();

        uname = (EditText) findViewById(R.id.txtUsername);
        pass = (EditText) findViewById(R.id.txtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnSkip = (Button) findViewById(R.id.btnSkip);
        DB = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = uname.getText().toString();
                String password = pass.getText().toString();

                if (user.equals("")||password.equals(""))
                    Toast.makeText(LoginScreen.this, "Username and Password should be entered.", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkusernamepass = DB.checkUsernamePass(user, password);
                    if (checkusernamepass){
                        Toast.makeText(LoginScreen.this, "Sign In successfull", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else
                        Toast.makeText(LoginScreen.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check in db, if valid go to home screen or else give as invalid uname and pass
                Intent intent = new Intent(LoginScreen.this, SignInScreen.class );
                startActivity(intent);

            }
        });


        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check in db, if valid go to home screen or else give as invalid uname and pass
                Intent intent = new Intent(LoginScreen.this, MainActivity.class );
                startActivity(intent);

            }
        });


    }

    private void cookieLogin() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String user_name = sharedPreferences.getString(field_uname, "");
        String hashed_pass = sharedPreferences.getString(field_hash, "");

        if (user_name.equals("") || hashed_pass.equals("")) return;
        else {
            Intent intent = new Intent(LoginScreen.this, MainActivity.class );
            startActivity(intent);
        }
    }
}