package com.example.diseaseclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginScreen extends AppCompatActivity {
    EditText uname;
    EditText pass;
    Button btnLogin, btnSignup, btnSkip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        uname = (EditText) findViewById(R.id.txtUsername);
        pass = (EditText) findViewById(R.id.txtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnSkip = (Button) findViewById(R.id.btnSkip);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname.getText().toString();
                pass.getText().toString();

                //Check in db, if valid go to home screen or else give as invalid uname and pass
                Intent intent = new Intent(LoginScreen.this, MainActivity.class );
                startActivity(intent);

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
}