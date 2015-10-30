package com.example.pedram.movietime;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Nitin1992 on 29-04-2015.
 */
public class JSONParser {

    public Mov ParseJson (String toParse) throws JSONException, MalformedURLException {

        Mov photos = new Mov();

        JSONObject root = new JSONObject(toParse);

            photos.setTitle(root.getString("Title"));
            photos.setPlot(root.getString("Plot"));
            photos.setImg(root.getString("Poster"));
            photos.setRatings(root.getString("imdbRating"));
            Log.d("Parser", photos.toString());
        return photos;
    }
}
