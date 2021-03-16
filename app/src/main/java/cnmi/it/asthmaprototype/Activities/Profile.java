package cnmi.it.asthmaprototype.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.R;


public class Profile extends AppCompatActivity {

    Button logout;
    ImageView profilepic;
    TextView patientAge, patientHeight, patientGender, patientWeight, patientCongenital;
    Button configbtn;
    int id;
    int age;
    int height;
    String gender;
    String weight;
    String congenital;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //googleSignInClient.getSignInIntent();

        logout = findViewById(R.id.logoutbtn);
        patientAge = findViewById(R.id.ageText);
        patientHeight = findViewById(R.id.heightText);
        patientGender = findViewById(R.id.genderText);
        patientWeight = findViewById(R.id.weightProfileText);
        patientCongenital = findViewById(R.id.congenitalProfileText);
        configbtn = findViewById(R.id.configbtn);

        logout.setOnClickListener(v -> GoogleSignOut());
        profilepic = findViewById(R.id.profilepic);
        profilepic.setImageResource(R.drawable.man);

        configbtn.setOnClickListener(v -> {

            Intent config = new Intent(Profile.this, PatientConfig.class);
            startActivity(config);
        });
        getDetails();

    }

    public void getDetails() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM asthma_patient", null);
        if (c != null) {
            c.moveToFirst();
            while (!c.isAfterLast()) {

                //user = new UserModel(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3));
                id = c.getInt(0);
                age = c.getInt(1);
                height = c.getInt(2);
                weight = c.getString(3);
                congenital = c.getString(4);
                gender = c.getString(5);
                c.moveToNext();
            }
            c.close();
            db.close();

            patientAge.setText(String.format("อายุ %s ปี", age));
            patientHeight.setText(String.format("สูง %s เซนติเมตร", height));
            patientGender.setText(String.format("เพศ %s", gender));
            patientWeight.setText(String.format("น้ำหนัก %s", weight));
            patientCongenital.setText(String.format("โรคประจำตัว %s", congenital));
        } else {
            patientAge.setText("Fill in gender, age, and height first!");

        }
    }

    public void GoogleSignOut() {

        googleSignInClient.signOut().addOnCompleteListener(this, task -> {
            Intent backtoLogin = new Intent(Profile.this, LoginActivity.class);
            backtoLogin.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(backtoLogin);
        });
    }


}