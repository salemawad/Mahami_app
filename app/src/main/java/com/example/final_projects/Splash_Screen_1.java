package com.example.final_projects;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class Splash_Screen_1 extends AppCompatActivity {
    private static int SPLASH_SCREEN = 2500;
    private Animation animation;
    private LottieAnimationView animationView;
    private TextView textView1, textView2;
    private SharedPreferences sharedPreferences;
    Boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen_1);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        animationView = findViewById(R.id.animate1);
        textView1 = findViewById(R.id.Welcome_text);
        textView2 = findViewById(R.id.Welcome_text2);

        //Animation For the objects
        animation = AnimationUtils.loadAnimation(this, R.anim.anim);

        //use Animation On Objects
        animationView.setAnimation(animation);
        textView1.setAnimation(animation);
        textView2.setAnimation(animation);

        // code  is to make the Activity full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Check For THe Activity class
        firstTime = sharedPreferences.getBoolean("firsTime", true);

        //this code for the splash screen what to do after finish it
        if (firstTime) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    firstTime = false;
                    editor.putBoolean("firstTime", firstTime);
                    editor.apply();
                    Intent intent2 = new Intent(Splash_Screen_1.this, Splash_Screen_2.class);
                    startActivity(intent2);
                }
            }, SPLASH_SCREEN);
        }else {
            Intent intent2 = new Intent(Splash_Screen_1.this, Login_Sing_up_Activity.class);
            startActivity(intent2);
        }

    }
}