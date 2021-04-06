package cnmi.it.asthmaprototype.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cnmi.it.asthmaprototype.Models.FlowColumn;
import cnmi.it.asthmaprototype.Models.InhalerColumn;
import cnmi.it.asthmaprototype.Models.PatientColumn;

public class  DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "asthma_patient.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PatientColumn.PatientEntry.TABLE_NAME + " (" +
                    PatientColumn.PatientEntry._ID + " INTEGER PRIMARY KEY," +
                    PatientColumn.PatientEntry.COLUMN_AGE + " INTEGER," +
                    PatientColumn.PatientEntry.COLUMN_HN + " TEXT," +
                    PatientColumn.PatientEntry.COLUMN_NAME + " TEXT," +
                    PatientColumn.PatientEntry.COLUMN_HEIGHT + " INTEGER," +
                    PatientColumn.PatientEntry.COLUMN_WEIGHT + " TEXT, " +
                    PatientColumn.PatientEntry.COLUMN_CONGENITAL+" TEXT, " +
                    PatientColumn.PatientEntry.COLUMN_GENDER + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + PatientColumn.PatientEntry.TABLE_NAME;

    private static final String SQL_CREATE_FLOW = "CREATE TABLE " + FlowColumn.FlowEntry.TABLE_NAME + " (" +
            FlowColumn.FlowEntry._ID + " INTEGER PRIMARY KEY," +
            FlowColumn.FlowEntry.COLUMN_FLOW + " INTEGER," +
            FlowColumn.FlowEntry.COLUMN_ZONE + " TEXT,"+
            FlowColumn.FlowEntry.COLUMN_MAX + " INTEGER," +
            FlowColumn.FlowEntry.COLUMN_80 + " INTEGER," +
            FlowColumn.FlowEntry.COLUMN_60 + " INTEGER," +
            FlowColumn.FlowEntry.COLUMN_HAVE_SYMPTOM + " BOOLEAN," +
            FlowColumn.FlowEntry.COLUMN_SYMPTOMS + " TEXT," +
            FlowColumn.FlowEntry.COLUMN_CAREMETHOD + " TEXT,"+
            FlowColumn.FlowEntry.COLUMN_USER_ID + " INTEGER," +
            FlowColumn.FlowEntry.COLUMN_TIME+ " TIME," +
            FlowColumn.FlowEntry.COLUMN_DATE+ " DATE)";

    private static final String SQL_CREATE_INHALER = "CREATE TABLE " + InhalerColumn.InhalerEntry.TABLE_NAME + " (" +
            InhalerColumn.InhalerEntry._ID + " INTEGER PRIMARY KEY," +
            InhalerColumn.InhalerEntry.COLUMN_NAME + " TEXT,"+
            InhalerColumn.InhalerEntry.COLUMN_IMAGE+ " BLOB,"+
            InhalerColumn.InhalerEntry.COLUMN_UPDATE_DATE+ " DATE)";

    private static final String SQL_DELETE_INHALER = "DROP TABLE IF EXISTS " + InhalerColumn.InhalerEntry.TABLE_NAME;

    private static final String SQL_DELETE_FLOW = "DROP TABLE IF EXISTS " + FlowColumn.FlowEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_FLOW);
        db.execSQL(SQL_CREATE_INHALER);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_FLOW);
        db.execSQL(SQL_DELETE_INHALER);
        onCreate(db);
    }
}
