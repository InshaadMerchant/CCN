package com.example.ccn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private Boolean validatePassword() {
        String val1 = LoginPassword.getText().toString();
        String passwordVal = "^" +
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val1.isEmpty()) {
            LoginPassword.setError("Field cannot be empty");
            return false;
        } else if (!val1.matches(passwordVal)) {
            LoginPassword.setError("Password is too weak");
            return false;
        } else {
            LoginPassword.setError(null);
            return true;
        }
    }

    Button CallSignUp, LoginButton, Registeration;
    TextInputEditText LoginEmail, LoginPassword;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    OAuthProvider.Builder provider = OAuthProvider.newBuilder("microsoft.com");
    FirebaseDatabase instance= FirebaseDatabase.getInstance();
    DatabaseReference user_idref = instance.getReference().child("user_id");
    boolean is_user=false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        LoginButton = findViewById(R.id.loginButton);
        Registeration= findViewById(R.id.registerbutton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Check if User is not registered, If the user is not registered, ask them to sign up

//              Add custom param sets tenant to UTA. This ensures that all authentication takes place through UTA SSO login.
                provider.addCustomParameter("tenant", "mavs.uta.edu");
                mAuth.startActivityForSignInWithProvider(Login.this, provider.build()).addOnSuccessListener( new OnSuccessListener<AuthResult>() {

                            @Override
                            public void onSuccess(AuthResult authResult) {
                                user_idref.addListenerForSingleValueEvent(
                                        new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                for(DataSnapshot userid: snapshot.getChildren())
                                                {
                                                    String compare= userid.getValue(String.class);
                                                    if(mAuth.getCurrentUser().getUid().matches(compare))
                                                    {
                                                        Toast.makeText(Login.this, "Welcome Back", Toast.LENGTH_SHORT).show();
                                                        is_user=true;
                                                    }
                                                }
                                                if(!is_user)
                                                {
                                                    Toast.makeText(Login.this, "Please sign up first", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(Login.this,Registration_1.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                                else
                                                {
                                                    Intent intent = new Intent(Login.this, Registration_2.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });

                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Login.this, "Login failed. Please try again after restarting the app", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });

            }
        });
        Registeration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registration_1.class);
                startActivity(intent);
                finish();
            }
        });
    }
}