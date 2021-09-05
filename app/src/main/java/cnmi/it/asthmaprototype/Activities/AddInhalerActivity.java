package cnmi.it.asthmaprototype.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.InhalerColumn;
import cnmi.it.asthmaprototype.Models.InhalerModel;
import cnmi.it.asthmaprototype.R;

public class AddInhalerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<InhalerModel> addedArraylist;
    Spinner inhalerSpinner, emergencySpinner, doseSpinner;
    EditText timesEdittext, indayEdittext, emergencyEdittext, dosequantity;
    FloatingActionButton fab, addfab;
    CheckBox morning, evening, isemergency;
    ImageView img;
    int inhalertype;
    TextView timesText3, timesText4, dosehead;
    InhalerModel addedinhaler;
    int ischecked, selectposition;
    String selecteditem, selecteddose;
    RadioGroup inhalergroup;
    int[] resources = {R.drawable.flixotide_evohaler,
            R.drawable.easyhaler_budesoniude,
            R.drawable.easyhaler_salbutamolsq,
            R.drawable.seretideevo,
            R.drawable.seretideaccuhaler,
            R.drawable.ventolin_inhaler};

    String[] resourcesname = {"Flixotide Evohaler", "Easyhaler Budesonide", "Easyhaler Salbutamol", "Seretide Evohaler", "Seretide Accuhaler", "Ventolin"};

    String[] symbicortdose = {"80/4.5 mcg", "160/4.5 mcg", "320/4.5 mcg"};
    String[] seretideevodose = {"25/50 mcg", "25/150 mcg", "25/150 mcg"};
    String[] seretideaccdose = {"50/100 mcg", "50/500 mcg", "50/500 mcg"};
    String[] nebulizationflix = {"0.5 mg/2 mL", "2 mg/ 2 mL"};
    String[] nebulizationpul = {"0.5 mg/2 mL", "2 mg/ 2 mL"};
    String[] ventolin = {"2.5 mg/2.5 mL", "5 mg/1 mL"};


    //TODO update inhalers and dosage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inhaler);
        fab = findViewById(R.id.floatingActionButton2);

        timesEdittext = findViewById(R.id.timesEdittext);
        indayEdittext = findViewById(R.id.indayEdittext);
        timesText3 = findViewById(R.id.timesText3);
        timesText4 = findViewById(R.id.timesText4);
        morning = findViewById(R.id.morningCheckBox);
        evening = findViewById(R.id.eveningCheckBox);
        inhalergroup = findViewById(R.id.inhalergroup);
        dosehead = findViewById(R.id.dosehead);
        doseSpinner = findViewById(R.id.dosespinner);
        dosequantity = findViewById(R.id.dosequantityEdittext);
        dosequantity.setVisibility(View.GONE);

//        isemergency = findViewById(R.id.isEmergencyCB);
        img = findViewById(R.id.inhalerpreview);


//        if(isemergency.isChecked()){
//            timesText3.setVisibility(View.INVISIBLE);
//            indayEdittext.setVisibility(View.INVISIBLE);
//            timesText4.setVisibility(View.INVISIBLE);
//            morning.setVisibility(View.INVISIBLE);
//            evening.setVisibility(View.INVISIBLE);
//        } else {
//            timesText3.setVisibility(View.VISIBLE);
//            indayEdittext.setVisibility(View.VISIBLE);
//            timesText4.setVisibility(View.VISIBLE);
//            morning.setVisibility(View.VISIBLE);
//            evening.setVisibility(View.VISIBLE);
//        }
//        isemergency.setOnCheckedChangeListener(new checkbox());
        inhalergroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.emergencyinhaler) {
                timesText3.setVisibility(View.GONE);
                indayEdittext.setVisibility(View.GONE);
                timesText4.setVisibility(View.GONE);
                morning.setVisibility(View.GONE);
                evening.setVisibility(View.GONE);
                inhalertype = 3;
                Log.d("inhalertype", "33");

            } else if (checkedId == R.id.acuteinhaler) {
                timesText3.setVisibility(View.GONE);
                indayEdittext.setVisibility(View.GONE);
                timesText4.setVisibility(View.GONE);
                morning.setVisibility(View.GONE);
                evening.setVisibility(View.GONE);
                inhalertype = 2;
                Log.d("inhalertype", "22");

            } else if (checkedId == R.id.normalinhaler) {
                inhalertype = 1;
                Log.d("inhalertype", "11");
                if (timesText3.getVisibility() == View.GONE) {
                    timesText3.setVisibility(View.VISIBLE);
                }
                if (indayEdittext.getVisibility() == View.GONE) {
                    indayEdittext.setVisibility(View.VISIBLE);
                }
                if (timesText4.getVisibility() == View.GONE) {
                    timesText4.setVisibility(View.VISIBLE);
                }
                if (morning.getVisibility() == View.GONE) {
                    morning.setVisibility(View.VISIBLE);
                }
                if (evening.getVisibility() == View.GONE) {
                    evening.setVisibility(View.VISIBLE);
                }

            } else {
                inhalertype = 4;
                Log.d("inhalertype", "44");
                if (timesText3.getVisibility() == View.GONE) {
                    timesText3.setVisibility(View.VISIBLE);
                }
                if (indayEdittext.getVisibility() == View.GONE) {
                    indayEdittext.setVisibility(View.VISIBLE);
                }
                if (timesText4.getVisibility() == View.GONE) {
                    timesText4.setVisibility(View.VISIBLE);
                }
                if (morning.getVisibility() == View.GONE) {
                    morning.setVisibility(View.VISIBLE);
                }
                if (evening.getVisibility() == View.GONE) {
                    evening.setVisibility(View.VISIBLE);
                }
            }
        });

        addedArraylist = new ArrayList<>();

        inhalerSpinner = findViewById(R.id.inhalerSpinner);
        inhalerSpinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, resourcesname);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inhalerSpinner.setAdapter(arrayAdapter);


        fab.setOnClickListener(v -> {
            int ismorning;
            int isevening;
            String times = timesEdittext.getText().toString();
            String inaday = indayEdittext.getText().toString();
            if (morning.isChecked()) {
                ismorning = 1;
            } else ismorning = 0;
            if (evening.isChecked()) {
                isevening = 1;
            } else isevening = 0;

            DatabaseHelper helper = new DatabaseHelper(this);
            SQLiteDatabase writedb = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(InhalerColumn.InhalerEntry.COLUMN_DID, selectposition);
            cv.put(InhalerColumn.InhalerEntry.COLUMN_NAME, selecteditem);
            cv.put(InhalerColumn.InhalerEntry.COLUMN_DOSAGE, selecteddose);
            cv.put(InhalerColumn.InhalerEntry.COLUMN_TYPE, inhalertype);
            cv.put(InhalerColumn.InhalerEntry.COLUMN_TIMES, times);
            cv.put(InhalerColumn.InhalerEntry.COLUMN_INADAY, inaday);
            cv.put(InhalerColumn.InhalerEntry.COLUMN_ISACTIVE, 1);
//            ismorning, isevening
            cv.put(InhalerColumn.InhalerEntry.COLUMN_MORNING, ismorning);
            cv.put(InhalerColumn.InhalerEntry.COLUMN_EVENING, isevening);
            writedb.insert(InhalerColumn.InhalerEntry.TABLE_NAME, null, cv);
            writedb.close();
            Intent addinhaler = new Intent(AddInhalerActivity.this, AddInhalerListActivity.class);

            addinhaler.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(addinhaler, 0);
        });

        doseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selecteddose = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), selecteddose, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(this, resourcesname[position], Toast.LENGTH_LONG).show();

        selectposition = position;
        selecteditem = resourcesname[position];
        img.setImageResource(resources[position]);

        if (selecteditem.equals(resourcesname[0])) {
            ArrayAdapter ar = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, symbicortdose);
            ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            doseSpinner.setAdapter(ar);
            if(dosequantity.getVisibility() == View.VISIBLE){
                dosequantity.setVisibility(View.GONE);
            }
        } else if (selecteditem.equals(resourcesname[1])) {
            ArrayAdapter ar = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, seretideevodose);
            ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            doseSpinner.setAdapter(ar);
            if(dosequantity.getVisibility() == View.VISIBLE){
                dosequantity.setVisibility(View.GONE);
            }
        } else if (selecteditem.equals(resourcesname[2])) {
            ArrayAdapter ar = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, seretideaccdose);
            ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            doseSpinner.setAdapter(ar);
            if(dosequantity.getVisibility() == View.VISIBLE){
                dosequantity.setVisibility(View.GONE);
            }
        } else if (selecteditem.equals(resourcesname[3])) {
            ArrayAdapter ar = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, nebulizationflix);
            ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            doseSpinner.setAdapter(ar);
            dosequantity.setVisibility(View.VISIBLE);
        } else if (selecteditem.equals(resourcesname[4])) {
            ArrayAdapter ar = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, nebulizationpul);
            ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            doseSpinner.setAdapter(ar);
            dosequantity.setVisibility(View.VISIBLE);
        } else if (selecteditem.equals(resourcesname[5])) {
            ArrayAdapter ar = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ventolin);
            ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            doseSpinner.setAdapter(ar);
            dosequantity.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    //
//    public ArrayList<InhalerModel> getAddedArraylist(){
//
//
//    }
}