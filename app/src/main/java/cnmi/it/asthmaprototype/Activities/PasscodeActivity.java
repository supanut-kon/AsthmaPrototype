package cnmi.it.asthmaprototype.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hanks.passcodeview.PasscodeView;

import java.util.ArrayList;

import cnmi.it.asthmaprototype.Database.DatabaseAccess;
import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.UserColumn;
import cnmi.it.asthmaprototype.Models.UserModel;
import cnmi.it.asthmaprototype.R;

public class PasscodeActivity  extends AppCompatActivity {

    PasscodeView passcodeView;
    SharedPreferences sharedPreferences;
    String localPasscode;
    ArrayList<UserModel> user = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passcode_view);

        passcodeView = findViewById(R.id.passcodeView);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        user = databaseAccess.getUser();
        String passcode = user.get(0).getPasscode();
        if(passcode != null){
            passcodeView.setLocalPasscode(passcode);
        }

        DatabaseHelper helper = new DatabaseHelper(this);
        passcodeView.setPasscodeLength(4).setPasscodeType(PasscodeView.PasscodeViewType.TYPE_SET_PASSCODE).setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {
                        Toast.makeText(getApplicationContext(), "Wrong Passcode", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String number) {


                        String passcode = passcodeView.getLocalPasscode();
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("passcode", passcode);
//                        editor.apply();

                        SQLiteDatabase writedb = helper.getWritableDatabase();
                        ContentValues cv = new ContentValues();
                        cv.put(UserColumn.UserEntry.PASSCODE, passcode);
                        writedb.update(UserColumn.UserEntry.TABLE_NAME,cv, "id = ?", new String[]{String.valueOf(1)});
                        startActivity(new Intent(PasscodeActivity.this, EditProfile.class));
                        finish();
                    }
                });



        

    }
}
