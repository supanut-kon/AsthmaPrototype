package cnmi.it.asthmaprototype.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import cnmi.it.asthmaprototype.Database.DatabaseAccess;
import cnmi.it.asthmaprototype.Models.InhalerModel;
import cnmi.it.asthmaprototype.R;

public class RedWarning extends AppCompatActivity {

    FloatingActionButton fab, redCall;
    TextView redText, redInhaler;
    ArrayList<InhalerModel> inhalers;
    ImageView redInhalerImage;
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
        setContentView(R.layout.activity_red_warning);

        inhalers = new ArrayList<>();
        DatabaseAccess db = DatabaseAccess.getInstance(this);
        db.open();
        inhalers = db.getInhaler(3);
        db.open();
        redInhalerImage = findViewById(R.id.RedInhalerImage);
        redInhalerImage.setImageResource(resources[inhalers.get(0).getDid()]);
        redText = findViewById(R.id.RedTextview);
        redText.setText("- ใช้ยาขยายหลอดลมฉุกเฉิน 6-10 สูด \nทุก 15 นาทีขณะเดินทางไปโรงพยาบาล\n\n- กินยาสเตียร์รอยด์ 1 เม็ด \nแล้วรีบไปโรงพยาบาลทันที");
        redInhaler = findViewById(R.id.RedinhalerText);
        redInhaler.setText(String.format("%s\n\n6-10 สูด ทุก 15 นาทีขณะเดินทางไปโรงพยาบาล", inhalers.get(0).getName()));

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