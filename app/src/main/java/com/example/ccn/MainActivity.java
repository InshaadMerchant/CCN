package com.example.ccn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    TextView textView;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        //Not sure if these are needed
         */

        Intent intent = new Intent(getApplicationContext(), Customized_feed.class);
        startActivity(intent);
        finish();
    }
}