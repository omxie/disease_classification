package com.example.diseaseclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserProfile extends AppCompatActivity {
    Button changePass, delAcc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        changePass = findViewById(R.id.ChangePassword);
        delAcc = findViewById(R.id.DeleteAcc);

        //changing pass
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(UserProfile.this, PasswordReset.class);
            startActivity(intent);
            }
        });

        //deleting account, asks for confirmation
        delAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
        public void openDialog(){
            delDialog dialog = new delDialog();
            dialog.show(getSupportFragmentManager(), "deleting account");
        }

}