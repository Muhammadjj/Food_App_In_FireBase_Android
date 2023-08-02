package org.pk.cas.fastfood1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

ImageView splash_Screen_welcome, splash_screen_image;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        Initialized the id's
        splash_screen_image = findViewById(R.id.splash_screen_image);
        splash_Screen_welcome = findViewById(R.id.splash_Screen_welcome);


        /*Blink Animation for the splash_Screen_welcome Image */
        Animation animation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.blink_animation);
        splash_Screen_welcome.startAnimation(animation);

        /*Blink Animation for the splash_screen_image Image */
        Animation bounce_anim = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.bounce_animation);
        splash_screen_image.startAnimation(bounce_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
            }
        },3000);

    }
}