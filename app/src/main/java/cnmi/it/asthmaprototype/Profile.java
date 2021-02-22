package cnmi.it.asthmaprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    Button logout;
    ImageView profilepic;
    TextView userAge;
    TextView userHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logout = findViewById(R.id.logoutbtn);

        logout.setOnClickListener(v -> finish());
        profilepic = findViewById(R.id.profilepic);
        profilepic.setImageResource(R.drawable.man);
    }



}