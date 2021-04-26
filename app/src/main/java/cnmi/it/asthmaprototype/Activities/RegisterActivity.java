package cnmi.it.asthmaprototype.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.UserColumn;
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

            if(!rpw.equals(pw)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("รหัสผ่าน").setMessage("รหัสผ่านไม่ตรงกัน กรุญาตรวจสอบอีกครั้ง").setNeutralButton(android.R.string.ok, null)
                        .setIcon(android.R.drawable.ic_dialog_alert).show();
            }else{
                DatabaseHelper dbHelper = new DatabaseHelper(this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(UserColumn.UserEntry.NAME, un);
                values.put(UserColumn.UserEntry.EMAIL, em);
                values.put(UserColumn.UserEntry.PASSWORD, pw);
                db.insert(UserColumn.UserEntry.TABLE_NAME, null, values);

                finish();
                Intent dash = new Intent(RegisterActivity.this, Dashboard.class);
                startActivity(dash);
            }

        });


    }
}
