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
import cnmi.it.asthmaprototype.Models.tmp_inhalerModel;
import cnmi.it.asthmaprototype.R;

public class addInhalerListActivity extends AppCompatActivity {

    int selectposition, ismorning, isevening, isemergency;
    String resourcename, times, inaday;
    InhalerModel addinhaler;
    ArrayList<tmp_inhalerModel> inhalerArrayList;
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
//        Bundle extras = getIntent().getExtras();
//        selectposition = extras.getInt("resourceid");
//        resourcename = extras.getString("resourcename");
//        isemergency = extras.getInt("isemergnecy");
//        ismorning = extras.getInt("ismorning");
//        isevening = extras.getInt("isevening");
//        times = extras.getString("times");
//        inaday = extras.getString("inaday");
//        addinhaler = new InhalerModel(0, selectposition, resourcename, times, inaday, isemergency, 1);
//        inhalerArrayList.add(addinhaler);

        getInhaler();
//        test.setText(inhalerArrayList.get(0).getName());


        addmore.setOnClickListener(v->{
            Intent toadd = new Intent(addInhalerListActivity.this, AddInhalerActivity.class);
            toadd.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(toadd, 0);
        });

        savefab.setOnClickListener(v->{
            DatabaseHelper helper = new DatabaseHelper(this);
            SQLiteDatabase writedb = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            ContentValues cvv = new ContentValues();
            for(int i = 0; i < inhalerArrayList.size(); i++) {
                cv.put(InhalerColumn.InhalerEntry.COLUMN_DID, inhalerArrayList.get(i).getDid());
                cv.put(InhalerColumn.InhalerEntry.COLUMN_NAME, inhalerArrayList.get(i).getName());
                cv.put(InhalerColumn.InhalerEntry.COLUMN_TYPE, inhalerArrayList.get(i).getType());
                cv.put(InhalerColumn.InhalerEntry.COLUMN_TIMES, inhalerArrayList.get(i).getTimes());
                cv.put(InhalerColumn.InhalerEntry.COLUMN_INADAY, inhalerArrayList.get(i).getInaday());
                //cv.put(InhalerColumn.InhalerEntry.COLUMN_ISACTIVE, 1);
                writedb.insert(InhalerColumn.InhalerEntry.TABLE_NAME, null, cv);

                cvv.put(TransColumn.TransEntry.COLUMN_DID, inhalerArrayList.get(i).getDid());
                cvv.put(TransColumn.TransEntry.COLUMN_PID, 1);
                cvv.put(TransColumn.TransEntry.COLUMN_MORNING, inhalerArrayList.get(i).getMorning());
                cvv.put(TransColumn.TransEntry.COLUMN_EVENING, inhalerArrayList.get(i).getEvening());
                writedb.insert(TransColumn.TransEntry.TABLE_NAME, null, cvv);

                helper.deletetmp(writedb);
                writedb.close();

                Intent toProfile = new Intent(addInhalerListActivity.this, PatientInformationEdit.class);
                toProfile.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                finish();
                startActivityIfNeeded(toProfile, 0);
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        getInhaler();
        Log.d("RESUME", "RESUME");
//        Bundle moreextras = getIntent().getExtras();
//        selectposition = moreextras.getInt("resourceid");
//        resourcename = moreextras.getString("resourcename");
//        isemergency = moreextras.getInt("isemergnecy");
//        ismorning = moreextras.getInt("ismorning");
//        isevening = moreextras.getInt("isevening");
//        times = moreextras.getString("times");
//        inaday = moreextras.getString("inaday");
//        addinhaler = new InhalerModel(0, selectposition, resourcename, times, inaday, isemergency, 1);
//        inhalerArrayList.add(addinhaler);
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