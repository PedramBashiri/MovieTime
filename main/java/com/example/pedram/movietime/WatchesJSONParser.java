package com.example.pedram.movietime;

import java.util.List;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class WatchesJSONParser {

private String json;

    public WatchesJSONParser(){
    }

	public WatchesJSONParser(String toParse) {
		json = toParse;
	}

	public void parse(List<Movie> movies) throws ParseException {

		JSONParser parser = new JSONParser();

		Object obj = parser.parse(json);
		JSONObject jsonObject = (JSONObject) obj;

		JSONArray rootArray = (JSONArray) jsonObject.get("data");

		for (int i = 0; i < rootArray.size(); i++) {

			JSONObject temp = (JSONObject) rootArray.get(i);
			JSONObject likes = (JSONObject) temp.get("data");
			JSONObject movie = (JSONObject) likes.get("movie");

			String movieName = (String) movie.get("title");

			if (movieName.matches("[a-zA-Z0-9 ':,.?]+")){
				Movie newMovie = new Movie(movieName,Long.parseLong((String) temp.get("id")),10202474253004793L);
				movies.add(newMovie);
			}
			else {
			}
		}
	}
	
	public void parse(List<Movie> movies, org.json.JSONObject jsonObject) throws ParseException, JSONException {

		JSONParser parser = new JSONParser();

		JSONArray rootArray = (JSONArray) jsonObject.get("data");

		for (int i = 0; i < rootArray.size(); i++) {

			JSONObject temp = (JSONObject) rootArray.get(i);
			JSONObject likes = (JSONObject) temp.get("data");
			JSONObject movie = (JSONObject) likes.get("movie");
			
			String movieName = (String) movie.get("title");
			
			if (movieName.matches("[a-zA-Z0-9 ':,.?]+")){
				Movie newMovie = new Movie(movieName,Long.parseLong((String) temp.get("id")),10202474253004793L);
				movies.add(newMovie);
			}
			else {
			}
		}
	}

	
}
