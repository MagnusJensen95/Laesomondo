package com.example.magnus.laesomondo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Magnus on 10-10-2016.
 */

public class DBHandler extends SQLiteOpenHelper{

    private static final String DBNAME = "userDatabase";
    private static final String TABLE_WPM = "wordsperminute";
    private static final int VERSION = 1;

    public static final String titel = "_textTitle";
    public static final String ord = "antalOrd";
    public static final String minutter = "minutter";



    public DBHandler(Context context) {
        super(context, DBNAME, null, VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_WPM + " ("
                + titel + " TEXT, "
                + ord + " INTEGER, " + minutter + " REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_WPM);
        this.onCreate(db);
    }

    public void addTestResult (String texttitel, int words, double minutes){

            SQLiteDatabase db = getWritableDatabase();

            ContentValues række = new ContentValues();
            række.put(titel, texttitel);
            række.put(ord, words);
            række.put(minutter, minutes);
            db.insert(TABLE_WPM, null, række);


            db.close();

        }

    public ArrayList<Result> getContent(){

        ArrayList<Result> results = new ArrayList<>();
        String[] kolonner = {titel, ord, minutter};
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(TABLE_WPM);


        Cursor cursor = builder.query(db, null, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Log.i("DEBUG", "Inside bruh");
                //Log.i("DEBUG", cursor.getString(0));
                results.add(new Result(cursor.getString(0), cursor.getInt(1), cursor.getDouble(2)));
                cursor.moveToNext();
            }
        }
        cursor.close();

        db.close();
        return  results;
    }
}
