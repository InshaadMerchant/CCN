package com.example.ccn;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class post_button
{
    public ImageButton postButton;
    postButton = (ImageButton) findViewById(R.id.post_button);
    postButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(post_button.this, CreatingPost.class);
        startActivity(intent);
    }
});
}