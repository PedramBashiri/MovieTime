package com.example.pedram.movietime;

import java.util.List;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class FriendsJSONParser {

    private String json;

    public FriendsJSONParser(){
    }

    public FriendsJSONParser(String toParse) {
        json = toParse;
    }

    public void parse(List<Friend> friends) throws ParseException{
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(json);
        JSONObject jsonObject = (JSONObject) obj;

        JSONArray rootArray = (JSONArray) jsonObject.get("data");

        for (int i = 0; i < rootArray.size(); i++) {

            JSONObject temp = (JSONObject) rootArray.get(i);

            String Name = (String) temp.get("name");
            long Id = (Long) temp.get("id");

            Friend newFriend = new Friend(Name,Id);
            friends.add(newFriend);
        }
    }
    public void parse(List<Friend> friends, org.json.JSONObject jsonObject) throws ParseException, JSONException {

		JSONParser parser = new JSONParser();

		JSONArray rootArray = (JSONArray) jsonObject.get("data");

		for (int i = 0; i < rootArray.size(); i++) {

			JSONObject temp = (JSONObject) rootArray.get(i);
			
			String Name = (String) temp.get("name");
			long Id = (Long) temp.get("id");
			
			Friend newFriend = new Friend(Name,Id);
			friends.add(newFriend);
		}
	}
}
