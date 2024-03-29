package cnmi.it.asthmaprototype.Database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import cnmi.it.asthmaprototype.Models.FlowModel;
import cnmi.it.asthmaprototype.Models.InhalerModel;
import cnmi.it.asthmaprototype.Models.PatientModel;
import cnmi.it.asthmaprototype.Models.UserModel;
import cnmi.it.asthmaprototype.Models.YellowPFModel;

public class DatabaseAccess {
    private static DatabaseAccess instance;
    private final SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private FlowModel flows;
    private ArrayList<FlowModel> flowsArraylist;
    private ArrayList<UserModel> userArraylist;
    private ArrayList<PatientModel> patientArraylist;
    private ArrayList<InhalerModel> addInhaler, getInhalers;
    private ArrayList<YellowPFModel> yellows;
    private YellowPFModel yellowPFModel;
    private InhalerModel inhalers;
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

    
    public ArrayList<UserModel> getUser(){
        userArraylist = new ArrayList<>();

        Cursor c = database.rawQuery("SELECT * FROM asthma_user", null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            theuser = new UserModel(c.getInt(0),c.getString(1), c.getString(2), c.getString(3), c.getString(4));
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
            patients = new PatientModel(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getString(4), c.getInt(5),c.getInt(6), c.getString(7), c.getString(8), c.getString(9), c.getInt(10));
            patientArraylist.add(patients);
            c.moveToNext();
        }
        c.close();
        return patientArraylist;
    }

    public ArrayList<InhalerModel> getAddInhaler(){
        addInhaler = new ArrayList<>();

        Cursor c = database.rawQuery("SELECT * FROM asthma_inhaler",null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            inhalers = new InhalerModel(c.getInt(0), c.getInt(1), c.getString(2),c.getString(3), c.getInt(4), c.getString(5), c.getString(6), c.getInt(7), c.getInt(8), c.getInt(9));
            addInhaler.add(inhalers);
            c.moveToNext();
        }
        c.close();
        return addInhaler;
    }

    public ArrayList<InhalerModel> getInhaler(int type){
        getInhalers = new ArrayList<>();
        Cursor c = database.rawQuery("SELECT * FROM asthma_inhaler WHERE type = " + type, null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            inhalers = new InhalerModel(c.getInt(0), c.getInt(1), c.getString(2),c.getString(3), c.getInt(4), c.getString(5), c.getString(6), c.getInt(7), c.getInt(8), c.getInt(9));
            getInhalers.add(inhalers);
            c.moveToNext();
        }
        c.close();
        return getInhalers;
    }

    public ArrayList<YellowPFModel> getYellow(int isactive){
        yellows = new ArrayList<>();
        Cursor c = database.rawQuery("SELECT * FROM yellow_log WHERE is_active =" + isactive + "ORDER BY id DESC LIMIT 1", null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            yellowPFModel = new YellowPFModel(c.getInt(0), c.getInt(1), c.getString(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6), c.getString(7), c.getString(8), c.getString(9), c.getInt(10));
            yellows.add(yellowPFModel);
        }
        return yellows;
    }

}