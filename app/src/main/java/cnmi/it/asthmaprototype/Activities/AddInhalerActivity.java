package cnmi.it.asthmaprototype.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.InhalerModel;
import cnmi.it.asthmaprototype.R;

public class AddInhalerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<InhalerModel> addedInhaler;
    Spinner inhalerSpinner, emergencySpinner;
    EditText timesEdittext, indayEdittext, emergencyEdittext;
    FloatingActionButton fab, addfab;
    CheckBox morning, evening, isemergency;
    CardView card;
    ImageView img;
    ViewGroup.LayoutParams layout, imageLayout;
    TextView timesText3, timesText4;

    final Calendar calendar = Calendar.getInstance();

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
        addfab = findViewById(R.id.addFab);
        timesEdittext = findViewById(R.id.timesEdittext);
        indayEdittext = findViewById(R.id.indayEdittext);
        timesText3 = findViewById(R.id.timesText3);
        timesText4 = findViewById(R.id.timesText4);
        morning = findViewById(R.id.morningCheckBox);
        evening = findViewById(R.id.eveningCheckBox);
        isemergency = findViewById(R.id.isEmergencyCB);

        if(isemergency.isChecked()){
            timesText3.setVisibility(View.INVISIBLE);
            indayEdittext.setVisibility(View.INVISIBLE);
            timesText4.setVisibility(View.INVISIBLE);
            morning.setVisibility(View.INVISIBLE);
            evening.setVisibility(View.INVISIBLE);
        }

        addedInhaler = new ArrayList<>();
        inhalerSpinner = findViewById(R.id.inhalerSpinner);
        inhalerSpinner.setOnItemSelectedListener(this);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), resources, resourcesname);
        inhalerSpinner.setAdapter(customAdapter);

        emergencySpinner.setOnItemSelectedListener(this);
        emergencySpinner.setAdapter(customAdapter);

        addfab.setOnClickListener(v->{

        });

        fab.setOnClickListener(v->{
            DatabaseHelper helper = new DatabaseHelper(this);
            SQLiteDatabase writedb = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
//            cv.put(InhalerColumn.InhalerEntry.COLUMN_INHALERID, inhalerSpinner.getSelectedItemPosition());
//            cv.put(InhalerColumn.InhalerEntry.COLUMN_PRESC, "ครั้งละ "+ timesEdittext.getText().toString() + " สูด" + " วันละ"+indayEdittext.getText().toString()+" ครั้ง"+" ตอน "+selected.getText().toString());
//            cv.put(InhalerColumn.InhalerEntry.COLUMN_EMINHALERID, emergencySpinner.getSelectedItemPosition());
//            cv.put(InhalerColumn.InhalerEntry.COLUMN_EMPRESC, "ครั้งละ "+emergencyEdittext.getText().toString()+ " สูด");
//            cv.put(InhalerColumn.InhalerEntry.COLUMN_PATIENT, 1);
//            cv.put(InhalerColumn.InhalerEntry.COLUMN_UPDATE_DATE, Calendar.DATE+" "+timetext);
//            cv.put(InhalerColumn.InhalerEntry.COLUMN_DID, inhalerSpinner.getSelectedItemPosition());
//            cv.put(InhalerColumn.InhalerEntry.COLUMN_NAME, inhalerSpinner.getSelectedItem().toString());
//            cv.put(InhalerColumn.InhalerEntry.COLUMN_TIMES, timesEdittext.getText().toString().trim());
//            cv.put(InhalerColumn.InhalerEntry.COLUMN_INADAY, indayEdittext.getText().toString().trim());
//            writedb.insert(InhalerColumn.InhalerEntry.TABLE_NAME, null, cv);
            
//            ContentValues emergency = new ContentValues();
//            emergency.put(InhalerColumn.InhalerEntry.COLUMN_DID, emergencySpinner.getSelectedItemPosition());
//            emergency.put(InhalerColumn.InhalerEntry.COLUMN_NAME, emergencySpinner.getSelectedItem().toString());
//            emergency.put(InhalerColumn.InhalerEntry.COLUMN_TIMES, emergencyEdittext.getText().toString().trim());
//            emergency.put(InhalerColumn.InhalerEntry.COLUMN_INADAY, "0");
//            writedb.insert(InhalerColumn.InhalerEntry.TABLE_NAME,null,emergency);

            writedb.close();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(this, resourcesname[position], Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}