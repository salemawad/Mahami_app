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
    final private static int SPLASH_SCREEN = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen_1);
        LottieAnimationView animationView = findViewById(R.id.animate1);
        TextView textView1 = findViewById(R.id.Welcome_text);
        TextView textView2 = findViewById(R.id.Welcome_text2);
        // ==================================================================================================================
        //this code for the splash screen what to do after finish it
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //This Code To Show The Activity Once When App Open For First Time
                SharedPreferences preferences = getSharedPreferences("PREFERENCES", MODE_PRIVATE);
                String FirstTimeInstall = preferences.getString("FirstTimeInstall", "");

                //If App is was Opened For First Time Do It .....
                if (FirstTimeInstall.equals("Yes")) {
                    Intent intent2 = new Intent(Splash_Screen_1.this, Splash_Screen_2.class);
                    startActivity(intent2);
                } else {
                    //Is Not Else ...... <_<
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("FirstTimeInstall", "Yes");
                    editor.apply();
                }
            }
        }, SPLASH_SCREEN);
        // ==================================================================================================================
        //Animation For the objects
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim);
        // ==================================================================================================================
        //use Animation On Objects
        animationView.setAnimation(animation);
        textView1.setAnimation(animation);
        textView2.setAnimation(animation);
        // ==================================================================================================================
        // code  is to make the Activity full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // ==================================================================================================================
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}