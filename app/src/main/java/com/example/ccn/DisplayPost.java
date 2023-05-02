package com.example.ccn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayPost extends AppCompatActivity {


    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference root = db.getReference().child("Posts");
    private ArrayList<Model> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_post);

        String title = getIntent().getStringExtra("TITLE");
        String contents = getIntent().getStringExtra("CONTENTS");
        String location = getIntent().getStringExtra("LOCATION");

        TextView title_name = findViewById(R.id.title_text);
        TextView total_contents = findViewById(R.id.contents);
        Button location_name = findViewById(R.id.location);

        title_name.setText(title);
        total_contents.setText(contents);
        location_name.setText(location);

        location_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(location != null) {
                    Intent intent = new Intent(DisplayPost.this, MapsActivity.class);
                    intent.putExtra("LOCATION", location);
                    startActivity(intent);
                }
            }
        });
    }
}