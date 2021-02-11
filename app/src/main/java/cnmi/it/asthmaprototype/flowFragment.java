package cnmi.it.asthmaprototype;

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

public class flowFragment extends AppCompatActivity {
        Button savebtn;
        TextView barValue;
        SeekBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flow_fragment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        savebtn = findViewById(R.id.flow_savebtn);
        bar = findViewById(R.id.seekBar);
        barValue = findViewById(R.id.barvalue);

        savebtn.setOnClickListener(v -> finish());

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


    }


}
