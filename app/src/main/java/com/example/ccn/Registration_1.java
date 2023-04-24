package com.example.ccn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration_1 extends AppCompatActivity {
    TextInputEditText regName, regUsername, regEmail, regPassword, security_answer;
    Button regNextButton, regtoLoginButton;
    private Spinner Security_Question;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration1);

        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPassword = findViewById(R.id.reg_password);
        regtoLoginButton = findViewById(R.id.existing_user);
        Security_Question = findViewById(R.id.secutity_Question);
        String[] Security_Questions = getResources().getStringArray(R.array.Security_Question);
        ArrayAdapter adapter = new ArrayAdapter( this, android.R.layout.simple_spinner_item,Security_Questions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Security_Question.setAdapter(adapter);
        security_answer = findViewById(R.id.secutity_Question_answer);
        regNextButton = findViewById(R.id.Next_Button);
        String security_question = Security_Question.getSelectedItem().toString();

        regtoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Registration_1.this,Login.class);
                startActivity(intent);
            }
        });
        regNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Registration Info");
                reference.setValue("");

            }
        });
    }
}