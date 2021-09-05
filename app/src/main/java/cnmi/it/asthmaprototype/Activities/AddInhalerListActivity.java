package cnmi.it.asthmaprototype.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import cnmi.it.asthmaprototype.Adapters.InhalerCardAdapter;
import cnmi.it.asthmaprototype.Database.DatabaseAccess;
import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.InhalerColumn;
import cnmi.it.asthmaprototype.Models.InhalerModel;
import cnmi.it.asthmaprototype.Models.TransColumn;
import cnmi.it.asthmaprototype.R;

public class AddInhalerListActivity extends AppCompatActivity {

    int selectposition, ismorning, isevening, isemergency;
    String resourcename, times, inaday;
    InhalerModel addinhaler;
    ArrayList<InhalerModel> inhalerArrayList;
    RecyclerView recycler;
    InhalerCardAdapter card;
    FloatingActionButton savefab;
    Button addmore;
    TextView test;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inhaler_list);
        addmore = findViewById(R.id.addmorebtn);
        recycler =findViewById(R.id.recycler);
        savefab = findViewById(R.id.addinhalersaveFab);
        inhalerArrayList = new ArrayList<>();

        getInhaler();
//        test.setText(inhalerArrayList.get(0).getName());

        addmore.setOnClickListener(v->{
            Intent toadd = new Intent(AddInhalerListActivity.this, AddInhalerActivity.class);
            toadd.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(toadd, 0);
        });

        savefab.setOnClickListener(v->{

                Intent toProfile = new Intent(AddInhalerListActivity.this, EditProfile.class);
                toProfile.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                toProfile.putExtra("added", true);
                finish();
                startActivityIfNeeded(toProfile, 0);
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        getInhaler();
        Log.d("RESUME", "RESUME");
    }


    public void getInhaler(){
        DatabaseAccess db = DatabaseAccess.getInstance(this);
        db.open();
        inhalerArrayList = db.getAddInhaler();
        db.close();

        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layout);
        card = new InhalerCardAdapter(this, inhalerArrayList);
        recycler.setAdapter(card);
        card.notifyDataSetChanged();
        //Log.d("recycler", recycler.getLayoutManager().toString());
    }


}