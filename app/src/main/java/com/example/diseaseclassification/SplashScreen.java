package com.example.diseaseclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView splashImg = (ImageView) findViewById(R.id.splashView);
        int logoImg = getResources().getIdentifier("@drawable/ic_diagnosis", null, this.getPackageName());
        splashImg.setImageResource(logoImg);


    }
}