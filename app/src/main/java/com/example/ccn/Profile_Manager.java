package com.example.ccn;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile_Manager extends AppCompatActivity {
    TextView profilenameLabel,profileusernameLabel,postcount;
    TextInputLayout profilename,profileusername,profiledescription;
    FirebaseAuth mauth= FirebaseAuth.getInstance();
    String uid, name, username, description;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    int post_count=0;

    DatabaseReference reference= database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_manager);

        profilename = findViewById(R.id.user_entered_name);
        profileusername = findViewById(R.id.user_entered_username);
        profiledescription = findViewById(R.id.user_entered_Description);
        profilenameLabel = findViewById(R.id.name_label);
        profileusernameLabel = findViewById(R.id.username_label);
        postcount= findViewById(R.id.number_post);
        showAllUserData();
//        reference = FirebaseDatabase.getInstance().getReference("users");
    }
    private void showAllUserData(){
        Intent intent  = getIntent();
        uid = mauth.getCurrentUser().getUid().toString();
        reference.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 RegistrationHelperClass user= snapshot.getValue(RegistrationHelperClass.class);
                profilename.getEditText().setText(user.getName().toString());
                profileusername.getEditText().setText(user.getUsername().toString());
                profilenameLabel.setText(user.getName());
                profileusernameLabel.setText(user.getUsername());
                profiledescription.getEditText().setText(user.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        post_count=0;
        reference.child("user_post").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren())
                {
                    post_count++;
                }
                postcount.setText(String.valueOf(post_count));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void update (View view){
//        if(isNameChanged() | isUsernameChanged() | isDescriptionChanged()){
//            Toast.makeText(this,"Data has been updated",Toast.LENGTH_SHORT).show();
            profileusername = findViewById(R.id.user_entered_username);
            String username= profileusername.getEditText().getText().toString();
//            profileusername.getEditText().setText(username);
            profilename = findViewById(R.id.user_entered_name);
            String name = profilename.getEditText().getText().toString();
//            profilename.getEditText().setText(name);
            profiledescription = findViewById(R.id.user_entered_Description);
            String description =profiledescription.getEditText().getText().toString();
            RegistrationHelperClass user = new RegistrationHelperClass(name,username,description);
            reference.child("users").child(uid).setValue(user);
            Intent intent = new Intent(Profile_Manager.this,Customized_feed.class);
            startActivity(intent);
//        else
//        {
//            Toast.makeText(this,"Data is same and cannot be updated",Toast.LENGTH_SHORT).show();
//        }
    }

//    private boolean isNameChanged() {
//        if(!name.equals(profilename.getEditText().getText().toString())){
//            reference.child(uid).child("name").setValue(profilename.getEditText().getText().toString());
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
//    private boolean isUsernameChanged() {
//        if(!name.equals(profileusername.getEditText().getText().toString())){
//            reference.child(uid).child("username").setValue(profileusername.getEditText().getText().toString());
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
//    private boolean isDescriptionChanged() {
//        if(!name.equals(profiledescription.getEditText().getText().toString())){
//            reference.child(uid).child("description").setValue(profiledescription.getEditText().getText().toString());
//            return true;
//        }
//        else {
//            return false;
//        }
//    }

}