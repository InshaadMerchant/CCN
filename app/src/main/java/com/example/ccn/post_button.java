package com.example.ccn;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class post_button
{
    private ImageButton post_button;
    post_button = (ImageButton) findViewById(R.id.post_button);
    post_button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(post_button.this, creating_post.class);
        startActivity(intent);
    }
});
}