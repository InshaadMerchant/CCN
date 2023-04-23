package com.example.ccn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration_1 extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword, editTextFirstName, editTextLastName, editTextSecurityQuestion, editTextAnswer;
    Button Register;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration1);
        mAuth = FirebaseAuth.getInstance();
        editTextFirstName = findViewById(R.id.FirstName);
        editTextLastName = findViewById(R.id.LastName);
        editTextEmail = findViewById(R.id.Email);
        editTextPassword = findViewById(R.id.password);
        editTextSecurityQuestion = findViewById(R.id.SecurityQuestion);
        editTextAnswer = findViewById(R.id.Answer);
        Register = findViewById(R.id.btn_next);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.LoginNow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registration_2.class);
                startActivity(intent);
                finish();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String FirstName, LastName, Email, password, SecurityQuestion, Answer;
                FirstName = String.valueOf(editTextFirstName.getText());
                LastName = String.valueOf(editTextLastName.getText());
                Email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                SecurityQuestion = String.valueOf(editTextSecurityQuestion.getText());
                Answer = String.valueOf(editTextAnswer.getText());

                if (TextUtils.isEmpty(FirstName))
                {
                    Toast.makeText(Registration_1.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(LastName))
                {
                    Toast.makeText(Registration_1.this, "Enter Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Email))
                {
                    Toast.makeText(Registration_1.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(Registration_1.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(SecurityQuestion))
                {
                    Toast.makeText(Registration_1.this, "Enter Security Question", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Answer))
                {
                    Toast.makeText(Registration_1.this, "Enter Answer", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(Email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful())
                                {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(Registration_1.this, "Account Created.",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    progressBar.setVisibility(View.GONE);
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Registration_1.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}
