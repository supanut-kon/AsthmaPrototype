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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import cnmi.it.asthmaprototype.Adapters.InhalerCardAdapter;
import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.InhalerColumn;
import cnmi.it.asthmaprototype.Models.InhalerModel;
import cnmi.it.asthmaprototype.R;

public class AddInhalerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    final Calendar calendar = Calendar.getInstance();
    ArrayList<InhalerModel> addedArraylist;
    Spinner inhalerSpinner, emergencySpinner, doseSpinner;
    EditText timesEdittext, indayEdittext, emergencyEdittext;
    FloatingActionButton fab, addfab;
    CheckBox morning, evening, isemergency;
    InhalerCardAdapter card;
    ImageView img;
    int inhalertype;
    TextView timesText3, timesText4, dosehead;
    InhalerModel addedinhaler;
    int ischecked, selectposition;
    String selecteditem;
    RadioGroup inhalergroup;
    RadioButton normal, abnormal, emergency;
    int[] resources = {R.drawable.flixotide_evohaler,
            R.drawable._0_aeronide,
            R.drawable.easyhaler_budesoniude,
            R.drawable.easyhaler_salbutamolsq,
            R.drawable.seretideevo,
            R.drawable.seretideaccuhaler,
            R.drawable.ventolin_inhaler};

    String[] resourcesname = {"Flixotide Evohaler", "Aeronide", "Easyhaler Budesonide", "Easyhaler Salbutamol", "Seretide Evohaler", "Seretide Accuhaler", "Ventolin"};

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

            } else if (checkedId == R.id.normalinhaler){
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

            }else {
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


        fab.setOnClickListener(v->{
            int ismorning;
            int isevening;
            String times = timesEdittext.getText().toString();
            String inaday = indayEdittext.getText().toString();
            if(morning.isChecked()){
                ismorning = 1;
            }else ismorning = 0;
            if(evening.isChecked()){
                isevening = 1;
            }else isevening = 0;

            DatabaseHelper helper = new DatabaseHelper(this);
            SQLiteDatabase writedb = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(InhalerColumn.InhalerEntry.COLUMN_DID, selectposition);
            cv.put(InhalerColumn.InhalerEntry.COLUMN_NAME, selecteditem);
            cv.put(InhalerColumn.InhalerEntry.COLUMN_TYPE, inhalertype);
            cv.put(InhalerColumn.InhalerEntry.COLUMN_TIMES, times);
            cv.put(InhalerColumn.InhalerEntry.COLUMN_INADAY, inaday);
            cv.put(InhalerColumn.InhalerEntry.COLUMN_ISACTIVE, 1);
//            ismorning, isevening
            writedb.insert(InhalerColumn.InhalerEntry.TABLE_NAME, null, cv);
            writedb.close();
            Intent addinhaler = new Intent(AddInhalerActivity.this, AddInhalerListActivity.class);

            addinhaler.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(addinhaler, 0);
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(this, resourcesname[position], Toast.LENGTH_LONG).show();

        selectposition = position;
        selecteditem = resourcesname[position];
        img.setImageResource(resources[position]);

//        if(selecteditem == "Seretide Evohaler"){
//
//        }

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