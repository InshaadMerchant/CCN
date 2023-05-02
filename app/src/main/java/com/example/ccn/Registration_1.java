package com.example.ccn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.OAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registration_1 extends AppCompatActivity {
    TextInputEditText regName, regUsername, regEmail, regDescription, security_answer, regReenterPassword;
    Button regVerify;
    //    Required database references and variables
    FirebaseAuth mAuth;
    OAuthProvider.Builder provider = OAuthProvider.newBuilder("microsoft.com");
    FirebaseDatabase instance = FirebaseDatabase.getInstance();
    //    user_idref is used to check existing users
//    whereas root_ref is being used to find child"users" and post data to database.
    DatabaseReference user_idref = instance.getReference().child("user_id");
    DatabaseReference root_ref = instance.getReference();
    boolean already_registered = false;

    private Boolean validateName() {
        String val = regName.getText().toString();
        if (val.isEmpty()) {
            regName.setError("This Field is Mandatory");
            return false;
        } else {
            regName.setError(null);
            return true;
        }
    }
    private Boolean validateDescription() {
        String val = regDescription.getText().toString();
        if (val.isEmpty()) {
            regDescription.setError("This Field is Mandatory");
            return false;
        } else {
            regDescription.setError(null);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = regUsername.getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Username is too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("White Spaces are not allowed");
            return false;
        } else {
            regUsername.setError(null);
            return true;
        }
    }


    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration1);
        mAuth = FirebaseAuth.getInstance();
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regDescription = findViewById(R.id.description);
        regVerify = findViewById(R.id.Verification_button);




        regVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                provider.addCustomParameter("tenant", "mavs.uta.edu");
                //Get all Values and convert them to String
                String name = regName.getText().toString();
                String username = regUsername.getText().toString();
                String description = regDescription.getText().toString();
                if (!validateDescription() | !validateName() | !validateUsername()) {
                    return;
                }
                RegistrationHelperClass new_user = new RegistrationHelperClass(name, username, description);
                mAuth.startActivityForSignInWithProvider(Registration_1.this, provider.build()).addOnSuccessListener( new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                user_idref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot userid : snapshot.getChildren()) {
                                            String compare = userid.getValue(String.class);
                                            if (mAuth.getCurrentUser().getUid().matches(compare)) {
                                                already_registered = true;
                                            }
                                        }
                                        if (already_registered) {
                                            Toast.makeText(Registration_1.this, "An account is already registered  under this id. Please sign in", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Registration_1.this, Customized_feed.class);
                                            intent.putExtra("uid",mAuth.getCurrentUser().getUid().toString());
                                            intent.putExtra("name",name);
                                            intent.putExtra("username",username);
                                            intent.putExtra("description",description);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            root_ref.child("users").child(mAuth.getCurrentUser().getUid()).setValue(new_user);
                                            user_idref.child(mAuth.getCurrentUser().getUid()).setValue(mAuth.getCurrentUser().getUid().toString());
                                            Toast.makeText(Registration_1.this, "USER REGISTERED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Registration_1.this, Customized_feed.class);
                                            intent.putExtra("uid",mAuth.getCurrentUser().getUid().toString());
                                            intent.putExtra("name",name);
                                            intent.putExtra("username",username);
                                            intent.putExtra("description",description);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Log.i("Error", "Error fetching data from Firebase.");
                                        finish();
                                    }
                                });

                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Registration_1.this, "Verification Email has been sent.",
                                                Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                });
            }
        });

    }
}