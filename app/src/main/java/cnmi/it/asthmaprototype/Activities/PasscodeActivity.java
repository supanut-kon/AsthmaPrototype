package cnmi.it.asthmaprototype.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hanks.passcodeview.PasscodeView;

import cnmi.it.asthmaprototype.R;

public class PasscodeActivity  extends AppCompatActivity {

    PasscodeView passcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passcode_view);

        passcodeView = findViewById(R.id.passcodeView);


        passcodeView.setPasscodeLength(4).setLocalPasscode("1234")
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {
                        Toast.makeText(getApplicationContext(), "Wrong Passcode", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String number) {
                        startActivity(new Intent(PasscodeActivity.this, PatientInformationEdit.class));
                    }
                });
    }
}
