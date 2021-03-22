package cnmi.it.asthmaprototype.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.FlowColumn;
import cnmi.it.asthmaprototype.R;

public class AfterFlow extends AppCompatActivity {

    RadioGroup symptoms;
    RadioButton abnormal, normal;
    TextView symptomsheader;
    CheckBox cough, heavybreathing, chestpain, suddenwake, easilytired;
    FloatingActionButton savefab;
    Spinner carespinner;
    int pfvalue, id, yellowvalue, redvalue;
    double max;
    String date, selectedspinner;
    double peakflow;

    final Calendar calendar = Calendar.getInstance();

    ArrayList<String> symptomsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_flow);

        Bundle extras = getIntent().getExtras();
        pfvalue = extras.getInt("pfvalue");
        id = extras.getInt("id");
        yellowvalue = extras.getInt("yellow");
        redvalue = extras.getInt("red");
        max = extras.getDouble("max");
        date = extras.getString("datetext");

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

        symptomsArray = new ArrayList<>();

        symptomsheader.setVisibility(View.GONE);
        cough.setVisibility(View.GONE);
        chestpain.setVisibility(View.GONE);
        heavybreathing.setVisibility(View.GONE);
        suddenwake.setVisibility(View.GONE);
        easilytired.setVisibility(View.GONE);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.caremethods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carespinner.setAdapter(adapter);
        carespinner.setOnItemClickListener((parent, view, position, id) -> selectedspinner = parent.getItemAtPosition(position).toString());



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

            if(cough.isChecked()){
                symptomsArray.add(cough.getText().toString());
            }
            if(heavybreathing.isChecked()){
                symptomsArray.add(heavybreathing.getText().toString());
            }
            if(chestpain.isChecked()){
                symptomsArray.add(chestpain.getText().toString());
            }
            if(suddenwake.isChecked()){
                symptomsArray.add(suddenwake.getText().toString());
            }
            if(easilytired.isChecked()){
                symptomsArray.add(easilytired.getText().toString());
            }


            savefab.setOnClickListener(vi -> {
                String timetext = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
                DatabaseHelper dbHelper2 = new DatabaseHelper(this);
                SQLiteDatabase db2 = dbHelper2.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(FlowColumn.FlowEntry.COLUMN_FLOW, pfvalue);
                values.put(FlowColumn.FlowEntry.COLUMN_ZONE, "yellow");
                values.put(FlowColumn.FlowEntry.COLUMN_MAX, peakflow);
                values.put(FlowColumn.FlowEntry.COLUMN_80, yellowvalue);
                values.put(FlowColumn.FlowEntry.COLUMN_60, redvalue);
                values.put(FlowColumn.FlowEntry.COLUMN_USER_ID, 1);
                values.put(FlowColumn.FlowEntry.COLUMN_DATE, date);
                values.put(FlowColumn.FlowEntry.COLUMN_TIME, timetext);
                values.put(FlowColumn.FlowEntry.COLUMN_HAVE_SYMPTOM, abnormal.getText().toString());
                values.put(FlowColumn.FlowEntry.COLUMN_SYMPTOMS, String.valueOf(symptomsArray));
                values.put(FlowColumn.FlowEntry.COLUMN_CAREMETHOD, selectedspinner);

                db2.insert(FlowColumn.FlowEntry.TABLE_NAME, null, values);
                db2.close();
                finish();
                Intent toYellow = new Intent(AfterFlow.this, YellowWarning.class);
                startActivity(toYellow);
            });
        });

        normal.setOnClickListener(v -> savefab.setOnClickListener(vi -> {

            String timetext = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
            DatabaseHelper dbHelper2 = new DatabaseHelper(this);
            SQLiteDatabase db2 = dbHelper2.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(FlowColumn.FlowEntry.COLUMN_FLOW, pfvalue);
            values.put(FlowColumn.FlowEntry.COLUMN_ZONE, "green");
            values.put(FlowColumn.FlowEntry.COLUMN_MAX, peakflow);
            values.put(FlowColumn.FlowEntry.COLUMN_80, yellowvalue);
            values.put(FlowColumn.FlowEntry.COLUMN_60, redvalue);
            values.put(FlowColumn.FlowEntry.COLUMN_USER_ID, 1);
            values.put(FlowColumn.FlowEntry.COLUMN_DATE, date);
            values.put(FlowColumn.FlowEntry.COLUMN_TIME, timetext);
            values.put(FlowColumn.FlowEntry.COLUMN_HAVE_SYMPTOM, normal.getText().toString());
            values.put(FlowColumn.FlowEntry.COLUMN_CAREMETHOD, selectedspinner);

            db2.insert(FlowColumn.FlowEntry.TABLE_NAME, null, values);
            db2.close();
            finish();
            Intent toGreen = new Intent(AfterFlow.this, GreenWarning.class);
            startActivity(toGreen);
        }));


//            selectedchoice = symptoms.getCheckedRadioButtonId();


    }
}