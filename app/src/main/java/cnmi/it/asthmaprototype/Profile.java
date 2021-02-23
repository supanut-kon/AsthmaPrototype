package cnmi.it.asthmaprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Profile extends AppCompatActivity {

    Button logout;
    ImageView profilepic;
    TextView userAge;
    TextView userHeight;
    TextView userGender;
    Executor executor;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logout = findViewById(R.id.logoutbtn);
        userAge = findViewById(R.id.ageText);
        userHeight = findViewById(R.id.heightText);
        userGender = findViewById(R.id.genderText);
        
        logout.setOnClickListener(v -> finish());
        profilepic = findViewById(R.id.profilepic);
        profilepic.setImageResource(R.drawable.man);
        

    }
    
    



}