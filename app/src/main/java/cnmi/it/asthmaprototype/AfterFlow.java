package cnmi.it.asthmaprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AfterFlow extends AppCompatActivity {

    RadioGroup symptoms;
    TextView symptomsheader;
    CheckBox cough, heavybreathing, chestpain, suddenwake, easilytired;
    FloatingActionButton savefab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_flow);

        symptoms = findViewById(R.id.symptomchoice);
        symptomsheader = findViewById(R.id.symptomsheader);
        cough = findViewById(R.id.cough);
        heavybreathing = findViewById(R.id.heavybreathing);
        chestpain = findViewById(R.id.chestpain);
        suddenwake = findViewById(R.id.suddenwake);
        easilytired = findViewById(R.id.easilytired);

        symptomsheader.setVisibility(View.GONE);
        cough.setVisibility(View.GONE);
        chestpain.setVisibility(View.GONE);
        heavybreathing.setVisibility(View.GONE);
        suddenwake.setVisibility(View.GONE);
        easilytired.setVisibility(View.GONE);

        int selectedchoice = symptoms.getCheckedRadioButtonId();
        //RadioButton choice = findViewById(selectedchoice);
        if(selectedchoice == R.id.AbnormalRadioButton){
            symptomsheader.setVisibility(View.VISIBLE);
            cough.setVisibility(View.VISIBLE);
            chestpain.setVisibility(View.VISIBLE);
            heavybreathing.setVisibility(View.VISIBLE);
            suddenwake.setVisibility(View.VISIBLE);
            easilytired.setVisibility(View.VISIBLE);
        }

        savefab.setOnClickListener(v ->{
            if(selectedchoice == R.id.AbnormalRadioButton){
                Intent toYellow = new Intent(AfterFlow.this, YellowWarning.class);
                startActivity(toYellow);
            }else startActivity(new Intent(AfterFlow.this, GreenWarning.class));

        });



    }
}