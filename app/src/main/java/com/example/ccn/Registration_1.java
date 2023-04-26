package com.example.ccn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration_1 extends AppCompatActivity{
    TextInputEditText regName, regUsername, regEmail, regPassword, security_answer, regReenterPassword ;
    Button regNextButton, regtoLoginButton;
//    String[] Security_Questions = getResources().getStringArray(R.array.Security_Question);
    public static String regsuccess= "passkey";
    private Spinner Security_Question;
    FirebaseAuth mAuth,userAuth;
    OAuthProvider.Builder provider = OAuthProvider.newBuilder("microsoft.com");

    FirebaseDatabase rootNode= FirebaseDatabase.getInstance();
    DatabaseReference reference;
    private Boolean validateName(){
        String val = regName.getText().toString();
        if(val.isEmpty()){
            regName.setError("This Field is Mandatory");
            return false;
        }
        else {
            regName.setError(null);
            return true;
        }
    }
    private Boolean validateUsername(){
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
    /*private Boolean validateemail(){
        String val = regEmail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        }//else if(!val.contains(".edu") | !val.contains(".uta.edu") | !val.contains("mavs.uta.edu")){
           // regEmail.setError("Use your Organization Email to Register");
            //return false;
        //}
        else {
            regEmail.setError(null);
            return true;
        }
    }*/

    private Boolean validatePassword() {
        String val1 = regPassword.getText().toString();
        String val2 = regReenterPassword.getText().toString();
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
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val1.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        }
        else if (val2.isEmpty()) {
            regReenterPassword.setError("Field cannot be empty");
            return false;
        } else if (!val2.matches(passwordVal)) {
            regReenterPassword.setError("Password is too weak");
            return false;
        } else if (!val2.contains(val1)) {
            regReenterPassword.setError("Password does not match");
            return false;
        }
        else {
            regPassword.setError(null);
            regReenterPassword.setError(null);
            return true;
        }
    }
    private Boolean validateSecurity_answer(){
        String val = security_answer.getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            security_answer.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            security_answer.setError("Answer is too long");
            return false;
        } else {
            security_answer.setError(null);
            return true;
        }
    }
    public void isloggedin()
    {

    }

    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration1);
        mAuth = FirebaseAuth.getInstance();
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPassword = findViewById(R.id.reg_password);
        regReenterPassword = findViewById(R.id.reg_renter_password);
        regtoLoginButton = findViewById(R.id.existing_user);
        Security_Question = findViewById(R.id.secutity_Question);
        regNextButton = findViewById(R.id.Next_Button);
//        ArrayAdapter adapter = new ArrayAdapter( this, android.R.layout.simple_spinner_item,Security_Questions);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        Security_Question.setAdapter(adapter);
        security_answer = findViewById(R.id.secutity_Question_answer);

        String security_question = Security_Question.getSelectedItem().toString();

        regtoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Registration_1.this,Login.class);

                if( mAuth.getCurrentUser()!=null)
                {
                    Toast.makeText(Registration_1.this, "user logged in.",
                            Toast.LENGTH_SHORT).show();
                }
                FirebaseAuth.getInstance().signOut();
                startActivity(intent);
                finish();
            }
        });

        regNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    boolean is_verified = false;
//                provider.addCustomParameter("login_hint","@mavs.uta.edu");
                provider.addCustomParameter("tenant","mavs.uta.edu");
                reference = rootNode.getReference();
                    //Get all Values and convert them to String
                    String name = regName.getText().toString();
                    String username = regUsername.getText().toString();
                    String email = regEmail.getText().toString();
                    String password = regPassword.getText().toString();
                    String security_question_answer = security_answer.getText().toString();
                    String security_question="jdskj";
                    RegistrationHelperClass helperClass = new RegistrationHelperClass(name,username,password,email,security_question,security_question_answer);
//                    reference.child(username).setValue(helperClass);
//                    Toast.makeText(Registration_1.this, "Verification Email has been sent.",
//                            Toast.LENGTH_SHORT).show();
//
                    mAuth.startActivityForSignInWithProvider(Registration_1.this , provider.build()).addOnSuccessListener(
                                    new OnSuccessListener<AuthResult>() {
                                        @Override
                                        public void onSuccess(AuthResult authResult) {
//                                            Intent intent= new Intent(Registration_1.this,registeration_success.class);
//                                            intent.putExtra(regsuccess,authResult.getAdditionalUserInfo().getUsername().toString());
//                                            startActivity(intent);
                                            Toast.makeText(Registration_1.this, "WORKING",
                                                    Toast.LENGTH_SHORT).show();
                                            // User is signed in.
                                            // IdP data available in
//                                              authResult.getAdditionalUserInfo().getProfile();
//                                           provider = FirebaseAuth.getInstance();
//                                            is_verified=true;
//                                            reference.child(username).setValue(helperClass);
                                            // The OAuth access token can also be retrieved:
                                            // ((OAuthCredential)authResult.getCredential()).getAccessToken().
                                            // The OAuth secret can be retrieved by calling:
                                            // ((OAuthCredential)authResult.getCredential()).getSecret().

                                        }
                                    })
                            .addOnFailureListener(
                                    new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Registration_1.this, "Verification Email has been sent.",
                                            Toast.LENGTH_SHORT).show();
                                            // Handle failure.
                                            return;
                                        }
                                    });

            }
        });

    }
}