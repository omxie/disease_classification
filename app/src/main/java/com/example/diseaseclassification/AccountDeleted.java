package com.example.diseaseclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AccountDeleted extends AppCompatActivity {
    private Button button;
    DBHelper DB;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String field_uname = "default";
    public static final String field_hash = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_deleted);

        button = findViewById(R.id.delbtn);
        DB = new DBHelper(this);
        //get Username for storing finding the specific user
        final SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        final String user_name = sharedPreferences.getString(field_uname, "");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean update = DB.deleteAccount(user_name);

                //checks if the results is true(account deleted)
                if(update) {
                    Toast.makeText(AccountDeleted.this, "User Credentials Deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AccountDeleted.this, LoginScreen.class);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(field_uname);
                    editor.remove(field_hash);
                    editor.apply();
                    finish();
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}