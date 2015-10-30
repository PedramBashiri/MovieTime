package com.example.pedram.movietime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    AccessToken accessTOken;
    CallbackManager callbackManager;

    List<Movie> movies;
    List<Friend> friends;
    BufferedWriter out = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies=new ArrayList<Movie>();
        friends = new ArrayList<Friend>();

        final String[] PERMISSIONS = { "user_photos" };

        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                accessTOken = loginResult.getAccessToken();

                new GraphRequest(
                        accessTOken,
                        "/me/friends",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                FriendsJSONParser parser = new FriendsJSONParser(response.toString());
                                try {
                                    parser.parse(friends);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                for(final Friend friend:friends){
//            /* handle the result */
                                    new GraphRequest(
                                            accessTOken,
                                            "/" + friend.getId()+"/movies",
                                            null,
                                            HttpMethod.GET,
                                            new GraphRequest.Callback() {
                                                public void onCompleted(GraphResponse response) {
                                                    WatchesJSONParser parser = new WatchesJSONParser(response.toString());
                                                    try {
                                                        parser.parse(movies);
                                                    } catch (ParseException e) {
                                                        e.printStackTrace();
                                                    }
                                                    try {
                                                        File mediaDir = new File("/sdcard/download/media");
                                                        if (!mediaDir.exists()){
                                                            mediaDir.mkdir();
                                                        }

                                                        File resolveMeSDCard = new File("/sdcard/download/data.csv");
                                                        resolveMeSDCard.createNewFile();
                                                        FileOutputStream fos = new FileOutputStream(resolveMeSDCard);



                                                        for (Movie movie : movies) {
                                                            String string = friend.getId() + "," + movie.getMovieId() + "," + 1.0 + "\n";
                                                            fos.write(string.getBytes());
                                                        }
                                                        fos.close();
                                                    }catch (IOException e) {
                                                    // error processing code

                                                } finally {
                                                    if (out != null) {
                                                        try {
                                                            out.close();
                                                        } catch (IOException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                }
                                                }
                                            }
                                    ).executeAsync();
                                }
                            }
                        }
                ).executeAsync();


                Intent i = new Intent(MainActivity.this,MovieListActivity.class);
                startActivity(i);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.display) {
            return true;
        }else if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
