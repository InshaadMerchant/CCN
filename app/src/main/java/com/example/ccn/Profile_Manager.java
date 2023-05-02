package com.example.ccn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile_Manager extends AppCompatActivity {
    TextView profilenameLabel,profileusernameLabel;
    TextInputLayout profilename,profileusername,profiledescription;
    FirebaseAuth mAuth;
    String uid, name, username, description;
    DatabaseReference reference;
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
        reference = FirebaseDatabase.getInstance().getReference("users");
    }
    private void showAllUserData(){
        Intent intent  = getIntent();
        uid = mAuth.getCurrentUser().getUid();
        //uid = intent.getStringExtra("uid");
        name = intent.getStringExtra("name");
        username = intent.getStringExtra("username");
        description = intent.getStringExtra("description");

        profilename.getEditText().setText(name);
        profileusername.getEditText().setText(username);
        profilenameLabel.setText(name);
        profileusernameLabel.setText(username);
        profiledescription.getEditText().setText(description);
    }
    public void update (View view){
        if(isNameChanged() | isUsernameChanged() | isDescriptionChanged()){
            Toast.makeText(this,"Data has been updated",Toast.LENGTH_SHORT).show();
            profileusernameLabel.setText(username);
            profiledescription.getEditText().setText(description);
        }
        else {
            Toast.makeText(this,"Data is same and cannot be updated",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNameChanged() {
        if(!name.equals(profilename.getEditText().getText().toString())){
            reference.child(uid).child("name").setValue(profilename.getEditText().getText().toString());
            return true;
        }
        else {
            return false;
        }
    }
    private boolean isUsernameChanged() {
        if(!name.equals(profileusername.getEditText().getText().toString())){
            reference.child(uid).child("username").setValue(profileusername.getEditText().getText().toString());
            return true;
        }
        else {
            return false;
        }
    }
    private boolean isDescriptionChanged() {
        if(!name.equals(profiledescription.getEditText().getText().toString())){
            reference.child(uid).child("description").setValue(profiledescription.getEditText().getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

}