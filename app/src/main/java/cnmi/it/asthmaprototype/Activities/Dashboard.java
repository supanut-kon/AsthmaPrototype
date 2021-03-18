package cnmi.it.asthmaprototype.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import cnmi.it.asthmaprototype.CardAdapter;
import cnmi.it.asthmaprototype.Database.DatabaseAccess;
import cnmi.it.asthmaprototype.Models.FlowModel;
import cnmi.it.asthmaprototype.R;

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
    RecyclerView recyclerView;
    CardAdapter card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();

//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//        if(acct != null){
//            gName = acct.getDisplayName();
//            gPhoto = acct.getPhotoUrl();
//        }
//        Uri pf = Uri.parse(extras.getString("uri"));
//        String nname = extras.getString("strName");

        //bottomNavigationView = findViewById(R.id.btnnvg);
        profilepic = findViewById(R.id.dbprofilepic);
//        profilepic.setImageURI(pf);
        profilepic.setImageResource(R.drawable.man);
        name = findViewById(R.id.user_name);
        name.setText("Patient's Name");
        dashboardPic = findViewById(R.id.dashboardimage);
//        progressBar = findViewById(R.id.dashboardProgressBar);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        SOS = findViewById(R.id.sosimg);
        fab = findViewById(R.id.fabadd);
        recyclerView = findViewById(R.id.recyclerView);

        SOS.setOnClickListener(v -> {
            startActivity(new Intent(Dashboard.this, RedWarning.class));
        });

//        progressBar.setScaleX(3f);
//        progressBar.setScaleY(3f);
//        progressBar.setProgress(75);
//        dashboardPic.setImageResource(R.drawable.ventolin_inhaler);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ArrayList<FlowModel> queryFlows = databaseAccess.getFlow();
        databaseAccess.close();

        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        card = new CardAdapter(this, queryFlows);
        recyclerView.setAdapter(card);
        card.notifyDataSetChanged();

        bottomAppBar.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {

            } else if (itemId == R.id.add) {
                Intent intentFlow = new Intent(Dashboard.this, Profile.class);
                startActivity(intentFlow);
            }
            return true;
        });

        fab.setOnClickListener(v -> {
            startActivity(new Intent(Dashboard.this, FlowActivity.class));
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ArrayList<FlowModel> queryFlows = databaseAccess.getFlow();
        databaseAccess.close();

        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        card = new CardAdapter(this, queryFlows);
        recyclerView.setAdapter(card);
        card.notifyDataSetChanged();
    }
}
