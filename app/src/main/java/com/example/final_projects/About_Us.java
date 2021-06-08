package com.example.final_projects;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class About_Us extends AppCompatActivity {
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__us);
        // code  is to make the Activity full screen
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        



    }
}