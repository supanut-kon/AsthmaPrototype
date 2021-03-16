package cnmi.it.asthmaprototype.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import cnmi.it.asthmaprototype.R;


public class splashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent tologin = new Intent(splashScreen.this, LoginActivity.class);
                startActivity(tologin);
            }
        }, 5000);

    }
}
