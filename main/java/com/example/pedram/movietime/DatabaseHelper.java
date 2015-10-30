package com.example.pedram.movietime;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Michael on 5/2/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "movie_watch.db";
    static final int DATABASE_VERSION = 1;

    DatabaseHelper(Context mContext){
        super(mContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        WatchTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        WatchTable.onUpgrade(db, oldVersion, newVersion);
    }
}
