package com.example.diseaseclassification;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        int logoImg = getResources().getIdentifier("@drawable/ic_diagnosis", null, this.getPackageName());
        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(LoginScreen.class)
                .withSplashTimeOut(3000)
                .withLogo(R.drawable.ic_diagnosis);

        config.getLogo().setMaxHeight(575);
        config.getLogo().setMaxWidth(575);
        View easySplashScreen = config.create();
        setContentView(easySplashScreen);

    }
}