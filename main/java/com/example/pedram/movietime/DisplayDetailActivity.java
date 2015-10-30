package com.example.pedram.movietime;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class DisplayDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_detail);
        ListView listView = (ListView) findViewById(R.id.listView2);
        DataManager dm = new DataManager(DisplayDetailActivity.this);
        final ArrayList<Watch> watched = dm.getAllWatch();
        if(watched.size()>0) {
            DisplayAdapter adapter = new DisplayAdapter(DisplayDetailActivity.this, watched);
            adapter.setNotifyOnChange(true);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(DisplayDetailActivity.this,
                            LikeDislikeActivity.class);
                    intent.putExtra("mov", watched.get(position));
                    startActivity(intent);
                    finish();

                }
            });
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            finish();
            return true;
        }else if (id == R.id.home) {
            Intent i = new Intent(DisplayDetailActivity.this,MovieListActivity.class);
            startActivity(i);
            finish();
            return true;
        }else if (id == R.id.msg) {
            Intent i = new Intent(DisplayDetailActivity.this,ContactActivity.class);
            startActivity(i);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
