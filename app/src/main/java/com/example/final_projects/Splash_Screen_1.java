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
    SharedPreferences preferences;
    Boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen_1);

        //=======================================Casting=====================================================
        LottieAnimationView animationView = findViewById(R.id.animate1);
        TextView textView1 = findViewById(R.id.Welcome_text);
        TextView textView2 = findViewById(R.id.Welcome_text2);

        //=============This Code To Show The Activity Once When App Open For First Time=======================
        preferences = getSharedPreferences("PREFERENCES", MODE_PRIVATE);
        firstTime = preferences.getBoolean("firstTime", true);
        //======If App is was Opened For First Time Do It .....=======
        if (firstTime) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences.Editor editor = preferences.edit();
                    firstTime = false;
                    editor.putBoolean("firstTime", false);
                    editor.apply();
                    Intent intent = new Intent(Splash_Screen_1.this, Splash_Screen_2.class);
                    startActivity(intent);
                    finish();

                }
            }, 5000);
        } else {
            //======Is Not Else ...... <_<=======
            Intent intent = new Intent(Splash_Screen_1.this, Splah_Dilog.class);
            startActivity(intent);
            finish();
        }

        // =====================================Animation For the objects====================================================
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim);

        // =====================================use Animation On Objects=====================================================
        animationView.setAnimation(animation);
        textView1.setAnimation(animation);
        textView2.setAnimation(animation);

        // ==================================== code  is to make the Activity full screen====================================
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}