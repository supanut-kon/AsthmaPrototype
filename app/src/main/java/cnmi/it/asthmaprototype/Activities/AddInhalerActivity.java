package cnmi.it.asthmaprototype.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.InhalerColumn;
import cnmi.it.asthmaprototype.R;

public class AddInhalerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner inhalerSpinner, emergencySpinner;
    EditText timesEdittext, indayEdittext, emergencyEdittext;
    FloatingActionButton fab;
    RadioGroup radio;
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
        timesEdittext = findViewById(R.id.timesEdittext);
        indayEdittext = findViewById(R.id.indayEdittext);
        emergencyEdittext = findViewById(R.id.emergencyTimesEdittext);
        radio = findViewById(R.id.radioGroup1);

        inhalerSpinner = findViewById(R.id.inhalerSpinner);
        inhalerSpinner.setOnItemSelectedListener(this);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), resources, resourcesname);
        inhalerSpinner.setAdapter(customAdapter);

        emergencySpinner = findViewById(R.id.inhalerEmergencySpinner);
        emergencySpinner.setOnItemSelectedListener(this);
        emergencySpinner.setAdapter(customAdapter);

//        int rowcount = c.getInt(0);
//        if(rowcount > 0){
//            for(int i=0;i<=resources.length;i++) {
//                String timetext = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
//                SQLiteDatabase writedb = helper.getWritableDatabase();
//                ContentValues cv = new ContentValues();
//                String flixotide = getResources().getResourceName(resources[i]);
//                cv.put(InhalerColumn.InhalerEntry.COLUMN_NAME, resourcesname[i]);
//                cv.put(InhalerColumn.InhalerEntry.COLUMN_IMAGE, convertImage(R.drawable.flixotide_evohaler));
//                cv.put(InhalerColumn.InhalerEntry.COLUMN_UPDATE_DATE, timetext);
//            }
//        }

        fab.setOnClickListener(v->{

            String timetext = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
            DatabaseHelper helper = new DatabaseHelper(this);
            SQLiteDatabase writedb = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            RadioButton selected = findViewById(radio.getCheckedRadioButtonId());
            cv.put(InhalerColumn.InhalerEntry.COLUMN_INHALERID, inhalerSpinner.getSelectedItemPosition());
            cv.put(InhalerColumn.InhalerEntry.COLUMN_PRESC, "ครั้งละ "+ timesEdittext.getText().toString() + " สูด" + " วันละ"+indayEdittext.getText().toString()+" ครั้ง"+" ตอน "+selected.getText().toString());
            cv.put(InhalerColumn.InhalerEntry.COLUMN_EMINHALERID, emergencySpinner.getSelectedItemPosition());
            cv.put(InhalerColumn.InhalerEntry.COLUMN_EMPRESC, "ครั้งละ "+emergencyEdittext.getText().toString()+ " สูด");
            cv.put(InhalerColumn.InhalerEntry.COLUMN_PATIENT, 1);
            cv.put(InhalerColumn.InhalerEntry.COLUMN_UPDATE_DATE, Calendar.DATE+" "+timetext);
            writedb.insert(InhalerColumn.InhalerEntry.TABLE_NAME, null, cv);



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