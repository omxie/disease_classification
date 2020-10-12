package com.example.diseaseclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

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
                    if (checkusernamepass == true){
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
}