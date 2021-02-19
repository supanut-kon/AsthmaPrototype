package cnmi.it.asthmaprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

        int selectedGender = genderGroup.getCheckedRadioButtonId();


        confirmbtn.setOnClickListener(v ->{
            RadioButton selectedRadio = findViewById(selectedGender);


            finish();
            Intent toDashboard = new Intent(PatientConfig.this, Dashboard.class);
            startActivity(toDashboard);
        });


    }

}