package cnmi.it.asthmaprototype.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cnmi.it.asthmaprototype.R;

public class GreenWarning extends AppCompatActivity {

    FloatingActionButton fab, help;
    TextView greentext, greendesc, greeninhalertext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_warning);

        greentext = findViewById(R.id.RedTextview);
        greendesc = findViewById(R.id.GreenDesc);
        greeninhalertext = findViewById(R.id.greeninhalerText);

        greentext.setText("- พ่นยาที่ใช้เป็นประจำ แม้ไม่มีอาการ\n\n- ก่อนออกกำลังกาย 15 นาที อาจใช้ยาขยายหลอดลมฉุกเฉิน ครั้งละ 2 สูด");

        greeninhalertext.setText("ครั้งละ 2 สูด วันละ 2 ครั้ง \nเช้าและก่อนนอน\nบ้วนปาก กลั้วคอ หลังสูดยา");

        //greendesc.setText("ทำกิจวัตรประจำวันได้ดี ไม่มีอาการไอและเหนื่อยหอบ");

        fab = findViewById(R.id.floatingActionButton);
        help = findViewById(R.id.inhalerHelp);


        fab.setOnClickListener(v -> finish());
    }
}