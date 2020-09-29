package com.example.diseaseclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText email, epass;
    private Button btnlog;
    private CheckBox chkbx;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText)findViewById(R.id.bemail);
        epass = (EditText) findViewById(R.id.bpass);
        btnlog = (Button) findViewById(R.id.blog);

        ImageView img = (ImageView) findViewById(R.id.imgc);
        TextView txt = (TextView) findViewById(R.id.tvc);

        int Imgres = getResources().getIdentifier("@drawable/ic_diagnosis", null, this.getPackageName());
        img.setImageResource(Imgres);
        txt.setText("MELANOMA DISEASE CLASSIFICATION");


        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SplashScreen.class);
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("pass", epass.getText().toString());
                startActivity(intent);

            }
        });
    }


    public void toastMsg(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}