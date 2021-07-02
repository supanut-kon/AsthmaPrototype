package cnmi.it.asthmaprototype.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import cnmi.it.asthmaprototype.R;

public class YellowFinalWarningActivity extends AppCompatActivity {

    FloatingActionButton fab, yellowTimer;
    TextView yellowtext, yellowinhaler, timer, againText, timerText, yellowHeader, yellowDesc, inhalertext, inhalerlabel;
    Button hiddenBtn;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yellow_warning);

//        Bundle extras = getIntent().getExtras();
//        if(extras!=null) {
//            count = extras.getInt("count");
//        } else count =1;
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
        image = findViewById(R.id.RedInhalerImage);
//        inhalerlabel = findViewById(R.id.redInhalerlabel);

        yellowinhaler.setVisibility(View.INVISIBLE);
        image.setVisibility(View.INVISIBLE);
        inhalerlabel.setVisibility(View.INVISIBLE);
        yellowDesc.setVisibility(View.INVISIBLE);
        hiddenBtn.setVisibility(View.INVISIBLE);
        againText.setVisibility(View.INVISIBLE);
        yellowTimer.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.INVISIBLE);
        timerText.setVisibility(View.INVISIBLE);

        yellowHeader.setText("อาการยังไม่ดีขึ้น");
        yellowtext.setTextSize(20);
        yellowtext.setText("- ผู้ปกครองควรนำคนไข้ไปโรงพยาบาลที่ใกล้ที่สุดโดยด่วน");

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
