package cnmi.it.asthmaprototype;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "asthma_patient.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserColumn.UserEntry.TABLE_NAME + " (" +
                    UserColumn.UserEntry._ID + " INTEGER PRIMARY KEY," +
                    UserColumn.UserEntry.COLUMN_AGE + " INTEGER," +
                    UserColumn.UserEntry.COLUMN_HEIGHT + " INTEGER," + UserColumn.UserEntry.COLUMN_GENDER + " TEXT)";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + UserColumn.UserEntry.TABLE_NAME;

    private static final String SQL_CREATE_FLOW = "CREATE TABLE " + FlowColumn.FlowEntry.TABLE_NAME + " (" +
            FlowColumn.FlowEntry._ID + " INTEGER PRIMARY KEY," +
            FlowColumn.FlowEntry.COLUMN_FLOW + " INTEGER," + FlowColumn.FlowEntry.COLUMN_USER_ID + " INTEGER)";
    private static final String SQL_DELETE_FLOW = "DROP TABLE IF EXISTS " + UserColumn.UserEntry.TABLE_NAME;

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
