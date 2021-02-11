package cnmi.it.asthmaprototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LoginActivity extends AppCompatActivity {
    Button google;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        google = findViewById(R.id.google);
        register = findViewById(R.id.register);

        google.setOnClickListener(v -> {
            finish();
            Intent dashboard = new Intent(LoginActivity.this, Dashboard.class);
            startActivity(dashboard);
        });

        register.setOnClickListener(v -> {
            finish();
            Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(register);
        });
    }
    
    
}
