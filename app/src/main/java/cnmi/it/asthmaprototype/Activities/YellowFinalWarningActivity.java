package cnmi.it.asthmaprototype.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import cnmi.it.asthmaprototype.R;

public class YellowFinalWarningActivity extends AppCompatActivity {

    FloatingActionButton fab, yellowTimer;
    TextView yellowtext, yellowinhaler, timer, againText, timerText, yellowHeader, yellowDesc;
    Button hiddenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yellow_warning);

        fab = findViewById(R.id.floatingActionButton);
        timer = findViewById(R.id.timer);
        yellowtext = findViewById(R.id.RedTextview);
        yellowinhaler = findViewById(R.id.RedinhalerText);
        yellowTimer = findViewById(R.id.yellowCalFab);
        hiddenBtn = findViewById(R.id.hiddenBtn);
        againText = findViewById(R.id.againText);
        timerText = findViewById(R.id.TimerText);
        yellowHeader = findViewById(R.id.YellowHeader1);
        yellowDesc = findViewById(R.id.YellowDesc);

        hiddenBtn.setVisibility(View.INVISIBLE);
        againText.setVisibility(View.INVISIBLE);
        yellowTimer.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.INVISIBLE);

        yellowTimer.setOnClickListener(v -> new CountDownTimer(9000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
                dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                Date date = new Date(millisUntilFinished);
                timer.setText(dateFormat.format(date)+ "\nเมื่อเวลานับถอยหลังเสร็จ กรุณาวัดแรงลมสูงสุดอีกครั้ง");
            }

            @Override
            public void onFinish() {
                timer.setVisibility(View.INVISIBLE);
                hiddenBtn.setVisibility(View.VISIBLE);
                againText.setVisibility(View.VISIBLE);
                yellowTimer.setVisibility(View.INVISIBLE);
                timerText.setVisibility(View.INVISIBLE);
                fab.setVisibility(View.INVISIBLE);

            }
        }.start());
        hiddenBtn.setOnClickListener(v -> startActivity(new Intent(YellowFinalWarningActivity.this, FlowActivityAfterYellow.class)));

        fab.setOnClickListener(v -> finish());
    }
}