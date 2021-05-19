package cnmi.it.asthmaprototype.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cnmi.it.asthmaprototype.R;

public class FinalWarningActivity extends AppCompatActivity {

    FloatingActionButton fab, yellowTimer;
    TextView yellowtext, yellowinhaler, timer, againText, timerText, yellowHeader;
    Button hiddenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yellow_warning);

        timer = findViewById(R.id.timer);
        yellowtext = findViewById(R.id.RedTextview);
        yellowinhaler = findViewById(R.id.RedinhalerText);
        yellowTimer = findViewById(R.id.yellowCalFab);
        hiddenBtn = findViewById(R.id.hiddenBtn);
        againText = findViewById(R.id.againText);
        timerText = findViewById(R.id.TimerText);
        yellowHeader = findViewById(R.id.YellowHeader1);

    }

    
}