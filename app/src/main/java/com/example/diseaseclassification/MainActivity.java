package com.example.diseaseclassification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private RecyclerView rcycleList;
    private RecyclerView.Adapter rcycleAdapter;
    private RecyclerView.LayoutManager rcycleLayoutManager;
    ArrayList<CardItems> cardItems = new ArrayList<>();

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String field_uname = "default";
    public static final String field_hash = "default";


    //private EditText email, epass;
    //private Button btnlog;
    //private CheckBox chkbx;


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

        
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toggle=new ActionBarDrawerToggle(MainActivity.this,drawerLayout, R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //AppCompatActivity activity = (AppCompatActivity) getApplicationContext();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.logo);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_profile:
                        Intent intent1 = new Intent(MainActivity.this, UserProfile.class);
                        startActivity(intent1);
                        return true;
                    case R.id.nav_faq:
                        Intent intent2 = new Intent(MainActivity.this, faq.class);
                        startActivity(intent2);
                        return true;
                    case R.id.nav_share:
                        Intent intent3 = new Intent(Intent.ACTION_SEND);
                        intent3.setType("text/plain");
                        String sharebody = "Disease classification";
                        String subject = "https://www.google.com/";
                        intent3.putExtra(Intent.EXTRA_SUBJECT, subject);
                        intent3.putExtra(Intent.EXTRA_TEXT, sharebody);
                        startActivity(Intent.createChooser(intent3, "Share"));
                        return true;
                    case R.id.nav_logout:
                        //Removing Cookies
                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(field_uname);
                        editor.remove(field_hash);
                        editor.apply();
                        Intent intent4 = new Intent(MainActivity.this, SignInScreen.class);
                        startActivity(intent4);
                        return true;
                    case R.id.nav_exit:
                        //moveTaskToBack(true);
                        //android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                        return true;
                }
                return true;
            }
        });



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