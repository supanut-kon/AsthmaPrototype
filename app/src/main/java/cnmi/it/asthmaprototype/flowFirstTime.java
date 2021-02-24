package cnmi.it.asthmaprototype;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.common.util.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class flowFirstTime extends AppCompatActivity {

    Button savebtn;
    SeekBar bar;
    TextView barValueText;
    int barValue;
    Integer[] pfvalue;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flow_firsttime);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        savebtn = findViewById(R.id.flow_savebtnFT);
        bar = findViewById(R.id.seekBarFT);
        barValueText = findViewById(R.id.barvalueFT);

        pfvalue = new Integer[3];

        bar.setMax(900);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //int val = (progress *(seekBar.getWidth()- 2 *seekBar.getThumbOffset())) / seekBar.getMax();
                barValueText.setText(String.format("%s", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        savebtn.setOnClickListener(v ->{

            pfvalue[count] = bar.getProgress();
            count++;
            if(count == 3){
                int max = Collections.max(Arrays.asList(pfvalue));

                DatabaseHelper dbHelper = new DatabaseHelper(this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(FlowColumn.FlowEntry.COLUMN_FLOW, max);
                values.put(FlowColumn.FlowEntry.COLUMN_USER_ID, 1);

                db.insert(FlowColumn.FlowEntry.TABLE_NAME, null,values);
                db.close();

                finish();
            }
        });
    }

}
