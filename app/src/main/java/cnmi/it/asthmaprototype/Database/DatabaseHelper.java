package cnmi.it.asthmaprototype.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cnmi.it.asthmaprototype.Models.FlowColumn;
import cnmi.it.asthmaprototype.Models.PatientColumn;

public class  DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "asthma_patient.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PatientColumn.UserEntry.TABLE_NAME + " (" +
                    PatientColumn.UserEntry._ID + " INTEGER PRIMARY KEY," +
                    PatientColumn.UserEntry.COLUMN_AGE + " INTEGER," +
                    PatientColumn.UserEntry.COLUMN_HEIGHT + " INTEGER," +
                    PatientColumn.UserEntry.COLUMN_WEIGHT + " TEXT, " +
                    PatientColumn.UserEntry.COLUMN_CONGENITAL+" TEXT, " +
                    PatientColumn.UserEntry.COLUMN_GENDER + " TEXT)";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + PatientColumn.UserEntry.TABLE_NAME;

    private static final String SQL_CREATE_FLOW = "CREATE TABLE " + FlowColumn.FlowEntry.TABLE_NAME + " (" +
            FlowColumn.FlowEntry._ID + " INTEGER PRIMARY KEY," +
            FlowColumn.FlowEntry.COLUMN_FLOW + " INTEGER," + FlowColumn.FlowEntry.COLUMN_USER_ID + " INTEGER)";
    private static final String SQL_DELETE_FLOW = "DROP TABLE IF EXISTS " + PatientColumn.UserEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_FLOW);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_FLOW);
        onCreate(db);
    }
}
