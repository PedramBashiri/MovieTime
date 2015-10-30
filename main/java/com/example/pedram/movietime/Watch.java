package com.example.pedram.movietime;

import java.io.Serializable;

public class Watch implements Serializable{
    private long _id;
    private String user;
    private String movieName;
    private long movieID;
    private String date;
    private String time;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getUser(){
        return user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getMovieName(){
        return movieName;
    }

    public void setMovieName(String movieName){
        this.movieName = movieName;
    }

    public long getMovieID(){
        return movieID;
    }

    public void setMovieID(long movieID){
        this.movieID = movieID;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String toString(){
        return "Watch [_id=" + _id + ", user=" + user + ", movieID=" + movieID + ", date=" + date + ", time=" + time + ", movieName=" + movieName +"]";
    }
}
