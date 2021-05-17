package cnmi.it.asthmaprototype.Database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import cnmi.it.asthmaprototype.Models.FlowModel;
import cnmi.it.asthmaprototype.Models.PatientModel;
import cnmi.it.asthmaprototype.Models.UserModel;

public class DatabaseAccess {
    private static DatabaseAccess instance;
    private final SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private FlowModel flows;
    private ArrayList<FlowModel> flowsArraylist;
    private ArrayList<UserModel> userArraylist;
    private ArrayList<PatientModel> patientArraylist;
    private UserModel theuser;
    private PatientModel patients;


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

    public ArrayList<UserModel> getUserInfoAfterLogin(String userEmail){
        userArraylist = new ArrayList<>();

        Cursor c = database.rawQuery("SELECT * FROM asthma_user WHERE email = " + userEmail, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            theuser = new UserModel(c.getInt(0),c.getString(1), c.getString(2), c.getString(3));
            userArraylist.add(theuser);
            c.moveToNext();
        }
        c.close();
        return userArraylist;
    }
    
    public ArrayList<UserModel> getUser(int id){
        userArraylist = new ArrayList<>();

        Cursor c = database.rawQuery("SELECT * FROM asthma_user WHERE id = " + id, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            theuser = new UserModel(c.getInt(0),c.getString(1), c.getString(2), c.getString(3));
            userArraylist.add(theuser);
            c.moveToNext();
        }
        c.close();
        return userArraylist;
    }

    public ArrayList<PatientModel> getPatient(int currentUserId){
        patientArraylist = new ArrayList<>();

        Cursor c = database.rawQuery("SELECT * FROM asthma_patient WHERE userid = " + currentUserId, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            patients = new PatientModel(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getString(4), c.getInt(5),c.getInt(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10), c.getInt(11));
            patientArraylist.add(patients);
            c.moveToNext();
        }
        c.close();
        return patientArraylist;
    }


}