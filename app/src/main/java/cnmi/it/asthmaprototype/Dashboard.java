package cnmi.it.asthmaprototype;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

public class Dashboard extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    BottomAppBar bottomAppBar;
    ImageView profilepic;
    ImageView dashboardPic;
    ProgressBar progressBar;
    ImageView SOS;
    TextView name;
    Uri gPhoto;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();

//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//        if(acct != null){
//            gName = acct.getDisplayName();
//            gPhoto = acct.getPhotoUrl();
//        }
        Uri pf = Uri.parse(extras.getString("uri"));
        String nname = extras.getString("strName");

        //bottomNavigationView = findViewById(R.id.btnnvg);
        profilepic = findViewById(R.id.dbprofilepic);
//        profilepic.setImageURI(pf);
        profilepic.setImageResource(R.drawable.man);
        name = findViewById(R.id.user_name);
        name.setText("Patient's Name");
        dashboardPic = findViewById(R.id.dashboardimage);
//        progressBar = findViewById(R.id.dashboardProgressBar);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        SOS = findViewById(R.id.sosbtn);
        SOS.setImageResource(R.drawable.sos);
        SOS.setOnClickListener(v -> finish());

//        progressBar.setScaleX(3f);
//        progressBar.setScaleY(3f);
//        progressBar.setProgress(75);
//        dashboardPic.setImageResource(R.drawable.ventolin_inhaler);

        bottomAppBar.setOnMenuItemClickListener(item ->{
            int itemId = item.getItemId();
            if (itemId == R.id.home) {

            } else if (itemId == R.id.add) {
                Intent intentFlow = new Intent(Dashboard.this, Profile.class);
                startActivity(intentFlow);
            }
            return true;
        });

        fab.setOnClickListener(v -> {
           startActivity(new Intent(Dashboard.this, flowFragment.class));
        });

    }
}
