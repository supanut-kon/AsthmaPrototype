 package cnmi.it.asthmaprototype.Activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
import cnmi.it.asthmaprototype.Models.PatientColumn;
import cnmi.it.asthmaprototype.R;


public class Profile extends AppCompatActivity {

    Button logout, peakflowedit;
    ImageView profilepic;
    TextView patientAge, patientHeight, patientGender, patientWeight, patientCongenital, patientName, patientHn, pefrtext, yellowpefr, redpefr;
    Button configbtn;
    int id = 0, age, height, pefr, yellow, red, newmax;
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
        pefrtext = findViewById(R.id.pefrprofile);
        yellowpefr = findViewById(R.id.yellowpefrprofile);
        redpefr = findViewById(R.id.redpefrprofile);
        peakflowedit = findViewById(R.id.peakfloweditbtn);

        logout.setOnClickListener(v -> GoogleSignOut());
        profilepic = findViewById(R.id.profilepic);
        profilepic.setImageResource(R.drawable.man);

        backImg.setOnClickListener(v -> onBackPressed());

        configbtn.setOnClickListener(v -> {
            Intent config = new Intent(Profile.this, EditProfile.class);
            startActivity(config);
        });
        getDetails();

        peakflowedit.setOnClickListener(v ->{
            peakfloweditDialog(this);
        });
    }

    public void peakfloweditDialog(Context c){
        final EditText newpeakflow = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("ตั้งค่าแรงลมสูงสุดใหม่")
                .setView(newpeakflow)
                .setPositiveButton("ตกลง", (dialog1, which) -> {
            newmax = Integer.parseInt(String.valueOf(newpeakflow.getText()));
            yellow = (int) (newmax*(80.00/100.00));
            red = (int) (newmax*(60.00/100.00));

            pefrtext.setText(String.format("ค่าแรงลมสูงสุด %s", newmax));
            yellowpefr.setText(String.valueOf(yellow));
            redpefr.setText(String.valueOf(red));

            DatabaseHelper helper = new DatabaseHelper(this);
            SQLiteDatabase writedb = helper.getReadableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put(PatientColumn.PatientEntry.COLUMN_PEFR, newmax);
            writedb.update("asthma_patient", cv, "_id = 1", null);
            writedb.close();

        })
                .setNegativeButton("ยกเลิก", null)
                .create();
        dialog.show();
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
                pefr = c.getInt(5);
                height = c.getInt(6);
                weight = c.getString(7);
                congenital = c.getString(8);
                gender = c.getString(9);
                c.moveToNext();
            }
            c.close();
            db.close();
            if(id == 0){
                patientAge.setText("Fill in gender, age, and height first!");
            } else {
                yellow = (int) (pefr*(80.00/100.00));
                red = (int) (pefr*(60.00/100.00));
                patientName.setText(name);
                pefrtext.setText(String.format("ค่าแรงลมสูงสุด %s", pefr));
                yellowpefr.setText(String.valueOf(yellow));
                redpefr.setText(String.valueOf(red));
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