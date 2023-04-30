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

public class CreatingPost extends AppCompatActivity {
    public TextInputEditText title;
    public TextInputEditText content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_post);
        title = findViewById(R.id.title_box);
        content = findViewById(R.id.content_box);
        String title_text = String.valueOf(title.getText());
        String content_text = String.valueOf(content.getText());
        Button publish_button = findViewById(R.id.publish_button);
        Button location_button = findViewById(R.id.location_button);
        publish_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v)
                {
                    Intent intent = new Intent(CreatingPost.this, Registration_2.class);
                    startActivity(intent);
                    finish();
                }
        });
    }

}
