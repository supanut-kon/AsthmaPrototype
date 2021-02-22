package cnmi.it.asthmaprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
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




        confirmbtn.setOnClickListener(v ->{
            saveData();
            finish();
            Intent toDashboard = new Intent(PatientConfig.this, Dashboard.class);
            startActivity(toDashboard);
        });

    }

    private void saveData(){
        int selectedGender = genderGroup.getCheckedRadioButtonId();
        RadioButton selectedRadio = findViewById(selectedGender);

        final int iAge = Integer.parseInt(age.getText().toString().trim());
        final int iHeight = Integer.parseInt(height.getText().toString().trim());
        final String gender = selectedRadio.getText().toString();
        CurrentUser currentUser = new CurrentUser();
        currentUser.setAge(iAge);
        currentUser.setHeight(iHeight);
        currentUser.setGender(gender);

        AsthmaDatabase.getDatabase(getApplicationContext()).userDAO().insert(currentUser);
    }

}