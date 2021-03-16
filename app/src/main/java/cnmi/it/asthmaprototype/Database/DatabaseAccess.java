package cnmi.it.asthmaprototype.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cnmi.it.asthmaprototype.Models.PatientModel;

public class DatabaseAccess {
    private static DatabaseAccess instance;
    private final SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private PatientModel patientModel;
//    private SummaryModel sumModel;
//    private ArrayList<MedModel> medModelArrayList;


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

//    public ArrayList<MedModel> getDruglist(){
//        medModelArrayList = new ArrayList<>();
//
//        Cursor cursor = database.rawQuery("SELECT * FROM druglist WHERE id != 1", null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//           medModel = new MedModel(
//                   cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getInt(3)
//           );
//
//           medModelArrayList.add(medModel);
//           cursor.moveToNext();
//        }
//        cursor.close();
//        return medModelArrayList;
//    }
//
//    public List<String> getDrugName(){
//        List<String> drugname = new ArrayList<>();
//        Cursor cursor = database.rawQuery("SELECT drugname FROM druglist WHERE id != 1", null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            drugname.add(cursor.getString(0));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return drugname;
//    }
//
//    public List<String> getHerbname(){
//        List<String> Herbname = new ArrayList<>();
//        Cursor cursor = database.rawQuery("SELECT Herbname FROM dhi_clarification", null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            Herbname.add(cursor.getString(0));
//            cursor.moveToNext();
//        }
//        cursor.close();
//
//        return Herbname;
//    }
//
//    public ArrayList<SummaryModel> getQuery(String med, int herb){
//        ArrayList<SummaryModel> Queried = new ArrayList<>();
//
//
//        Cursor cursorSumm = database.rawQuery("SELECT DISTINCT "+med+" FROM dhi_summary WHERE id = "+herb+"",
//                null);
//        Cursor cursorClrf = database.rawQuery("SELECT DISTINCT "+med+" FROM dhi_clarification WHERE id = "+herb+"",
//                null);
//        Cursor cursorDoc = database.rawQuery("SELECT DISTINCT "+med+" FROM dhi_documentation WHERE id = "+herb+"",
//                null);
//        Cursor cursorRef  = database.rawQuery("SELECT DISTINCT "+med+" FROM dhi_references WHERE id = "+herb+"",
//                null);
//        Cursor cursorSvr = database.rawQuery("SELECT DISTINCT "+med+" FROM dhi_severity WHERE id = "+herb+"",
//                null);
//
//
//        cursorSumm.moveToFirst();
//        while (!cursorSumm.isAfterLast()){
//            sumModel = new SummaryModel(
//                    cursorSumm.getString(0)
//            );
//
//            Queried.add(sumModel);
//            cursorSumm.moveToNext();
//        }
//        cursorSumm.close();
//
//        cursorClrf.moveToFirst();
//        while (!cursorClrf.isAfterLast()){
//            sumModel = new SummaryModel(
//                    cursorClrf.getString(0)
//            );
//
//            Queried.add(sumModel);
//            cursorClrf.moveToNext();
//        }
//        cursorClrf.close();
//
//        cursorDoc.moveToFirst();
//        while (!cursorDoc.isAfterLast()){
//            sumModel = new SummaryModel(
//                    cursorDoc.getString(0)
//            );
//
//            Queried.add(sumModel);
//            cursorDoc.moveToNext();
//        }
//        cursorDoc.close();
//
//        cursorRef.moveToFirst();
//        while (!cursorRef.isAfterLast()){
//            sumModel = new SummaryModel(
//                    cursorRef.getString(0)
//            );
//
//            Queried.add(sumModel);
//            cursorRef.moveToNext();
//        }
//        cursorRef.close();
//
//        cursorSvr.moveToFirst();
//        while (!cursorSvr.isAfterLast()){
//            sumModel = new SummaryModel(
//                    cursorSvr.getString(0)
//            );
//
//            Queried.add(sumModel);
//            cursorSvr.moveToNext();
//        }
//        cursorSvr.close();
//
//        return Queried;
//    }

}

