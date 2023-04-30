package com.example.ccn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Profile_Manager extends AppCompatActivity {
    TextView profilenameLabel,profileusernameLabel;
    TextInputLayout profilename,profileusername,profiledescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_manager);
        profilename = findViewById(R.id.user_entered_name);
        profileusername = findViewById(R.id.user_entered_username);
        profiledescription = findViewById(R.id.user_entered_Description);
        profilenameLabel = findViewById(R.id.name_label);
        profileusernameLabel = findViewById(R.id.username_label);
        showAllUserData();
    }
    private void showAllUserData(){
        Intent intent  = getIntent();
        String uid = intent.getStringExtra("uid");
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        String description = intent.getStringExtra("description");

        profilename.getEditText().setText(name);
        profileusername.getEditText().setText(username);
        profilenameLabel.setText(name);
        profileusernameLabel.setText(username);
        profiledescription.getEditText().setText(description);
    }
}