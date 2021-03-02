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

import java.util.Arrays;
import java.util.Collections;

public class flowFragment extends AppCompatActivity {
    Button savebtn;
    TextView barValue;
    TextView greenpef;
    TextView yellowpef;
    TextView redpef;
    SeekBar bar;
    Integer[] pfvalue;
    int count;
    int id;
    int age;
    int height;
    String gender;
    long flow;
    double peakflow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flow_fragment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        savebtn = findViewById(R.id.flow_savebtn);
        bar = findViewById(R.id.seekBar);
        barValue = findViewById(R.id.barvalue);
        pfvalue = new Integer[3];
        greenpef = findViewById(R.id.greenpef);
        yellowpef = findViewById(R.id.yellowpef);
        redpef = findViewById(R.id.redpef);



        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor f = db.rawQuery("SELECT * FROM asthma_flow", null); // WHERE Userid = currentUser
        if(f == null){
            finish();
            Intent toConfig = new Intent(flowFragment.this, PatientConfig.class);
            startActivity(toConfig);
        }else{
            flowMeasure();
        }

    }
    public void flowMeasure(){
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM asthma_patient", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {

            id = c.getInt(0);
            age = c.getInt(1);
            height = c.getInt(2);
            gender = c.getString(3);

            c.moveToNext();
        }
        c.close();

        if(gender.equals("ชาย")){
            peakflow = 319.13-(4.75*age)+0.035*Math.pow(age,2);
        }else {
            peakflow = -487.12+(7*age)-0.0085*Math.pow(age,2);
        }

        bar.setMax((int) peakflow);
        greenpef.setText(String.valueOf(peakflow));
        yellowpef.setText(String.valueOf(peakflow*(80.00/100.00)));
        redpef.setText(String.valueOf(peakflow*(60.00/100.00)));

        //bar.setMax(900);
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

        savebtn.setOnClickListener(v -> {
            pfvalue[count] = bar.getProgress();
            count++;
            if(count == 3){
                int max = Collections.max(Arrays.asList(pfvalue));

                DatabaseHelper dbHelper2 = new DatabaseHelper(this);
                SQLiteDatabase db2 = dbHelper2.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(FlowColumn.FlowEntry.COLUMN_FLOW, max);
                values.put(FlowColumn.FlowEntry.COLUMN_USER_ID, 1);

                db2.insert(FlowColumn.FlowEntry.TABLE_NAME, null,values);
                db2.close();

                finish();
            }
        });
    }




}
