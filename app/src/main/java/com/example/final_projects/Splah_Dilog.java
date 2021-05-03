package com.example.final_projects;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Splah_Dilog extends AppCompatActivity {
    FirebaseAuth mAth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah__dilog);
        mAth = FirebaseAuth.getInstance();
        if (mAth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        finish();
    }
}