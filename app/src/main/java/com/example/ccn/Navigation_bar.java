package com.example.ccn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Navigation_bar extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch(item.getItemId())
                {
                    case R.id.home:
                    {
                        Toast.makeText(Navigation_bar.this, "home selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.add_post:
                    {
                        Toast.makeText(Navigation_bar.this, "Add Post Selected", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(MainActivity.this, creating_post.class);
                        //startActivity(intent);
                        break;
                    }
                    case R.id.about:
                    {
                        Toast.makeText(Navigation_bar.this, "about selected", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(MainActivity.this, profile.class);
                        //startActivity(intent);
                        break;
                    }
                    case R.id.logout:
                    {
                        Toast.makeText(Navigation_bar.this, "logout selected", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(MainActivity.this, login.class);
                        //startActivity(intent);
                        break;
                    }
                    case R.id.share:
                    {
                        Toast.makeText(Navigation_bar.this, "share selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                return false;
            }
        });
    }
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
}