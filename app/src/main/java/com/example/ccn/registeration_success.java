package com.example.ccn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class registeration_success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration_success);
        Intent intent = getIntent();
        String getname= intent.getStringExtra(Registration_1.regsuccess);
       Toast.makeText(this, getname,Toast.LENGTH_SHORT).show();
    }
}