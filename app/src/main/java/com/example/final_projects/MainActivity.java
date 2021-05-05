package com.example.final_projects;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.final_projects.Fragment.Home_Fragment;
import com.example.final_projects.Fragment.Profile_Fragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    FirebaseAuth mAtu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAtu = FirebaseAuth.getInstance();
        //============================================Nav_Casting===========================================
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
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
    }

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
        switch (item.getItemId()){
            case R.id.nav_Language:
                Toast.makeText(this, "Language", Toast.LENGTH_SHORT).show();
                break;
        }
        switch (item.getItemId()){
            case R.id.nav_sitting:
                Toast.makeText(this, "Sitting", Toast.LENGTH_SHORT).show();
                break;
        }
        switch (item.getItemId()){
            case R.id.nav_about_us:
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                break;
        }
        switch (item.getItemId()){
            case R.id.nav_share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plan");
                String shareBody = "Task Management";
                share.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                startActivity(Intent.createChooser(share, "اختر التطبيق للمشاركة"));
                break;
        }
        switch (item.getItemId()){
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

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}