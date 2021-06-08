package com.example.final_projects;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.FirebaseStorage;
import com.hsalf.smileyrating.SmileyRating;

import javax.xml.transform.Result;

public class Feedback_Ativity extends AppCompatActivity {
    private SmileyRating smileyRating;
    Button button_send;
    TextInputEditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback__ativity);
        smileyRating = findViewById(R.id.smile_rating);
        button_send=findViewById(R.id.button_send);
        input=findViewById(R.id.input);
        // code  is to make the Activity full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        smileyRating.setSmileySelectedListener(new SmileyRating.OnSmileySelectedListener() {
            @Override
            public void onSmileySelected(SmileyRating.Type type) {

                if (SmileyRating.Type.GREAT == type) {
                    Toast.makeText(Feedback_Ativity.this, "رائع", Toast.LENGTH_SHORT).show();

                }else
                if (SmileyRating.Type.BAD == type) {
                    Toast.makeText(Feedback_Ativity.this, "سيء", Toast.LENGTH_SHORT).show();

                }else
                if (SmileyRating.Type.TERRIBLE == type) {
                    Toast.makeText(Feedback_Ativity.this, "مكروه", Toast.LENGTH_SHORT).show();

                }else
                if (SmileyRating.Type.GOOD == type) {
                    Toast.makeText(Feedback_Ativity.this, "جيد جدا", Toast.LENGTH_SHORT).show();

                }else
                if (SmileyRating.Type.OKAY == type) {
                    Toast.makeText(Feedback_Ativity.this, "جيد", Toast.LENGTH_SHORT).show();

                }
                int rating = type.getRating();

            }
        });

        LayoutInflater inflater= getLayoutInflater();
        View view=inflater.inflate(R.layout.custm_toast,(ViewGroup)findViewById(R.id.con_toast));

        // Create Toast
        final  Toast toast =new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_HORIZONTAL,25,500);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                toast.show();
            }
        });
    }
}