package cnmi.it.asthmaprototype;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class PatientConfig extends AppCompatActivity {

    RadioGroup genderGroup;
    EditText age;
    EditText height;
    Button confirmbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_config);

        age = findViewById(R.id.ageInput);
        height = findViewById(R.id.heightInput);
        genderGroup = findViewById(R.id.GenderGroup);
        confirmbtn = findViewById(R.id.inputConfirmbtn);

        confirmbtn.setOnClickListener(v ->{
            int iage = Integer.parseInt(age.getText().toString().trim());
            int iheight = Integer.parseInt(height.getText().toString().trim());
            int selectedGender = genderGroup.getCheckedRadioButtonId();
            RadioButton selectedRadio = findViewById(selectedGender);
            String igender = selectedRadio.getText().toString();
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(UserColumn.UserEntry.COLUMN_AGE, iage);
            values.put(UserColumn.UserEntry.COLUMN_HEIGHT, iheight);
            values.put(UserColumn.UserEntry.COLUMN_GENDER, igender);

            db.insert(UserColumn.UserEntry.TABLE_NAME, null, values);

            db.close();
            finish();
            Intent toDashboard = new Intent(PatientConfig.this, Dashboard.class);
            startActivity(toDashboard);
        });

    }


}