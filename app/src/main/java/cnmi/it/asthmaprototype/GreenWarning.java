package cnmi.it.asthmaprototype;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GreenWarning extends AppCompatActivity {

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_warning);

        fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(v -> finish());
    }
}