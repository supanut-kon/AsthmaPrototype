package cnmi.it.asthmaprototype.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cnmi.it.asthmaprototype.R;

public class GreenFinalActivity extends AppCompatActivity {

    FloatingActionButton fab, yellowTimer;
    TextView yellowtext, yellowinhaler, timer, againText, timerText, yellowHeader, yellowDesc;
    Button hiddenBtn;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yellow_warning);
        layout = findViewById(R.id.main_layout);
        layout.setBackgroundColor(getColor(R.color.WarningGreen));
        timer = findViewById(R.id.timer);
        yellowtext = findViewById(R.id.RedTextview);
        yellowinhaler = findViewById(R.id.RedinhalerText);
        yellowTimer = findViewById(R.id.yellowCalFab);
        hiddenBtn = findViewById(R.id.hiddenBtn);
        againText = findViewById(R.id.againText);
        timerText = findViewById(R.id.TimerText);
        yellowHeader = findViewById(R.id.YellowHeader1);
        yellowDesc = findViewById(R.id.YellowDesc);
        fab = findViewById(R.id.floatingActionButton);

        hiddenBtn.setVisibility(View.INVISIBLE);
        againText.setVisibility(View.INVISIBLE);
        yellowTimer.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.INVISIBLE);
        timerText.setVisibility(View.INVISIBLE);
        yellowHeader.setText("อาการดีขึ้น");
        yellowDesc.setVisibility(View.INVISIBLE);
        yellowtext.setText("สูตรยาจะปรับเพิ่มขึ้นเป็นสองเท่าเป็นเวลาเจ็ดวัน\nและไปพบแพทย์ภายในเวลา 7 วัน");
        yellowinhaler.setText("ครั้งละ 4 สูด วันละ 2 ครั้ง \nเช้าและก่อนนอน\nบ้วนปาก กลั้วคอ หลังสูดยา");

        fab.setOnClickListener(v -> finish());

    }
}