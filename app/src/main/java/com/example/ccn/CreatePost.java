package com.example.ccn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreatePost extends AppCompatActivity {
    Button btnpublish, btncancel;
    TextInputEditText Title, Description;
    DatabaseReference reference;
    FirebaseAuth mauth= FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        btncancel = findViewById(R.id.cancelbutton);
        btnpublish = findViewById(R.id.publishButton);
        Title = findViewById(R.id.title_post);
        Description = findViewById(R.id.description);
        reference = FirebaseDatabase.getInstance().getReference();

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatePost.this, Customized_feed.class);
                startActivity(intent);
                finish();
            }
        });
        btnpublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insert();
                Intent intent = new Intent(CreatePost.this, Customized_feed.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void Insert()
    {
        String title = Title.getText().toString();
        String description = Description.getText().toString();
        Model helperclass = new Model(title,description);
        reference.child("Posts").push().setValue(helperclass);
                reference.child("user_post").child(mauth.getCurrentUser().getUid().toString()).push().setValue(helperclass);
                //.addOnCompleteListener(new OnCompleteListener<Void>() {
                  //  @Override
                    //public void onComplete(@NonNull Task<Void> task) {
                      //  if(task.isSuccessful())
                        //{


                        //}
                    }
                //});


    //}

}