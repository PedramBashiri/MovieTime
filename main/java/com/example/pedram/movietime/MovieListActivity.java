package com.example.pedram.movietime;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.apache.mahout.cf.taste.common.TasteException;
import org.json.JSONException;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MovieListActivity extends ActionBarActivity {
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    private static ProgressDialog PD = null;
    ArrayList<Mov> movies = new ArrayList<>();
    List<String> movieNames ;
    String[] mov_rec = {"Sin City","Batman Returns","Don","Iron Man","The Dark Knight"};
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        AccessToken token = AccessToken.getCurrentAccessToken();

        Recommender recommender = new Recommender();
        movieNames = new ArrayList<String> ();
        List<Long> movieIds = new ArrayList<Long> ();
//        try {
//            movieIds = recommender.recomm();
//        } catch (TasteException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        for(Long id : movieIds) {
            new GraphRequest(token
                    ,
                    "/" + id.toString(),
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
                            try {
                                movieNames.add(response.getJSONObject().get("name").toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            ).executeAsync();
        }
        setContentView(R.layout.activity_movie_list);
        listView = (ListView) findViewById(R.id.listView);

 //       String[] array = (String[]) movieNames.toArray();
        new downloadFile().execute(mov_rec);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MovieListActivity.this,
                        SaveActivity.class);
                intent.putExtra("app", movies.get(position));
                startActivity(intent);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_list, menu);
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
            Intent i = new Intent(MovieListActivity.this,DisplayDetailActivity.class);
            startActivity(i);
            finish();
            return true;
        }else if (id == R.id.action_logout) {
            finish();
            return true;
        }else if (id == R.id.msg) {
            Intent i = new Intent(MovieListActivity.this,ContactActivity.class);
            startActivity(i);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class downloadFile extends AsyncTask<String,String,ArrayList<Mov>>
    {
        @Override
        protected ArrayList<Mov> doInBackground(String... params) {
            URL request = null;
            for (String a : params) {
                try {
                    a= a.replaceAll(" ","+");
                    request = new URL("http://www.omdbapi.com/?t=" + a + "&y=&plot=short&r=json");
                    HttpURLConnection con = (HttpURLConnection) request
                            .openConnection();
                    con.setRequestMethod("GET");
                    con.connect();
                    Log.d("demo", "connected");
                    int statusCode = con.getResponseCode();
                    if (statusCode == HttpURLConnection.HTTP_OK) {
                       InputStream in = con.getInputStream();
                        BufferedReader BF = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        StringBuilder Str = new StringBuilder();
                        String line = BF.readLine();

                        while (line != null) {
                            Str.append(line);
                            line = BF.readLine();
                        }

                        JSONParser parser = new JSONParser();
                        Mov photos = parser.ParseJson(Str.toString());
                        movies.add(photos);
                       // return photos;


                    }
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Log.d("Sax", e.toString());
                    e.printStackTrace();
                }
            publishProgress(a);
            }
            return movies;
           // return null;
        }

        @Override
        protected void onPreExecute() {
            Log.d("demo", "in preexecute");
            PD = new ProgressDialog(MovieListActivity.this);
            PD.setMessage("Loading Results ...");
            PD.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            PD.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<Mov> myApps) {
            MovAdapter adapter = new MovAdapter(MovieListActivity.this, movies);
            adapter.setNotifyOnChange(true);
            listView.setAdapter(adapter);
            PD.dismiss();
//            Log.d("movie", myApps.toString());
//            movies.add(myApps);
            super.onPostExecute(myApps);
        }

        @Override
        protected void onProgressUpdate(String... values) {

            super.onProgressUpdate(values);
        }
    }
}
