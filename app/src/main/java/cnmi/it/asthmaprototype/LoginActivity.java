package cnmi.it.asthmaprototype;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    Button register;
    SignInButton signInButton;
    GoogleSignInClient googleSignInClient;
    FirebaseAuth auth;
    int RC_SIGN_IN = 1;
//    SharedPreferences sharedPreferences;
//    static final String gName = "gPersonName";
//    static final String gGvnName = "gGivenName";
//    static final String gFamName = "gFamilyName";
//    static final String gEmail = "gEmail";
//    static final String gId = "gId";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        signInButton = findViewById(R.id.googlebtn);
        register = findViewById(R.id.register);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton.setOnClickListener(v->{
           // signIn();
        });

        register.setOnClickListener(v -> {
            finish();
            Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(register);
        });

//        config.setOnClickListener(v ->{
//           finish();
//           Intent config = new Intent(LoginActivity.this, PatientConfig.class);
//           startActivity(config);
//        });
    }



    private void signIn(){
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completeTask){
        try{
            GoogleSignInAccount acc = completeTask.getResult(ApiException.class);
            Toast.makeText(LoginActivity.this, "Signed In Successfully", Toast.LENGTH_SHORT).show();

        } catch (ApiException e) {
            Toast.makeText(LoginActivity.this, "Failed to Sign In", Toast.LENGTH_SHORT).show();

        }
    }

}
