package com.example.pedram.movietime;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    Context mContext;
    DatabaseHelper dbOpenHelper;
    SQLiteDatabase db;
    WatchDAO watchDao;

    public DataManager(Context mContext){
        this.mContext = mContext;
        dbOpenHelper = new DatabaseHelper(mContext);
        db = dbOpenHelper.getWritableDatabase();
        watchDao = new WatchDAO(db);
    }

    public void close(){
        db.close();
    }

    public long saveWatch(Watch watch){
        return watchDao.save(watch);
    }
    public boolean updateWatch(Watch watch){
        return watchDao.update(watch);
    }
    public boolean deleteWatch(Watch watch){
        return watchDao.delete(watch);
    }
    public Watch getWatch(long id){
        return watchDao.get(id);
    }
    public ArrayList<Watch> getAllWatch(){
        return watchDao.getAll();
    }
}
