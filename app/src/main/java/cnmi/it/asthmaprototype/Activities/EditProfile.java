package cnmi.it.asthmaprototype.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.PatientColumn;
import cnmi.it.asthmaprototype.R;

public class EditProfile extends AppCompatActivity {

    RadioGroup genderGroup;
    EditText age, height, weight, congenital, hn, patientname;
    int userid;
    //Button confirmbtn;
    Button addinhalerbtn;
    int max;
    boolean inhaleradded = false;
    FloatingActionButton savefab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);
        userid = 0;
        SharedPreferences getPrefs = this.getSharedPreferences("AppPreferences", MODE_PRIVATE);
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            inhaleradded = extras.getBoolean("added");
//        }
        if(getPrefs !=null) {

            userid = getPrefs.getInt("id", 0);
        }

        age = findViewById(R.id.ageInput);
        height = findViewById(R.id.heightInput);
        genderGroup = findViewById(R.id.GenderGroup);
        savefab = findViewById(R.id.saveprofilefab);
        weight = findViewById(R.id.weightText);
        congenital = findViewById(R.id.congenitaltext);
        hn = findViewById(R.id.hntext);
        addinhalerbtn = findViewById(R.id.editInhalerbtn);
        patientname = findViewById(R.id.patientnametext);

        addinhalerbtn.setOnClickListener(v -> {
            startActivity(new Intent(EditProfile.this, AddInhalerListActivity.class));
        });

        if(inhaleradded = true){
            savefab.setOnClickListener(v -> {
                int iage = Integer.parseInt(age.getText().toString().trim());
                int iheight = Integer.parseInt(height.getText().toString().trim());
                int selectedGender = genderGroup.getCheckedRadioButtonId();
                String ipatientname = patientname.getText().toString().trim();
                RadioButton selectedRadio = findViewById(selectedGender);
                String igender = selectedRadio.getText().toString();

                if (igender.equals("ชาย")) {
                    max = (int) (319.13 - (4.75 * iheight) + 0.035 * Math.pow(iheight, 2));
                } else {
                    max = (int) (-487.12 + (7 * iheight) - 0.0085 * Math.pow(iheight, 2));
                }

                DatabaseHelper dbHelper = new DatabaseHelper(this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(PatientColumn.PatientEntry.COLUMN_HN, hn.getText().toString().trim());
                values.put(PatientColumn.PatientEntry.COLUMN_NAME, ipatientname);
                values.put(PatientColumn.PatientEntry.COLUMN_AGE, iage);
                values.put(PatientColumn.PatientEntry.COLUMN_HEIGHT, iheight);
                values.put(PatientColumn.PatientEntry.COLUMN_GENDER, igender);
                values.put(PatientColumn.PatientEntry.COLUMN_PEFR, max);
                values.put(PatientColumn.PatientEntry.COLUMN_WEIGHT, weight.getText().toString().trim());
                values.put(PatientColumn.PatientEntry.COLUMN_CONGENITAL, congenital.getText().toString().trim());
                values.put(PatientColumn.PatientEntry.COLUMN_USER, userid);

                db.insert(PatientColumn.PatientEntry.TABLE_NAME, null, values);

                db.close();
                finish();
                Intent toDashboard = new Intent(EditProfile.this, Dashboard.class);
                startActivity(toDashboard);
            });
        }else {
            Toast.makeText(this, "กรุณาเพิ่มยาพ่นก่อนบันทึกข้อมูล", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditProfile.this, AddInhalerListActivity.class));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}