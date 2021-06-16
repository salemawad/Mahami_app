package com.example.final_projects;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Splah_Dilog extends AppCompatActivity {
    FirebaseAuth mAtu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah__dilog);

        //====================================Check if The User Have Login OR Not =============================================
        mAtu = FirebaseAuth.getInstance();
        if (mAtu.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        } else {
            startActivity(new Intent(getApplicationContext(), Login_Sing_up_Activity.class));
        }
    }

}