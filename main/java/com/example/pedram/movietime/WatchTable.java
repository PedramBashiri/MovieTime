package com.example.pedram.movietime;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Michael on 5/2/2015.
 */
public class WatchTable {
    static final String TABLE_NAME = "toWatch";
    static final String WATCH_ID = "_id";
    static final String watch_USER = "user";
    static final String watch_MOVIE = "movie";
    static final String watch_MOVIENAME = "movieName";
    static final String watch_DATE = "date";
    static final String watch_TIME = "time";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + WatchTable.TABLE_NAME + " (");
        sb.append(WatchTable.WATCH_ID + " integer primary key autoincrement, ");
        sb.append(WatchTable.watch_USER + " text not null, ");
        sb.append(WatchTable.watch_MOVIE + " text not null, ");
        sb.append(WatchTable.watch_DATE + " date not null, ");
        sb.append(WatchTable.watch_TIME + " time not null, ");
        sb.append(WatchTable.watch_MOVIENAME + " text not null");
        sb.append(");");

        try{
            db.execSQL(sb.toString());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + WatchTable.TABLE_NAME);
        WatchTable.onCreate(db);
    }
}
