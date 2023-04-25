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
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    /*private Boolean validateemail(){
        String val = LoginEmail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            LoginEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            LoginEmail.setError("Invalid email address");
            return false;
        }else if(!val.contains(".edu")){
            LoginEmail.setError("Use your Organization Email to Register");
            return false;
        }
        else {
            LoginEmail.setError(null);
            return true;
        }
    }*/
    private Boolean validatePassword() {
        String val1 = LoginPassword.getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
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
        }
        else {
            LoginPassword.setError(null);
            return true;
        }
    }
    Button CallSignUp,LoginButton;
    TextInputEditText LoginEmail, LoginPassword;
    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        CallSignUp= findViewById(R.id.signup_button);
        CallSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Login.this,Registration_1.class);
                startActivity(intent);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        LoginEmail = findViewById(R.id.login_Email);
        LoginPassword = findViewById(R.id.login_password);
        LoginButton = findViewById(R.id.loginButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !validatePassword()){
                    return;
                }
                String email,password;
                email = String.valueOf(LoginEmail.getText());
                password = String.valueOf(LoginPassword.getText());
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Login.this, "Login Successful.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login.this, "Check your Username or Password",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }
}