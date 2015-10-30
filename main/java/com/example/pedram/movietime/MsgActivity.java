package com.example.pedram.movietime;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MsgActivity extends ActionBarActivity {
    @Override
    public void onBackPressed() {
        Intent i = new Intent(MsgActivity.this,ContactActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        ArrayList<String> names = getIntent().getStringArrayListExtra("Names");
        final ArrayList<String> numbs = getIntent().getStringArrayListExtra("Numbers");

//        Bundle b = getIntent().getExtras();
//        String[] resultArr = b.getStringArray("selectedItems");

        TextView phoneTxt = (TextView) findViewById(R.id.phoneText);
        final TextView phoneEdit = (TextView) findViewById(R.id.phoneEdit);
        TextView msgTxt = (TextView) findViewById(R.id.msgText);
        final TextView msgEdit = (EditText) findViewById(R.id.msgEdit);
        Button btnSend = (Button) findViewById(R.id.btnSend);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        phoneEdit.setText(names.toString());
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( msgEdit.getText().toString().equals("")) {
                    Toast t = Toast.makeText(MsgActivity.this, "Enter Message", Toast.LENGTH_LONG);
                    t.show();


                }else {
                    SmsManager smsManager = SmsManager.getDefault();
                    for (int i = 0; i < numbs.size(); i++) {
                        if (numbs.get(i).toString().contains("+1") && numbs.get(i).toString().length() == 12) {
                            smsManager.sendTextMessage(numbs.get(i).toString(), null,
                                    msgEdit.getText().toString(), null, null);
                        } else if (numbs.get(i).toString().length() == 10)
                            smsManager.sendTextMessage("+1" + numbs.get(i).toString(), null,
                                    msgEdit.getText().toString(), null, null);
                    }
                    Intent i = new Intent(MsgActivity.this, MovieListActivity.class);
                    startActivity(i);
                    finish();
                }
            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MsgActivity.this,MovieListActivity.class);
                startActivity(i);
                finish();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_msg, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
