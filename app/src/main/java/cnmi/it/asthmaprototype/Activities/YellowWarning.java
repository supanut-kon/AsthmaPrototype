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
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import cnmi.it.asthmaprototype.Database.DatabaseAccess;
import cnmi.it.asthmaprototype.Models.InhalerModel;
import cnmi.it.asthmaprototype.R;

public class YellowWarning extends AppCompatActivity {

    FloatingActionButton fab, yellowTimer;
    TextView yellowtext, yellowinhaler, timer, againText, timerText;
    ImageView yellowInhaler;
    Button hiddenBtn;
    ArrayList<InhalerModel> inhalers;
    int count = 0;
    int[] resources = {R.drawable.flixotide_evohaler,
            R.drawable._0_aeronide,
            R.drawable.easyhaler_budesoniude,
            R.drawable.easyhaler_salbutamolsq,
            R.drawable.seretideevo,
            R.drawable.seretideaccuhaler,
            R.drawable.ventolin_inhaler};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yellow_warning);

//        Bundle extras = getIntent().getExtras();
//        extras.getInt("count");
        DatabaseAccess db = DatabaseAccess.getInstance(this);
        db.open();
        inhalers = db.getInhaler(2);
        db.close();
        timer = findViewById(R.id.timer);
        yellowtext = findViewById(R.id.RedTextview);
        yellowInhaler = findViewById(R.id.RedInhalerImage);
        yellowtext.setText("- ใช้ยาขยายหลอดลมฉุกเฉิน ครั้งละ 3 สูด \nอาจสูดซ้ำได้ทุก 15 นาที ติดต่อกันไม่เกิน 3 ครั้ง\n\n- เป่าเครื่องวัดแรงลมสูงสุดซ้ำ หากอาการไม่ดีขึ้น\nหรือค่าแรงลมสูงสุดที่เป่าได้ยังอยู่ระดับเดิม \nให้รีบไปโรงพยาบาลที่ใกล้ที่สุด \n\n- หากอาการดีขึ้น ให้ใช้ยาขยายหลอดลมฉุกเฉิน 2-4 สูด ทุก 3-4 ชั่วโมงต่ออีก 1-2 วัน และปรับเพิ่มยาที่ใช้ประจำทุกวันเป็น 2 เท่า");
        yellowInhaler.setImageResource(resources[inhalers.get(0).getDid()]);
        yellowinhaler = findViewById(R.id.RedinhalerText);
        yellowinhaler.setText(String.format("%s\n\nครั้งละ %s สูด", inhalers.get(0).getName(), inhalers.get(0).getTimes()));
        fab = findViewById(R.id.floatingActionButton);
        yellowTimer = findViewById(R.id.yellowCalFab);
        hiddenBtn = findViewById(R.id.hiddenBtn);
        hiddenBtn.setVisibility(View.INVISIBLE);
        againText = findViewById(R.id.againText);
        againText.setVisibility(View.INVISIBLE);
        timerText = findViewById(R.id.TimerText);




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
        Intent toFlow = new Intent(YellowWarning.this, FlowActivityAfterYellow.class);
        toFlow.putExtra("count", count+1);
        hiddenBtn.setOnClickListener(v -> {
            finish();
            startActivity(toFlow);
        });

        fab.setOnClickListener(v -> finish());

    }
}