package com.example.final_projects;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.final_projects.Fragment.Home_Fragment;
import com.example.final_projects.Fragment.Profile_Fragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    FirebaseAuth mAtu;
    TextView name_header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocal();
        setContentView(R.layout.activity_main);
        mAtu = FirebaseAuth.getInstance();
        //============================================Nav_Casting===========================================
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        name_header = findViewById(R.id.text_name_header);
        //============================================Nav_Config===========================================
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Home_Fragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
//        Bundle extra = getIntent().getExtras();
//        String text = extra.getString("Next Screen");
//        name_header.setText(text);
    }
    //============================================Nav_Config===========================================

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Home_Fragment()).commit();
                break;
        }
        switch (item.getItemId()) {
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Profile_Fragment()).commit();
                break;
        }
        switch (item.getItemId()) {
            case R.id.nav_Language:
                Showdialog();
                 break;
        }
        switch (item.getItemId()) {
            case R.id.nav_sitting:
                Intent intent =new Intent(MainActivity.this,Sitteng.class);
                startActivity(intent);
                break;
        }
        switch (item.getItemId()) {
            case R.id.nav_about_us:
                Intent intent =new Intent(MainActivity.this,About_Us.class);
                startActivity(intent);
                break;
        }
        switch (item.getItemId()) {
            case R.id.nav_share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plan");
                String shareBody = "Task Management";
                share.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                startActivity(Intent.createChooser(share, "اختر التطبيق للمشاركة"));
                break;
        }
        switch (item.getItemId()) {
            case R.id.nav_about_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Home_Fragment()).commit();
                break;
        }

        if (item.getItemId() == R.id.nav_logout) {

            Toast.makeText(MainActivity.this, "LogOut", Toast.LENGTH_SHORT).show();
            mAtu.signOut();
            finishAffinity();
            startActivity(new Intent(MainActivity.this, Login_Sing_up_Activity.class));
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    //============================================Language_Config===========================================

    private void Showdialog() {
        final String[] lis_String = {"Arabic", "English"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Change in progress");
        builder.setSingleChoiceItems(lis_String, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    setLocal("AR");
                    recreate();
                } else {
                    setLocal("EN");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void setLocal(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        //save data to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Setting", MODE_PRIVATE).edit();
        editor.putString("lang", lang);
        editor.apply();

    }

    // load language saved in shared preferences
    public void loadLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences("Setting", Activity.MODE_PRIVATE);
        String language = sharedPreferences.getString("lang", "");
        setLocal(language);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}