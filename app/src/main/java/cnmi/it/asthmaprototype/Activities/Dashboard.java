package cnmi.it.asthmaprototype.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import cnmi.it.asthmaprototype.Adapters.CardAdapter;
import cnmi.it.asthmaprototype.Database.DatabaseAccess;
import cnmi.it.asthmaprototype.Models.FlowModel;
import cnmi.it.asthmaprototype.R;

public class Dashboard extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    BottomAppBar bottomAppBar;
    ImageView profilepic;
    ImageView dashboardPic;
    ProgressBar progressBar;
    String gName;
    ImageView SOS;
    TextView name;
    Uri gPhoto;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    CardAdapter card;
    CardView profilecard;
//    private FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();

//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct != null){
            gName = acct.getDisplayName();
            gPhoto = acct.getPhotoUrl();
        }
//        Uri pf = Uri.parse(extras.getString("uri"));
//        String nname = extras.getString("strName");

        profilepic = findViewById(R.id.dbprofilepic);
        profilepic.setImageURI(gPhoto);
        profilepic.setImageResource(R.drawable.man);
        name = findViewById(R.id.user_name);
        name.setText("Patient's Name");
        dashboardPic = findViewById(R.id.dashboardimage);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        SOS = findViewById(R.id.sosimg);
        fab = findViewById(R.id.fabadd);
        recyclerView = findViewById(R.id.recyclerView);

        SOS.setOnClickListener(v -> {
            startActivity(new Intent(Dashboard.this, RedWarning.class));
        });

        getFlows();

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

        fab.setOnClickListener(v -> startActivity(new Intent(Dashboard.this, FlowActivity.class)));

        profilecard.setOnClickListener(v -> startActivity(new Intent(Dashboard.this, Profile.class)));

    }
    public void getFlows(){
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

    @Override
    protected void onResume() {
        super.onResume();
        getFlows();
    }

}
