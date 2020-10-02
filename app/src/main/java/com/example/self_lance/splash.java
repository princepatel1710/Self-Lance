package com.example.self_lance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3500;
    //variable

    Animation pushdown, pushright;
    ImageView splash_image;
    TextView splash_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //animation
        pushdown= AnimationUtils.loadAnimation(this, R.anim.push_down);
        pushright= AnimationUtils.loadAnimation(this, R.anim.push_right);

        //hooks
        splash_image=findViewById(R.id.logo);
        splash_logo=findViewById(R.id.logoname);

        splash_image.setAnimation(pushdown);
        splash_logo.setAnimation(pushright);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splash.this, login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}