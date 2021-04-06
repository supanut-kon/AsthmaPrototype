package cnmi.it.asthmaprototype.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.card.MaterialCardView;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cnmi.it.asthmaprototype.Database.DatabaseHelper;
import cnmi.it.asthmaprototype.Models.InhalerColumn;
import cnmi.it.asthmaprototype.R;

public class AddInhalerActivity extends AppCompatActivity {

    MaterialCardView card1, card2;
    Date date;
    Calendar calendar = Calendar.getInstance();
    int[] resources = {R.drawable.flixotide_evohaler,
            R.drawable._0_aeronide,
            R.drawable.easyhaler_budesoniude,
            R.drawable.easyhaler_salbutamolsq,
            R.drawable.seretideevo,
            R.drawable.seretideaccuhaler,
            R.drawable.ventolin_inhaler};

    String[] resourcesname = {"flixotide", "aeronide", "budesonide", "salbutamol", "seretideevo", "seretideaccu", "ventolin"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inhaler);

        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM asthma_inhaler", null);


        int rowcount = c.getInt(0);
        if(rowcount > 0){
            for(int i=0;i<=resources.length;i++) {
                String timetext = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
                SQLiteDatabase writedb = helper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                String flixotide = getResources().getResourceName(resources[i]);
                cv.put(InhalerColumn.InhalerEntry.COLUMN_NAME, resourcesname[i]);
                cv.put(InhalerColumn.InhalerEntry.COLUMN_IMAGE, convertImage(R.drawable.flixotide_evohaler));
                cv.put(InhalerColumn.InhalerEntry.COLUMN_UPDATE_DATE, timetext);
            }
        }



//        card1 = findViewById(R.id.card1);
//        card2 = findViewById(R.id.card2);
//
//        card1.setOnClickListener(v ->{
//           card1.setChecked(!card1.isChecked());
//        });
//
//        card2.setOnClickListener(v ->{
//            card2.setChecked(!card2.isChecked());
//        });
    }

    public byte[] convertImage(int src){
        Bitmap image = BitmapFactory.decodeResource(getResources(), src);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        image.recycle();
        return bytes;
    }
}