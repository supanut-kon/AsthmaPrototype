package cnmi.it.asthmaprototype.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cnmi.it.asthmaprototype.R;

public class RedWarning extends AppCompatActivity {

    FloatingActionButton fab, redCall;
    TextView redText, redInhaler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_warning);

        redText = findViewById(R.id.RedTextview);
        redText.setText("- ใช้ยาขยายหลอดลมฉุกเฉิน 6-10 สูด \nทุก 15 นาทีขณะเดินทางไปโรงพยาบาล\n\n- กินยาสเตียร์รอยด์ 1 เม็ด \nแล้วรีบไปโรงพยาบาลทันที");
        redInhaler = findViewById(R.id.RedinhalerText);
        redInhaler.setText("6-10 สูด ทุก 15 นาทีขณะเดินทางไปโรงพยาบาล");

        fab = findViewById(R.id.floatingActionButton);
        redCall = findViewById(R.id.RedCallFab);
        redCall.setOnClickListener(v -> call());


        fab.setOnClickListener(v -> finish());
    }

    private void call(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:1669"));
        startActivity(callIntent);
    }
}