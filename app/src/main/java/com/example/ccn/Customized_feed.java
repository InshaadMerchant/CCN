package com.example.ccn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Customized_feed extends AppCompatActivity implements PostInterface, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    String uid, name, username, description;
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;

    private RecyclerView recyclerView;

    private Button edit, delete;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference root = db.getReference().child("Posts");
    private FeedAdapter adapter;
    private ArrayList<Model> list;
    public ImageButton postButton;
    private SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized_feed);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();
        Intent intent  = getIntent();
        uid = mAuth.getCurrentUser().getUid();
        //uid = intent.getStringExtra("uid");
        name = intent.getStringExtra("name");
        username = intent.getStringExtra("username");
        description = intent.getStringExtra("description");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new FeedAdapter(this, list, this);

        recyclerView.setAdapter(adapter);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);
        navigationView.bringToFront();
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Model model = dataSnapshot.getValue(Model.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        postButton = (ImageButton) findViewById(R.id.post_button);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customized_feed.this, CreatePost.class);
                startActivity(intent);
            }
        });
    }

    private void filterList(String text) {
        ArrayList<Model> filteredList = new ArrayList<>();
        for(Model model:list ) {
            if(model.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(model);
            }
        }

        if(filteredList.isEmpty()) {
            Toast.makeText(this, "No matches found", Toast.LENGTH_SHORT).show();
        } else{
            adapter.setFilteredList(filteredList);
        }
    }

    @Override
    public void onButtonClick(int position) {
        Intent intent = new Intent(Customized_feed.this, DisplayPost.class);
        Model model = list.get(position);

        intent.putExtra("TITLE", model.getTitle());
        intent.putExtra("CONTENTS", model.getContents());
        intent.putExtra("LOCATION", model.getLocation());

        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
                    case R.id.home: {
                        Toast.makeText(Customized_feed.this, "Home selected", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Customized_feed.this,Customized_feed.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.add_post: {
                        Toast.makeText(Customized_feed.this, "Add Post Selected", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Customized_feed.this,CreatePost.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.about: {
                        Toast.makeText(Customized_feed.this, "Opening Profile Manager", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Customized_feed.this, Profile_Manager.class);
                        intent.putExtra("name",name);
                        intent.putExtra("username",username);
                        intent.putExtra("description",description);
                        startActivity(intent);
                        break;
                    }
                    case R.id.logout: {
                        Toast.makeText(Customized_feed.this, "Log-Out Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Customized_feed.this, Login.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.share: {
                        Toast.makeText(Customized_feed.this, "share selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
        }
        return true;
    }
}