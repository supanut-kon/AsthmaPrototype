package cnmi.it.asthmaprototype.Activities;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cnmi.it.asthmaprototype.Database.DatabaseAccess;
import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.InhalerModel;
import cnmi.it.asthmaprototype.Models.YellowPFModel;
import cnmi.it.asthmaprototype.R;

public class GreenWarning extends AppCompatActivity {

    FloatingActionButton fab, help;
    TextView greentext, greendesc, greeninhalertext;
    ImageView greenImage;
    ArrayList<YellowPFModel> yellows;
    int id = 0;
    ArrayList<InhalerModel> inhalers;
    int[] resources = {R.drawable.flixotide_evohaler,
            R.drawable._0_aeronide,
            R.drawable.easyhaler_budesoniude,
            R.drawable.easyhaler_salbutamolsq,
            R.drawable.seretideevo,
            R.drawable.seretideaccuhaler,
            R.drawable.ventolin_inhaler};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_warning);

        greentext = findViewById(R.id.RedTextview);
        greendesc = findViewById(R.id.GreenDesc);
        greeninhalertext = findViewById(R.id.greeninhalerText);
        greenImage = findViewById(R.id.GreenImage);
        inhalers = new ArrayList<>();
        //greendesc.setText("ทำกิจวัตรประจำวันได้ดี ไม่มีอาการไอและเหนื่อยหอบ");
        DatabaseAccess db = DatabaseAccess.getInstance(this);
        db.open();
        inhalers = db.getInhaler(1);
        db.close();
        greenImage.setImageResource(resources[inhalers.get(0).getDid()]);

        greentext.setText("- พ่นยาที่ใช้เป็นประจำ แม้ไม่มีอาการ\n\n- ก่อนออกกำลังกาย 15 นาที อาจใช้ยาขยายหลอดลมฉุกเฉิน ครั้งละ 2 สูด");

        greeninhalertext.setText(String.format("%s\n\nครั้งละ %s สูด วันละ %s ครั้ง", inhalers.get(0).getName(), inhalers.get(0).getTimes(), inhalers.get(0).getInaday()));

        fab = findViewById(R.id.floatingActionButton);
        help = findViewById(R.id.inhalerHelp);

        fab.setOnClickListener(v -> finish());
    }

    public void getandcheckYellows(){
        DatabaseAccess db = DatabaseAccess.getInstance(this);
        db.open();
        yellows = db.getYellow(1);
        db.close();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyyy");
        Date idate, jdate;


        for(int i=0; i < yellows.size(); i++){
            for(int j =1; j < yellows.size(); j++){
                 String idatestring = yellows.get(i).getEnddate();
                 String jdatestring = yellows.get(j).getEnddate();
                 try {
                     idate = format.parse(idatestring);
                     jdate = format.parse(jdatestring);
                 } catch (ParseException e){
                     e.printStackTrace();
                 }


            }
        }



    }
}