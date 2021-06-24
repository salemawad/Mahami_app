package com.example.final_projects;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class Sitteng extends AppCompatActivity {

    TextView lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitteng);
        //===========================code  is to make the Activity full screen======================
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        lang = findViewById(R.id.lang);
        //==========================================================================================
        loadLocal();
        lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });

    }


    //==============================================================================================
    public void ShowDialog() {
        final String[] lis_String = {"Arabic", "English"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Sitteng.this);
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
                recreate();
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //==============================================================================================
    public void setLocal(String lang) {
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

    //==============================load language saved in shared preferences=======================
    public void loadLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences("Setting", Activity.MODE_PRIVATE);
        String language = sharedPreferences.getString("lang", "");
        setLocal(language);
    }
}