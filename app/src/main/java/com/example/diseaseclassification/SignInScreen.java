package com.example.diseaseclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class SignInScreen extends AppCompatActivity {

    EditText uname;
    EditText pass, repass;
    Button btnSignUp;
    DBHelper DB;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String field_uname = "default";
    public static final String field_hash = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);

        uname = (EditText) findViewById(R.id.txtUsername);
        pass = (EditText) findViewById(R.id.txtPass);
        repass = (EditText) findViewById(R.id.txtRePass);
        btnSignUp = (Button) findViewById(R.id.btnLogin);
        DB = new DBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = uname.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();

                if (user.equals("")||password.equals("")||repassword.equals(""))
                    Toast.makeText(SignInScreen.this, "Please fill in the details.", Toast.LENGTH_SHORT).show();
                else{
                    if (password.equals(repassword)){
                        Boolean checkuser = DB.checkUsername(user);
                        if (checkuser==false){

                            //Hashing password before storing into the DB
                            String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
                            Boolean insert = DB.insertData(user, bcryptHashString);
                            if (insert == true) {
                                Toast.makeText(SignInScreen.this, "Registered Sucessfully!", Toast.LENGTH_SHORT).show();

                               //Saving Cookies
                                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(field_uname, user);
                                editor.putString(field_hash, bcryptHashString);
                                editor.apply();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignInScreen.this, "Registration Failed.", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(SignInScreen.this, "UserName already exists.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignInScreen.this, "Password Mismatch.", Toast.LENGTH_SHORT).show();
                    }
                }
                Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(intent);
            }
        });

    }

    int counter=0;
    @Override
    public void onBackPressed() {
        counter++;
        if (counter == 1){
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        }else if(counter == 2){
            moveTaskToBack(true);
            //android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }
}