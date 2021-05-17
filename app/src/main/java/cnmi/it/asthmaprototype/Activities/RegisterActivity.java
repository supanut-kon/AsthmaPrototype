package cnmi.it.asthmaprototype.Activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import cnmi.it.asthmaprototype.Database.DatabaseAccess;
import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.UserColumn;
import cnmi.it.asthmaprototype.Models.UserModel;
import cnmi.it.asthmaprototype.R;

public class RegisterActivity extends AppCompatActivity {
    Button Reg;
    EditText username, email, password, repeatpassword;
    String un, em, pw, rpw;
    SharedPreferences prefs;


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

        prefs = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);

        Reg = findViewById(R.id.registerbtn);

        Reg.setOnClickListener(v -> {

            un = username.getText().toString();
            em = email.getText().toString().trim();
            pw = password.getText().toString().trim();
            rpw = repeatpassword.getText().toString().trim();

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
                db.close();

//                int id = loginAfterRegister(em);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("username", un);
                editor.putString("email", em);
//                editor.putInt("id", id);
                editor.apply();

                finish();
                Intent dash = new Intent(RegisterActivity.this, Dashboard.class);
                startActivity(dash);
            }

        });


    }

    private int loginAfterRegister(String email){ 
        UserModel user = null;
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        ArrayList<UserModel> theuser = databaseAccess.getUserInfoAfterLogin(email);
        for (int i = 0; i < theuser.size(); i++) {
           
            user = theuser.get(i);
        }
        int userid = 0;
        if (user != null) {
            userid = user.getId();
        }
        databaseAccess.close();
        
        return userid;
        
    }
//     private String getPatient(int id){
//         UserModel user = null;
//         DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
//         databaseAccess.open();
//
//         ArrayList<UserModel> theuser = databaseAccess.getUser(id);
//         for (int i = 0; i < theuser.size(); i++) {
//
//             user = theuser.get(i);
//         }
//         String patients = null;
//         if(user != null) {
//             if (user.getPatientid() != null) {
//                 patients = user.getPatientid();
//             } else patients = null;
//         }
//         databaseAccess.close();
//
//         return patients;
//     }
    
    
}
