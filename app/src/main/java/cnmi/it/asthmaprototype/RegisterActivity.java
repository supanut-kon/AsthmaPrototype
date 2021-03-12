package cnmi.it.asthmaprototype;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    Button Reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Reg = findViewById(R.id.registerbtn);

        Reg.setOnClickListener(v -> {
            finish();
            Intent dash = new Intent(RegisterActivity.this, Dashboard.class);
            startActivity(dash);
        });


    }
}
