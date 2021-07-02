package cnmi.it.asthmaprototype.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cnmi.it.asthmaprototype.Models.FlowColumn;
import cnmi.it.asthmaprototype.Models.InhalerColumn;
import cnmi.it.asthmaprototype.Models.PatientColumn;
import cnmi.it.asthmaprototype.Models.TransColumn;
import cnmi.it.asthmaprototype.Models.UserColumn;
import cnmi.it.asthmaprototype.Models.YellowPFColumn;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "asthma_patient.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PatientColumn.PatientEntry.TABLE_NAME + " (" +
                    PatientColumn.PatientEntry._ID + " INTEGER PRIMARY KEY," +
                    PatientColumn.PatientEntry.COLUMN_AGE + " INTEGER," +
                    PatientColumn.PatientEntry.COLUMN_HN + " TEXT," +
                    PatientColumn.PatientEntry.COLUMN_NAME + " TEXT," +
                    PatientColumn.PatientEntry.COLUMN_BD + " DATE," +
                    PatientColumn.PatientEntry.COLUMN_PEFR + " INTEGER," +
                    PatientColumn.PatientEntry.COLUMN_HEIGHT + " INTEGER," +
                    PatientColumn.PatientEntry.COLUMN_WEIGHT + " INTEGER, " +
                    PatientColumn.PatientEntry.COLUMN_CONGENITAL+" TEXT, " +
                    PatientColumn.PatientEntry.COLUMN_GENDER + " TEXT, "+
                    PatientColumn.PatientEntry.COLUMN_USER+ " INTEGER)";

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
            FlowColumn.FlowEntry.COLUMN_PATIENT_ID + " INTEGER," +
            FlowColumn.FlowEntry.COLUMN_TIME+ " TIME," +
            FlowColumn.FlowEntry.COLUMN_DATE+ " DATE)";

    private static final String SQL_CREATE_INHALER = "CREATE TABLE " + InhalerColumn.InhalerEntry.TABLE_NAME + " (" +
            InhalerColumn.InhalerEntry._ID + " INTEGER PRIMARY KEY," +
            InhalerColumn.InhalerEntry.COLUMN_DID + " INTEGER,"+
            InhalerColumn.InhalerEntry.COLUMN_NAME +" TEXT,"+
            InhalerColumn.InhalerEntry.COLUMN_TYPE+ " INTEGER,"+
            InhalerColumn.InhalerEntry.COLUMN_TIMES + " TEXT,"+
            InhalerColumn.InhalerEntry.COLUMN_INADAY + " TEXT,"+
            InhalerColumn.InhalerEntry.COLUMN_MORNING + " INTEGER,"+
            InhalerColumn.InhalerEntry.COLUMN_EVENING + " INTEGER,"+
            InhalerColumn.InhalerEntry.COLUMN_ISACTIVE+ " INTEGER)";

    private static final String SQL_CREATE_USER = "CREATE TABLE " + UserColumn.UserEntry.TABLE_NAME + " (" +
            UserColumn.UserEntry._ID + " INTEGER PRIMARY KEY," +
            UserColumn.UserEntry.NAME + " TEXT,"+
            UserColumn.UserEntry.EMAIL + " TEXT,"+
            UserColumn.UserEntry.PASSWORD + " TEXT,"+
            UserColumn.UserEntry.PASSCODE + " TEXT)";

    private static final String SQL_CREATE_YELLOWLOG = "CREATE TABLE " + YellowPFColumn.YellowPFEntry.TABLE_NAME + " (" +
            YellowPFColumn.YellowPFEntry._ID + " INTEGER PRIMARY KEY," +
            YellowPFColumn.YellowPFEntry.COLUMN_PEF + " INTEGER,"+
            YellowPFColumn.YellowPFEntry.COLUMN_ZONE + " TEXT,"+
            YellowPFColumn.YellowPFEntry.COLUMN_MAX + " INTEGER,"+
            YellowPFColumn.YellowPFEntry.COLUMN_80 + " INTEGER,"+
            YellowPFColumn.YellowPFEntry.COLUMN_60 + " INTEGER,"+
            YellowPFColumn.YellowPFEntry.COLUMN_PATIENTID + " INTEGER,"+
            YellowPFColumn.YellowPFEntry.COLUMN_DATE + " TEXT,"+
            YellowPFColumn.YellowPFEntry.COLUMN_TIME + " TEXT,"+
            YellowPFColumn.YellowPFEntry.COLUMN_ENDDATE + " TEXT)";



    private static final String SQL_CREATE_TEMP_INHALER = "CREATE TABLE tmp_inhaler (id INTEGER PRIMARY KEY, did INTEGER, name TEXT, times TEXT, inaday TEXT, type INT, isactive INT, morning INT, evening INT)";

    private static final String SQL_DELETE_INHALER = "DROP TABLE IF EXISTS " + InhalerColumn.InhalerEntry.TABLE_NAME;

    private static final String SQL_DELETE_FLOW = "DROP TABLE IF EXISTS " + FlowColumn.FlowEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_FLOW);
        db.execSQL(SQL_CREATE_INHALER);
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_TEMP_INHALER);
        db.execSQL(SQL_CREATE_YELLOWLOG);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_FLOW);
        db.execSQL(SQL_DELETE_INHALER);
        onCreate(db);
    }

    public void deletetmp(SQLiteDatabase db){
        db.execSQL("DELETE FROM asthma_inhaler");
    }

    public void deletetmprow(SQLiteDatabase db, int id){
        db.execSQL("DELETE FROM tmp_inhaler WHERE id =" + id);
    }
}
