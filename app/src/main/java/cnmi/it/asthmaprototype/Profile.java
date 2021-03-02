package cnmi.it.asthmaprototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Profile extends AppCompatActivity {

    Button logout;
    ImageView profilepic;
    TextView userAge;
    TextView userHeight;
    TextView userGender;
    UserModel user;
    Button configbtn;
    int id;
    int age;
    int height;
    String gender;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        googleSignInClient.getSignInIntent();

        logout = findViewById(R.id.logoutbtn);
        userAge = findViewById(R.id.ageText);
        userHeight = findViewById(R.id.heightText);
        userGender = findViewById(R.id.genderText);
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

    public void getDetails(){
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM asthma_patient", null);
        if(c!=null) {
            c.moveToFirst();
            while (!c.isAfterLast()) {

                //user = new UserModel(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3));
                id = c.getInt(0);
                age = c.getInt(1);
                height = c.getInt(2);
                gender = c.getString(3);

                c.moveToNext();
            }
            c.close();
            db.close();

            userAge.setText(String.format("Age %s Years old", age));
            userHeight.setText(String.format("%s Centimetres", height));
            userGender.setText(String.format("เพศ %s", gender));
        } else {
            userAge.setText("Fill in gender, age, and height first!");

        }
    }

    public void GoogleSignOut(){

        googleSignInClient.signOut().addOnCompleteListener(this, task -> {
            Intent backtoLogin = new Intent(Profile.this, LoginActivity.class);
            backtoLogin.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(backtoLogin);
        });
    }
    
    



}