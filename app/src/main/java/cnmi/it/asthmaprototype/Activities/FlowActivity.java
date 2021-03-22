package cnmi.it.asthmaprototype.Activities;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.FlowColumn;
import cnmi.it.asthmaprototype.R;

import static android.media.CamcorderProfile.get;

public class FlowActivity extends AppCompatActivity {
    TextView barValue, greenpef,yellowpef, redpef;
    SeekBar bar;
    int pfvalue, id, age, height, yellowvalue, redvalue;
    String gender;
    double max;
    ChipGroup periodchip;
    EditText date;
    FloatingActionButton fabNext;
    Chip morning, evening;
    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //savebtn = findViewById(R.id.flow_savebtn);
        bar = findViewById(R.id.seekBar);
        barValue = findViewById(R.id.barvalue);
        greenpef = findViewById(R.id.greenpeftext);
        yellowpef = findViewById(R.id.yellowpef);
        redpef = findViewById(R.id.redpef);
        periodchip = findViewById(R.id.periodchips);
        date = findViewById(R.id.flowdate);
        fabNext = findViewById(R.id.fabNextBtn);
        morning = findViewById(R.id.morningchip);
        evening = findViewById(R.id.eveningchip);
        morning.setText("ช่วงเช้า");
        evening.setText("ช่วงเย็น");

        DatePickerDialog.OnDateSetListener setdate = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };
        String dateFormat = "d MMMM y";
        SimpleDateFormat df = new SimpleDateFormat(dateFormat, new Locale("th", "TH"));
        Date today = new Date();
        date.setText(df.format(today));
        date.setInputType(InputType.TYPE_NULL);
        date.setOnClickListener(v -> new DatePickerDialog(FlowActivity.this, setdate, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show());

        checkentry();

    }

    public void updateLabel() {
        String myFormat = "d MMMM y";
        SimpleDateFormat df = new SimpleDateFormat(myFormat, new Locale("th", "TH"));

        date.setText(df.format(calendar.getTime()));
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
            gender = c.getString(5);

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

        if (gender != null) {
            if (gender.equals("ชาย")) {
                max = 319.13 - (4.75 * height) + 0.035 * Math.pow(height, 2);
            } else {
                max = -487.12 + (7 * height) - 0.0085 * Math.pow(height, 2);
            }


            yellowvalue = (int) (max * (80.00 / 100.00));

            redvalue = (int) (max * (60.00 / 100.00));

            bar.setMax((int) ((int) max +(max *(15.00/100.00))));
            greenpef.setText(String.valueOf((int) max));
            yellowpef.setText(String.valueOf((int) yellowvalue));
            redpef.setText(String.valueOf((int) redvalue));
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
                barValue.setText(String.valueOf(progress));
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
//            DatabaseHelper dbHelper2 = new DatabaseHelper(this);
//            SQLiteDatabase db2 = dbHelper2.getWritableDatabase();
//
//
//            ContentValues values = new ContentValues();
//            values.put(FlowColumn.FlowEntry.COLUMN_FLOW, pfvalue);
//            values.put(FlowColumn.FlowEntry.COLUMN_MAX, peakflow);
//            values.put(FlowColumn.FlowEntry.COLUMN_80, yellowvalue);
//            values.put(FlowColumn.FlowEntry.COLUMN_60, redvalue);
//            values.put(FlowColumn.FlowEntry.COLUMN_USER_ID, 1);
//            values.put(FlowColumn.FlowEntry.COLUMN_DATE, datetext);
//            values.put(FlowColumn.FlowEntry.COLUMN_TIME, timetext);


//            db2.insert(FlowColumn.FlowEntry.TABLE_NAME, null, values);
//            db2.close();
            if (pfvalue < redvalue) {
                finish();
                String datetext = date.getText().toString();
                String timetext = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
                DatabaseHelper dbHelper2 = new DatabaseHelper(this);
                SQLiteDatabase db2 = dbHelper2.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(FlowColumn.FlowEntry.COLUMN_FLOW, pfvalue);
                values.put(FlowColumn.FlowEntry.COLUMN_ZONE, "red");
                values.put(FlowColumn.FlowEntry.COLUMN_MAX, max);
                values.put(FlowColumn.FlowEntry.COLUMN_80, yellowvalue);
                values.put(FlowColumn.FlowEntry.COLUMN_60, redvalue);
                values.put(FlowColumn.FlowEntry.COLUMN_USER_ID, 1);
                values.put(FlowColumn.FlowEntry.COLUMN_DATE, datetext);
                values.put(FlowColumn.FlowEntry.COLUMN_TIME, timetext);
                db2.insert(FlowColumn.FlowEntry.TABLE_NAME, null, values);
                db2.close();
                Intent toRed = new Intent(FlowActivity.this, RedWarning.class);

                startActivity(toRed);
            } else {
                finish();
                Intent toAfter = new Intent(FlowActivity.this, AfterFlow.class);
                String datetext = date.getText().toString();

                toAfter.putExtra("pfvalue", pfvalue);
                toAfter.putExtra("max", max);
                toAfter.putExtra("yellow", yellowvalue);
                toAfter.putExtra("red", redvalue);
                toAfter.putExtra("id", 1);
                toAfter.putExtra("date", datetext);

                startActivity(toAfter);
            }
        });

    }



}
