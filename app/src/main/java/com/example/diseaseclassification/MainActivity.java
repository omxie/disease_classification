package com.example.diseaseclassification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private RecyclerView rcycleList;
    private RecyclerView.Adapter rcycleAdapter;
    private RecyclerView.LayoutManager rcycleLayoutManager;
    ArrayList<CardItems> cardItems = new ArrayList<>();



    private EditText email, epass;
    private Button btnlog;
    private CheckBox chkbx;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardItems.add(new CardItems(R.drawable.ic_skin_, "melanoma image classification", "Opens phone's camera to identify between malignant and non-malignant Melanoma"));
        cardItems.add(new CardItems(R.drawable.ic_eye, "eye defect image classification", "Opens phone's camera to identify whether eye defect is present"));
        rcycleList = findViewById(R.id.rcycleCards);
        rcycleList.setHasFixedSize(true);
        rcycleLayoutManager = new LinearLayoutManager(this);
        rcycleAdapter = new CardsAdapter(cardItems);

        rcycleList.setLayoutManager(rcycleLayoutManager);
        rcycleList.setAdapter(rcycleAdapter);

        
        actionBar = getSupportActionBar();
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toggle=new ActionBarDrawerToggle(MainActivity.this,drawerLayout, R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //AppCompatActivity activity = (AppCompatActivity) getApplicationContext();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.logo);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_profile:
                        Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_faq:
                        Toast.makeText(MainActivity.this, "FAQ", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_share:
                        Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_logout:
                        Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.nav_exit:
                        Toast.makeText(MainActivity.this, "Exit", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return true;
            }
        });



        /*email = (EditText)findViewById(R.id.bemail);
        epass = (EditText) findViewById(R.id.bpass);
       btnlog = (Button) findViewById(R.id.blog);

      btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SplashScreen.class);
                intent.putExtra("email", email.getText().toString());
                intent.putExtra("pass", epass.getText().toString());
                startActivity(intent);

            }
        });

        */



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void toastMsg(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}