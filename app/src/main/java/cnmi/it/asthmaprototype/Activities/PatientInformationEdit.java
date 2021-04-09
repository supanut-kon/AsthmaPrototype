package cnmi.it.asthmaprototype.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.PatientColumn;
import cnmi.it.asthmaprototype.R;

public class PatientInformationEdit extends AppCompatActivity {

    RadioGroup genderGroup;
    EditText age, height, weight, congenital, hn, patientname;
    //Button confirmbtn;
    FloatingActionButton savefab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        age = findViewById(R.id.ageInput);
        height = findViewById(R.id.heightInput);
        genderGroup = findViewById(R.id.GenderGroup);
        savefab = findViewById(R.id.saveprofilefab);
        weight = findViewById(R.id.weightText);
        congenital = findViewById(R.id.congenitaltext);
        hn = findViewById(R.id.hntext);
        patientname = findViewById(R.id.patientnametext);

        savefab.setOnClickListener(v -> {
            int iage = Integer.parseInt(age.getText().toString().trim());
            int iheight = Integer.parseInt(height.getText().toString().trim());
            int selectedGender = genderGroup.getCheckedRadioButtonId();
            RadioButton selectedRadio = findViewById(selectedGender);
            String igender = selectedRadio.getText().toString();

            DatabaseHelper dbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(PatientColumn.PatientEntry.COLUMN_HN, hn.getText().toString().trim());
            values.put(PatientColumn.PatientEntry.COLUMN_AGE, iage);
            values.put(PatientColumn.PatientEntry.COLUMN_HEIGHT, iheight);
            values.put(PatientColumn.PatientEntry.COLUMN_GENDER, igender);
            values.put(PatientColumn.PatientEntry.COLUMN_WEIGHT, weight.getText().toString().trim());
            values.put(PatientColumn.PatientEntry.COLUMN_CONGENITAL, congenital.getText().toString().trim());

            db.insert(PatientColumn.PatientEntry.TABLE_NAME, null, values);

            db.close();
            finish();
            Intent toDashboard = new Intent(PatientInformationEdit.this, Dashboard.class);
            startActivity(toDashboard);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();

    }
}