package cnmi.it.asthmaprototype.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import cnmi.it.asthmaprototype.R;

public class LoginActivity extends AppCompatActivity {


    SignInButton signInButton;
    GoogleSignInClient googleSignInClient;
    Button guestIn;
    TextView skip;
    int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar); y

        signInButton = findViewById(R.id.googlebtn);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        guestIn = findViewById(R.id.guestlogin);
        //register = findViewById(R.id.register);
        skip = findViewById(R.id.SkipText);

        skip.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, Dashboard.class)));

        guestIn.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton.setOnClickListener(v -> signIn());
    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completeTask) {
        try {
            GoogleSignInAccount acc = completeTask.getResult(ApiException.class);
            String name = acc.getDisplayName();
            Uri pfphoto = acc.getPhotoUrl();
            Intent toDashboard = new Intent(LoginActivity.this, Dashboard.class);
            toDashboard.putExtra("strName", name);

            if (pfphoto != null) {
                toDashboard.putExtra("uri", pfphoto.toString());
            }
            startActivity(toDashboard);

            Toast.makeText(LoginActivity.this, "Signed In Successfully", Toast.LENGTH_SHORT).show();

        } catch (ApiException e) {
            Toast.makeText(LoginActivity.this, "Failed to Sign In", Toast.LENGTH_SHORT).show();

        }
    }

}
