package com.example.final_projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Splash_Screen_1 extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    private Animation animation;
    private LottieAnimationView animationView;
    private TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen_1);
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

        //this code for the splash screen what to do after finish it
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent2 = new Intent(Splash_Screen_1.this, Splash_Screen_2.class);
                startActivity(intent2);
            }
        }, SPLASH_SCREEN);
    }
}