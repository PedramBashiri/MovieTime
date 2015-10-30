package com.example.pedram.movietime;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;


public class LikeDislikeActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_dislike);
        Parse.initialize(this,"j3UDGQDbuAyc2vfGIRfJggC7WD21hDJi2Y4zfg2P","g3jQvhUTqtpxe1uvE0CLOFBn7vHHy6OEkmg05FHL");
        Intent i = getIntent();
        final Watch mov;
        mov = (Watch) i.getSerializableExtra("mov");
        TextView title = (TextView) findViewById(R.id.textView6);
        final TextView title1 = (TextView) findViewById(R.id.textView7);
        TextView dateTime = (TextView) findViewById(R.id.textView8);
        TextView dateTime1 = (TextView) findViewById(R.id.textView9);
        ImageButton b1 = (ImageButton) findViewById(R.id.imageButton);
        ImageButton b2 = (ImageButton) findViewById(R.id.imageButton2);
        title.setText("Title");
        title1.setText(mov.getMovieName());
        dateTime.setText("Date and Time");
        dateTime1.setText(mov.getDate()+" "+mov.getTime());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager dm = new DataManager(LikeDislikeActivity.this);
                dm.deleteWatch(mov);
                Intent i = new Intent(LikeDislikeActivity.this,DisplayDetailActivity.class);
                startActivity(i);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject p = new ParseObject("MovieDetails");
                p.put("MovieId", title1.getText());
                p.put("UserID", "1234");
                p.put("Ratings", "1");
                p.saveInBackground();
                DataManager dm = new DataManager(LikeDislikeActivity.this);
                dm.deleteWatch(mov);
                Intent i = new Intent(LikeDislikeActivity.this,DisplayDetailActivity.class);
                startActivity(i);
                finish();
            }
        });
    }



}
