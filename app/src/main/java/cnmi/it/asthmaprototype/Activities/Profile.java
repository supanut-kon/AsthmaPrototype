package cnmi.it.asthmaprototype.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.R;


public class Profile extends AppCompatActivity {

    Button logout;
    ImageView profilepic;
    TextView patientAge, patientHeight, patientGender, patientWeight, patientCongenital, patientName, patientHn;
    Button configbtn;
    int id, age, height;
    String gender, weight, congenital, name ,hn;
    GoogleSignInClient googleSignInClient;
    ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //googleSignInClient.getSignInIntent();
        final EditText passcodeInput = new EditText(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        passcodeInput.setLayoutParams(params);
        passcodeInput.setInputType(1);

        logout = findViewById(R.id.logoutbtn);
        patientAge = findViewById(R.id.ageText);
        patientHeight = findViewById(R.id.heightText);
        patientGender = findViewById(R.id.genderText);
        patientWeight = findViewById(R.id.weightProfileText);
        patientCongenital = findViewById(R.id.congenitalProfileText);
        patientName = findViewById(R.id.nametext);
        patientHn = findViewById(R.id.hnText);
        configbtn = findViewById(R.id.configbtn);
        backImg = findViewById(R.id.backImage);

        logout.setOnClickListener(v -> GoogleSignOut());
        profilepic = findViewById(R.id.profilepic);
        profilepic.setImageResource(R.drawable.man);

        backImg.setOnClickListener(v -> onBackPressed());

        configbtn.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Set Passcode")
                    .setMessage("Please set a passcode before continue")
                    .setCancelable(true)
                    .setView(passcodeInput)
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {

                    }).setNegativeButton(android.R.string.no, null)
                    .show();
//            Intent config = new Intent(Profile.this, PatientInformationEdit.class);
//            startActivity(config);
        });
        getDetails();

    }

    public void getDetails() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM asthma_patient", null);
        {
            c.moveToFirst();
            while (!c.isAfterLast()) {

                //user = new UserModel(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3));
                id = c.getInt(0);
                age = c.getInt(1);
                hn = c.getString(2);
                name = c.getString(3);
                height = c.getInt(4);
                weight = c.getString(5);
                congenital = c.getString(6);
                gender = c.getString(7);
                c.moveToNext();
            }
            c.close();
            db.close();
            if(age == Integer.parseInt(null)){
                patientAge.setText("Fill in gender, age, and height first!");
            } else {
                patientName.setText(name);
                patientHn.setText(hn);
                patientAge.setText(String.format("อายุ %s ปี", age));
                patientHeight.setText(String.format("สูง %s เซนติเมตร", height));
                patientGender.setText(String.format("เพศ %s", gender));
                patientWeight.setText(String.format("น้ำหนัก %s", weight));
                patientCongenital.setText(String.format("โรคประจำตัว %s", congenital));
            }
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