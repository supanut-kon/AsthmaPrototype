package cnmi.it.asthmaprototype.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import cnmi.it.asthmaprototype.Models.FlowModel;
import cnmi.it.asthmaprototype.Models.PatientModel;

public class DatabaseAccess {
    private static DatabaseAccess instance;
    private final SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private PatientModel patientModel;
    private FlowModel flows;
    private ArrayList<FlowModel> flowsArraylist;


    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public ArrayList<FlowModel> getFlow(){
        flowsArraylist = new ArrayList<>();

        Cursor c = database.rawQuery("SELECT * FROM asthma_flow", null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            flows = new FlowModel(c.getInt(0),c.getInt(1),c.getString(2),c.getInt(3),c.getInt(4),c.getInt(5),c.getInt(6),c.getString(7),c.getString(8), c.getString(9), new String[]{c.getString(10)},c.getString(11));
            flowsArraylist.add(flows);
            c.moveToNext();
        }
        c.close();
        return flowsArraylist;
    }
}