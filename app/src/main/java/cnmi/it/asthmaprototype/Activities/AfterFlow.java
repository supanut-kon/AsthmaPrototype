package cnmi.it.asthmaprototype.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cnmi.it.asthmaprototype.R;

public class AfterFlow extends AppCompatActivity {

    RadioGroup symptoms;
    RadioButton abnormal, normal;
    TextView symptomsheader;
    CheckBox cough, heavybreathing, chestpain, suddenwake, easilytired;
    FloatingActionButton savefab;
    int selectedchoice;
    Spinner carespinner;

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
        savefab = findViewById(R.id.savestfab);
        abnormal = findViewById(R.id.AbnormalRadioButton);
        normal = findViewById(R.id.NormalRadioButton);
        carespinner = findViewById(R.id.carespinner);


        symptomsheader.setVisibility(View.GONE);
        cough.setVisibility(View.GONE);
        chestpain.setVisibility(View.GONE);
        heavybreathing.setVisibility(View.GONE);
        suddenwake.setVisibility(View.GONE);
        easilytired.setVisibility(View.GONE);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.caremethods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carespinner.setAdapter(adapter);



//        if(selectedchoice == R.id.AbnormalRadioButton){
//
//        }
        abnormal.setOnClickListener(v -> {
            symptomsheader.setVisibility(View.VISIBLE);
            cough.setVisibility(View.VISIBLE);
            chestpain.setVisibility(View.VISIBLE);
            heavybreathing.setVisibility(View.VISIBLE);
            suddenwake.setVisibility(View.VISIBLE);
            easilytired.setVisibility(View.VISIBLE);

            savefab.setOnClickListener(vi -> {
                finish();
                Intent toYellow = new Intent(AfterFlow.this, YellowWarning.class);
                startActivity(toYellow);
            });
        });

        normal.setOnClickListener(v -> savefab.setOnClickListener(vi -> {
            finish();
            Intent toGreen = new Intent(AfterFlow.this, GreenWarning.class);
            startActivity(toGreen);
        }));


//            selectedchoice = symptoms.getCheckedRadioButtonId();


    }
}