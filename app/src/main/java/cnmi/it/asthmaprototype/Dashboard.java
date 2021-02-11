package cnmi.it.asthmaprototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Dashboard extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView profilepic;
    ImageView dashboardPic;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.btnnvg);
        profilepic = findViewById(R.id.dbprofilepic);
        profilepic.setImageResource(R.drawable.man);
        dashboardPic = findViewById(R.id.dashboardimage);
        progressBar = findViewById(R.id.dashboardProgressBar);

        progressBar.setScaleX(3f);
        progressBar.setScaleY(3f);
        progressBar.setProgress(75);
        dashboardPic.setImageResource(R.drawable.ventolin_inhaler);

    bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
        int itemId = item.getItemId();
        if (itemId == R.id.home) {

        } else if (itemId == R.id.add) {
            Intent intentFlow = new Intent(Dashboard.this, flowFragment.class);
            startActivity(intentFlow);
        } else if (itemId == R.id.profile) {
            Intent intentProfile = new Intent(Dashboard.this, Profile.class);
            startActivity(intentProfile);
        }
        return true;
    });

    }





}
