package com.sashapps.gogo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shahar on 14/10/13.
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_COUNTRIES = "countries";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CONTINENT = "continent";
    public static final String COLUMN_COUNTRY = "country";
    private static final String DATABASE_NAME = "gogo.db";

    // Database creation sql statement
    private static final String DATABASE_CREATE =
            "create table " + TABLE_COUNTRIES + "(" +
                      COLUMN_ID        + " integer primary key autoincrement, " +
                      COLUMN_CONTINENT + " text not null, " +
                      COLUMN_COUNTRY   + " text not null);";

    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRIES);
        onCreate(db);
    }

    public ArrayList<String> returnCountriesByContinent(String continent){

        ArrayList<String> countries = new ArrayList<String>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT country FROM countries WHERE continent='"+continent+"';",null);
        c.moveToFirst();

        while (c.moveToNext()){
            String country = c.getString(c.getColumnIndex(COLUMN_COUNTRY));
            countries.add(country);
        }

        db.close();

        return countries;
    }

}
