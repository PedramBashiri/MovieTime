package com.example.pedram.movietime;

import java.io.Serializable;

/**
 * Created by Nitin1992 on 29-04-2015.
 */
public class Mov implements Serializable {
    String title,plot,ratings,img;

    @Override
    public String toString() {
        return "Mov{" +
                "title='" + title + '\'' +
                ", plot='" + plot + '\'' +
                ", ratings='" + ratings + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
