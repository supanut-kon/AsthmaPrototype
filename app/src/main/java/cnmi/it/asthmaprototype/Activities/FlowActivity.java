package cnmi.it.asthmaprototype.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.FlowColumn;
import cnmi.it.asthmaprototype.R;

public class FlowActivity extends AppCompatActivity {
    TextView barValue;
    TextView greenpef;
    TextView yellowpef;
    TextView redpef;
    SeekBar bar;
    int pfvalue;
    int id;
    int age;
    int height;
    String gender;
    double peakflow;
    ChipGroup periodchip;
    EditText date;
    FloatingActionButton fabNext;
    int yellowvalue, redvalue;
    Chip morning, evening;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //savebtn = findViewById(R.id.flow_savebtn);
        bar = findViewById(R.id.seekBar);
        barValue = findViewById(R.id.barvalue);
        greenpef = findViewById(R.id.greenpef);
        yellowpef = findViewById(R.id.yellowpef);
        redpef = findViewById(R.id.redpef);
        periodchip = findViewById(R.id.periodchips);
        date = findViewById(R.id.flowdate);
        fabNext = findViewById(R.id.fabNextBtn);
        morning = findViewById(R.id.morningchip);
        evening = findViewById(R.id.eveningchip);

        morning.setText("ช่วงเช้า");
        evening.setText("ช่วงเย็น");


        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        date.setText(df.format(new Date()));
        checkentry();

    }

    public void checkentry() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor f = db.rawQuery("SELECT * FROM asthma_flow", null); // WHERE Userid = currentUser

        if (f.getCount() < 0) {
            finish();
            Intent toConfig = new Intent(FlowActivity.this, PatientConfig.class);
            startActivity(toConfig);
        } else {
            flowMeasure();
        }
        f.close();
    }

    public void flowMeasure() {
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

        if (gender != null) {
            if (gender.equals("ชาย")) {
                peakflow = 319.13 - (4.75 * height) + 0.035 * Math.pow(height, 2);
            } else {
                peakflow = -487.12 + (7 * height) - 0.0085 * Math.pow(height, 2);
            }


            yellowvalue = (int) (peakflow * (80.00 / 100.00));

            redvalue = (int) (peakflow * (60.00 / 100.00));

            bar.setMax((int) peakflow);
            greenpef.setText(df.format((int) peakflow));
            yellowpef.setText(df.format((int) yellowvalue));
            redpef.setText(df.format((int) redvalue));
        } else {
            bar.setMax(900);
            greenpef.setText(String.valueOf(900));
            yellowpef.setText(String.valueOf(900 * (80.00 / 100.00)));
            redpef.setText(String.valueOf(900 * (60.00 / 100.00)));
        }
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

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

        fabNext.setOnClickListener(v -> {
            pfvalue = bar.getProgress();
            DatabaseHelper dbHelper2 = new DatabaseHelper(this);
            SQLiteDatabase db2 = dbHelper2.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(FlowColumn.FlowEntry.COLUMN_FLOW, pfvalue);
            values.put(FlowColumn.FlowEntry.COLUMN_USER_ID, 1);

            db2.insert(FlowColumn.FlowEntry.TABLE_NAME, null, values);
            db2.close();
            if (pfvalue < redvalue) {
                finish();
                startActivity(new Intent(FlowActivity.this, RedWarning.class));
            } else {
                finish();
                Intent toAfter = new Intent(FlowActivity.this, AfterFlow.class);
                startActivity(toAfter);
            }
        });

    }


}
