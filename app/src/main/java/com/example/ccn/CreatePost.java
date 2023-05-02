package com.example.ccn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    FirebaseAuth mauth;
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
            }
        });

    }
    private void Insert()
    {
        String title = Title.getText().toString();
        String description = Description.getText().toString();
        CreatePostHelper helperclass = new CreatePostHelper(title,description);
        reference.child("Posts").child(mauth.getCurrentUser().getUid().toString()).setValue(helperclass)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {


                        }
                    }
                });


    }

}