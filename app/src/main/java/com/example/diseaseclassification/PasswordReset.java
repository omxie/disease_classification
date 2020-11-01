package com.example.diseaseclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordReset extends AppCompatActivity {
    Button reset_pass;
    EditText pass1;
    EditText pass2;
    DBHelper DB;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String field_uname = "default";
    public static final String field_hash = "default";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        reset_pass = findViewById(R.id.ChangePassword);
        pass1 = findViewById(R.id.pass1);
        pass2 = findViewById(R.id.pass2);
        DB = new DBHelper(this);
        reset_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password1 = pass1.getText().toString();
                String password2 = pass2.getText().toString();

                //get Username for storing finding the specific user
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                String user_name = sharedPreferences.getString(field_uname, "");

                //If any one of the password fields is empty
                if (password1.equals("")||password2.equals(""))
                    Toast.makeText(PasswordReset.this, "Please fill in the details.", Toast.LENGTH_SHORT).show();

                //If the passwords field do not match
                if (!(password1.equals(password2)))
                    Toast.makeText(PasswordReset.this, "Password Mismatch.", Toast.LENGTH_SHORT).show();

                //If the password fields are matching
                if (password1.equals(password2)){
                    String bcryptHashString = BCrypt.withDefaults().hashToString(12, password1.toCharArray());
                    Boolean update = DB.updatePassword(user_name, bcryptHashString);

                    if(update) {
                        Toast.makeText(PasswordReset.this, ""+user_name, Toast.LENGTH_SHORT).show();
                        Toast.makeText(PasswordReset.this, "Password Reset Successful", Toast.LENGTH_SHORT).show();

                        //update the cookies
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(field_hash, bcryptHashString);
                        editor.apply();

                        //Intent intent = new Intent(PasswordReset.this, MainActivity.class);
                        finish();
                        //startActivity(intent);
                    } else
                        Toast.makeText(PasswordReset.this, "400: Internal Server Error", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}