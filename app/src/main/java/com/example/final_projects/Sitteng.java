package com.example.final_projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.final_projects.Fragment.Profile_Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Sitteng extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    Button btn_about, btn_account_setting, btn_setting_app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitteng);
        floatingActionButton = findViewById(R.id.floating);
        btn_about = findViewById(R.id.btn_about);
        btn_setting_app = findViewById(R.id.btn_setting_app);
        btn_account_setting = findViewById(R.id.btn_account_setting);
        btn_setting_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          //      Intent intentss = new Intent(Sitteng.this, Application_Setting.class);
           //     startActivity(intentss);
                Toast.makeText(Sitteng.this, "aaaaaaa", Toast.LENGTH_SHORT).show();
            }
        });


        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_about = new Intent(Sitteng.this, About_Us.class);
                startActivity(intent_about);
            }
        });
        btn_account_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     Intent intent_account = new Intent(Sitteng.this, Profile_Fragment.class);
           //     startActivity(intent_account);
                Toast.makeText(Sitteng.this, "aaaaaaa", Toast.LENGTH_SHORT).show();

            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plan");
                share.putExtra(Intent.EXTRA_SUBJECT, "ShearApp");
                startActivity(Intent.createChooser(share, "اختر التطبيق للمشاركة"));
            }
        });
    }
}