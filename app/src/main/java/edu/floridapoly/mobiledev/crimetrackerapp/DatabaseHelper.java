package edu.floridapoly.mobiledev.crimetrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "crimeTrackDB";
    private static String tableOne= "activity";
    private static String tableTwo = "activityClassifcation";


    // Create table SQL query
    public static final String createActivityTable = // Creates table for holding activities
            "CREATE TABLE IF NOT EXISTS " + tableOne + "("
                    + "crimeId" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "activityN" + " TEXT,"
                    + "latitude" + " REAL,"
                    + "longitude" + " REAL,"
                    + "activityD" + " TEXT,"
                    + "activityS" + " TEXT,"
                    + "activityClass" + " INTEGER,"
                    + " FOREIGN KEY (activityClass) REFERENCES "+tableTwo+"(activityId)"
                    + ")";

    public static final String createClassificationTable =  //create
            "CREATE TABLE IF NOT EXISTS " + tableTwo + "("
            + "activityId" + "INTEGER PRIMARY KEY, "
            + " activity " + " TEXT "
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(createActivityTable);
        db.execSQL(createClassificationTable);
    }



    public long insertActivity(String name, double latitude, double longitude, String date, int classification, String summary) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put("activityN", name);
        values.put("latitude", latitude);
        values.put("longitude",longitude);
        values.put("activityClass", classification);
        values.put("activityS",summary);
        values.put("activityD",date);


        // insert row
        long id = db.insert(tableOne, null,values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public crimeActivity getActivity(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tableOne,
                new String[]{"crimeId", "activityN","latitude","longitude","activityDate","activityClass","activityS"},
                "crimeId" + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        crimeActivity activity = new crimeActivity(
                cursor.getString(cursor.getColumnIndex("activityN")),
                cursor.getString(cursor.getColumnIndex("activityS")),
                cursor.getString(cursor.getColumnIndex("activityClass")),
                cursor.getDouble(cursor.getColumnIndex("longitude")),
                cursor.getDouble(cursor.getColumnIndex("latitude")),
                cursor.getString(cursor.getColumnIndex("activityD")),
                cursor.getInt(cursor.getColumnIndex("crimeId"))

                );


        // close the db connection
        cursor.close();

        return activity;
    }



    public int numActivities() {
        String countQuery = "SELECT  * FROM " + tableOne;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + tableOne);

        // Create tables again
        onCreate(db);
    }

    public int insertClassification(String classification){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("activity",classification);

        int id = (int)db.insert(tableTwo, null,values);
        db.close();

        return id;
    }

    public ParcelThing getAll(){


        ParcelThing test = new ParcelThing();

        ArrayList<crimeActivity> databae = new ArrayList<crimeActivity>();

        String countQuery = "SELECT  * FROM " + tableOne;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        cursor.moveToFirst();
        Log.d("datas",String.valueOf(cursor.getCount()));


        while(!cursor.isAfterLast()){

                 //  crimeActivity c = new crimeActivity(cursor.getString(cursor.getColumnIndex("activityN")),cursor.getInt(cursor.getColumnIndex("longitude")),cursor.getInt(cursor.getColumnIndex("latitude")));
                  test.add(new crimeActivity(cursor.getString(cursor.getColumnIndex("activityN")),cursor.getDouble(cursor.getColumnIndex("longitude")),cursor.getDouble(cursor.getColumnIndex("latitude")), cursor.getString(cursor.getColumnIndex("activityClass"))));


            cursor.moveToNext();
        }

        db.close();



        return test;

    }





}
