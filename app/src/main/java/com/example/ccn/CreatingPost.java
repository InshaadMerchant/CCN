package com.example.ccn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseRegistrar;
import com.google.firebase.database.FirebaseDatabase;

public class CreatingPost extends AppCompatActivity {
    public TextInputEditText title;
    public TextInputEditText content;
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    FirebaseDatabase ref= FirebaseDatabase.getInstance();
    DatabaseReference root= ref.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_post);
        title = findViewById(R.id.title_box);
        content = findViewById(R.id.content_box);
        String title_text = title.getText().toString();
        String content_text = content.getText().toString();
        Button publish_button = findViewById(R.id.publish_button);
        Button location_button = findViewById(R.id.location_button);
        Model post= new Model(title_text,content_text);
        publish_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v)
                {
                    //ToDo: Verify that user have some content and title which is not null
                    root.child("Posts").push().setValue(post);
                    root.child("user_post").child(mAuth.getCurrentUser().getUid()).setValue(post);
                    Intent intent = new Intent(CreatingPost.this, Customized_feed.class);
                    startActivity(intent);
                    finish();
                }
        });
    }

}
