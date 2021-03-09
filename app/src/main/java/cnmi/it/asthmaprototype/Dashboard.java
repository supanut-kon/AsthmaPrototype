package cnmi.it.asthmaprototype;

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
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

public class Dashboard extends Fragment {

    BottomNavigationView bottomNavigationView;
    ImageView profilepic;
    ImageView dashboardPic; 
    ProgressBar progressBar;
    ImageView SOS;
    TextView name;
    Uri gPhoto;

    @Override
    public void onCreateView(Bundle savedInstanceState) {
         return inflater.inflate(R.layout.dashboard_fragment, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//        if(acct != null){
//            gName = acct.getDisplayName();
//            gPhoto = acct.getPhotoUrl();
//        }
        Uri pf = Uri.parse(extras.getString("uri"));
        String nname = extras.getString("strName");

        //bottomNavigationView = findViewById(R.id.btnnvg);
        profilepic = findViewById(R.id.dbprofilepic);
        profilepic.setImageURI(pf);
        name = findViewById(R.id.user_name);
        name.setText(nname);
        dashboardPic = findViewById(R.id.dashboardimage);
        progressBar = findViewById(R.id.dashboardProgressBar);
        SOS = findViewById(R.id.sosbtn);
        SOS.setImageResource(R.drawable.sos);
        SOS.setOnClickListener(v -> finish());

        progressBar.setScaleX(3f);
        progressBar.setScaleY(3f);
        progressBar.setProgress(75);
        dashboardPic.setImageResource(R.drawable.ventolin_inhaler);

//    bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
//        int itemId = item.getItemId();
//        if (itemId == R.id.home) {
//
//        } else if (itemId == R.id.add) {
//
//            Intent intentFlow = new Intent(Dashboard.this, flowFragment.class);
//            startActivity(intentFlow);
//
//        } else if (itemId == R.id.profile) {
//            Intent intentProfile = new Intent(Dashboard.this, Profile.class);
//            startActivity(intentProfile);
//        }
//        return true;
//    });

    }
}
