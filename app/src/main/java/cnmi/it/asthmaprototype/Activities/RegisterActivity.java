package cnmi.it.asthmaprototype.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import cnmi.it.asthmaprototype.R;

public class RegisterActivity extends AppCompatActivity {
    Button Reg;
    EditText username, email, password, repeatpassword;
    String un, em, pw, rpw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        username = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmailAddress);
        password = findViewById(R.id.registerPassword);
        repeatpassword = findViewById(R.id.registerPasswordrepeat);



        Reg = findViewById(R.id.registerbtn);

        Reg.setOnClickListener(v -> {

            un = username.getText().toString();
            em = email.getText().toString();
            pw = password.getText().toString();
            rpw = repeatpassword.getText().toString();

            if(rpw != pw){
                
            }else{
                finish();
                Intent dash = new Intent(RegisterActivity.this, Dashboard.class);
                startActivity(dash);
            }

        });


    }
}
