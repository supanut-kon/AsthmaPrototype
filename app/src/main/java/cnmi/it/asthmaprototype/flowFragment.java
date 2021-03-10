package cnmi.it.asthmaprototype;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;

public class flowFragment extends AppCompatActivity {
    Button savebtn;
    TextView barValue;
    TextView greenpef;
    TextView yellowpef;
    TextView redpef;
    SeekBar bar;
    int pfvalue;
    View blur;
    int count;
    int id;
    int age;
    int height;
    String gender;
    long flow;
    double peakflow;
    ChipGroup periodchip;
    FloatingActionButton fabNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flow_fragment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //savebtn = findViewById(R.id.flow_savebtn);
        bar = findViewById(R.id.seekBar);
        barValue = findViewById(R.id.barvalue);
        greenpef = findViewById(R.id.greenpef);
        yellowpef = findViewById(R.id.yellowpef);
        redpef = findViewById(R.id.redpef);
        periodchip = findViewById(R.id.periodchips);
        checkentry();

    }
    public void checkentry(){
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor f = db.rawQuery("SELECT * FROM asthma_flow", null); // WHERE Userid = currentUser

        if(f.getCount() < 0){
            finish();
            Intent toConfig = new Intent(flowFragment.this, PatientConfig.class);
            startActivity(toConfig);
        }else{
            flowMeasure();
        }
        f.close();
    }

    public void flowMeasure(){
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM asthma_patient", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            //user = new UserModel(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3));
            id = c.getInt(0);
            age = c.getInt(1);
            height = c.getInt(2);
            gender = c.getString(3);

            c.moveToNext();
        }
        c.close();


//        Cursor f = db.rawQuery("SELECT * FROM asthma_patient", null);
//        f.moveToFirst();
//        while (!f.isAfterLast()) {
//
//            //user = new UserModel(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3));
//            flow = f.getInt(1);
//
//            f.moveToNext();
//        }
//        f.close();
//        db.close();
        DecimalFormat df = new DecimalFormat("#.##");

        if(gender != null){
            if(gender.equals("ชาย")){
                peakflow = 319.13-(4.75*height)+0.035*Math.pow(height,2);
            }else {
                peakflow = -487.12+(7*height)-0.0085*Math.pow(height,2);
            }

            bar.setMax((int) peakflow);
            greenpef.setText(df.format(peakflow));
            yellowpef.setText(df.format(peakflow * (80.00 / 100.00)));
            redpef.setText(df.format(peakflow * (60.00 / 100.00)));
        } else {
            bar.setMax(900);
            greenpef.setText(String.valueOf(900));
            yellowpef.setText(String.valueOf(900*(80.00/100.00)));
            redpef.setText(String.valueOf(900*(60.00/100.00)));
        }
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //int val = (progress *(seekBar.getWidth()- 2 *seekBar.getThumbOffset())) / seekBar.getMax();
                barValue.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        fabNext.setOnClickListener(v ->{
            pfvalue = bar.getProgress();
            DatabaseHelper dbHelper2 = new DatabaseHelper(this);
            SQLiteDatabase db2 = dbHelper2.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(FlowColumn.FlowEntry.COLUMN_FLOW, pfvalue);
            values.put(FlowColumn.FlowEntry.COLUMN_USER_ID, 1);

            db2.insert(FlowColumn.FlowEntry.TABLE_NAME, null,values);
            db2.close();

            //finish();
            Intent toAfter = new Intent(flowFragment.this, AfterFlow.class);
            startActivity(toAfter);
        });

    }


}
