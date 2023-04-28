package com.example.ccn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity  {

    Handler handler;
    Runnable runnable;
    Animation topAnim, bottomAnim;
    ImageView img;
    LinearLayout layout;

    @SuppressLint({"Range", "SuspiciousIndentation", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        topAnim= AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);


        setContentView(R.layout.activity_main);
        img= findViewById(R.id.homescreen);
        layout = findViewById(R.id.back_logo);
        img.setAnimation(bottomAnim);
        layout.setAnimation(topAnim);
        img.animate().alpha(3000).setDuration(0);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent dsp = new Intent(MainActivity.this,Login.class);
                Pair[] pairs = new Pair[2];
                pairs[0]= new Pair<View,String>(img,"college_networking");
                pairs[1]= new Pair<LinearLayout,String>(layout,"layout");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                startActivity(dsp,options.toBundle());
                finish();
            }
        },3000);


    }
}