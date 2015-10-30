package com.example.pedram.movietime;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class WatchDAO {
    private SQLiteDatabase db;
    public WatchDAO(SQLiteDatabase db){
        this.db = db;
    }

    public long save(Watch watch){
        ContentValues values = new ContentValues();
        values.put(WatchTable.watch_DATE, watch.getDate());
        values.put(WatchTable.watch_MOVIE, watch.getMovieID());
        values.put(WatchTable.watch_TIME, watch.getTime());
        values.put(WatchTable.watch_USER, watch.getUser());
        values.put(WatchTable.watch_MOVIENAME, watch.getMovieName());
        return db.insert(WatchTable.TABLE_NAME, null, values);
    }

    public boolean update(Watch watch){
        ContentValues values = new ContentValues();
        values.put(WatchTable.watch_DATE, watch.getDate());
        values.put(WatchTable.watch_MOVIE, watch.getMovieID());
        values.put(WatchTable.watch_TIME, watch.getTime());
        values.put(WatchTable.watch_USER, watch.getUser());
        values.put(WatchTable.watch_MOVIENAME, watch.getMovieName());
        return db.update(WatchTable.TABLE_NAME, values, WatchTable.WATCH_ID+"="+ watch.get_id(), null) > 0;
    }

    public boolean delete(Watch watch){
        return db.delete(WatchTable.TABLE_NAME, WatchTable.WATCH_ID + "="+watch.get_id(), null) > 0;
    }

    public Watch get(long id){
        Watch watch = null;
        Cursor c = db.query(true, WatchTable.TABLE_NAME,
                new String[]{WatchTable.WATCH_ID, WatchTable.watch_USER, WatchTable.watch_MOVIE, WatchTable.watch_DATE, WatchTable.watch_TIME, WatchTable.watch_MOVIENAME},
                WatchTable.WATCH_ID+"="+ id, null, null, null, null, null);
        if(c != null){
            c.moveToFirst();
            watch = this.buildWatchFromCursor(c);
        }
        if(!c.isClosed()){
            c.close();
        }
        return watch;
    }
    public ArrayList<Watch> getAll(){
        ArrayList<Watch> list = new ArrayList<Watch>();
        Cursor c = db.query(WatchTable.TABLE_NAME,
                new String[]{WatchTable.WATCH_ID, WatchTable.watch_USER, WatchTable.watch_MOVIE, WatchTable.watch_DATE, WatchTable.watch_TIME, WatchTable.watch_MOVIENAME},
                null, null, null, null, null);
        if(c != null){
            c.moveToFirst();
            do{
                Watch watch = this.buildWatchFromCursor(c);
                if(watch != null){
                    list.add(watch);
                }
            } while (c.moveToNext());
            if(!c.isClosed()){
                c.close();
            }
        }
        return list;
    }

    private Watch buildWatchFromCursor(Cursor c){
        Watch watch = null;
        if(c != null){
            watch = new Watch();
            watch.set_id(c.getLong(0));
            watch.setUser(c.getString(1));
            watch.setMovieID(c.getLong(2));
            watch.setDate(c.getString(3));
            watch.setTime(c.getString(4));
            watch.setMovieName(c.getString(5));
        }
        return watch;
    }

}
