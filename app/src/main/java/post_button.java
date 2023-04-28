import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import com.example.ccn.MainActivity;
import com.example.ccn.R;
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
