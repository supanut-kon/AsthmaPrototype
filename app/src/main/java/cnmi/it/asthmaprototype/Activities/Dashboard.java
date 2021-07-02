package cnmi.it.asthmaprototype.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import cnmi.it.asthmaprototype.Adapters.CardAdapter;
import cnmi.it.asthmaprototype.Adapters.YellowCardAdapter;
import cnmi.it.asthmaprototype.Database.DatabaseAccess;
import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.FlowModel;
import cnmi.it.asthmaprototype.Models.InhalerModel;
import cnmi.it.asthmaprototype.Models.PatientModel;
import cnmi.it.asthmaprototype.Models.UserModel;
import cnmi.it.asthmaprototype.Models.YellowPFModel;
import cnmi.it.asthmaprototype.R;

public class Dashboard extends AppCompatActivity {

    BottomAppBar bottomAppBar;
    ImageView profilepic;
    ImageView dashboardPic;
    String gName;
    ImageView SOS;
    TextView name;
    Uri gPhoto;
    FloatingActionButton fab;
    RecyclerView recyclerView, yellowCard;
    CardAdapter card;
    YellowCardAdapter ycard;
    CardView profilecard;
    ArrayList<YellowPFModel> yellows;
    ArrayList<InhalerModel> yellowinhaler = new ArrayList<>();
    SharedPreferences sharedPreferences;
//    private FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //Bundle extras = getIntent().getExtras();
        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        profilecard = findViewById(R.id.profilepiccard);

//        getUserId();
        //ArrayList<PatientModel> patients = db.getPatient(userid);
//        if(patients == null){
            profilecard.setOnClickListener(v -> startActivity(new Intent(Dashboard.this, PasscodeActivity.class)));
//        }else {
//            profilecard.setOnClickListener(v -> startActivity(new Intent(Dashboard.this, AddProfile.class)));
//        }
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct != null){
            gName = acct.getDisplayName();
            gPhoto = acct.getPhotoUrl();
        }
//        Uri pf = Uri.parse(extras.getString("uri"));
//        String nname = extras.getString("strName");

//        profilepic = findViewById(R.id.dbprofilepic);
//        profilepic.setImageURI(gPhoto);
//        profilepic.setImageResource(R.drawable.man);
        name = findViewById(R.id.user_name);
//        name.setText("Patient's Name");
        dashboardPic = findViewById(R.id.dashboardimage);

        bottomAppBar = findViewById(R.id.bottomAppBar);
        SOS = findViewById(R.id.sosimg);
        fab = findViewById(R.id.fabadd);
        recyclerView = findViewById(R.id.recyclerView);
        yellowCard = findViewById(R.id.yellowCardRecyclerView);

        SOS.setOnClickListener(v -> {
            startActivity(new Intent(Dashboard.this, RedWarning.class));
        });

        getFlows();
        getYellowandDisplay();

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

        //profilecard.setOnClickListener(v -> startActivity(new Intent(Dashboard.this, Profile.class)));

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

//    public void getUserId(){
//        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
//        databaseAccess.open();
//        ArrayList<UserModel> user = databaseAccess.getUser();
//        databaseAccess.close();
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt("id", user.get(0).getId());
//        editor.apply();
//        name.setText(user.get(0).getName());
//    }

    public void getYellowandDisplay(){
        int yid = 0, did;
        String enddate = null, datel;
        Date date = null;
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM yellow_log WHERE is_active = " + 1 + " ", null);
        c.moveToFirst();
        while(c.isAfterLast()) {
            yid = c.getInt(0);
            enddate = c.getString(9);


        }
        c.close();
        db.close();
        String dateFormat = "d MMMM y";
        SimpleDateFormat df = new SimpleDateFormat(dateFormat, new Locale("th" , "TH"));
        try{
            date = df.parse(enddate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(new Date().after(date)){
            
        }


        if(yid != 0){
            DatabaseAccess dbAccess = DatabaseAccess.getInstance(this);
            yellowinhaler = dbAccess.getInhaler(4);
            LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            yellowCard.setLayoutManager(layout);
            ycard = new YellowCardAdapter(this, yellowinhaler);
            recyclerView.setAdapter(ycard);
            ycard.notifyDataSetChanged();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        getFlows();
    }

}
