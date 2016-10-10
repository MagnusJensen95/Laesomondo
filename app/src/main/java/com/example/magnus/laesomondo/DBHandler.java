package com.example.magnus.laesomondo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        public void addTestResult (String titel, int words, double minutes){

            SQLiteDatabase db = getWritableDatabase();

            ContentValues række = new ContentValues();
            række.put(this.titel, titel);
            række.put(this.ord, words);
            række.put(this.minutter, minutes);
            db.insert(this.TABLE_WPM, null, række);




        }
}
