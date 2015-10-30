package com.example.pedram.movietime;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class SaveActivity extends Activity implements View.OnClickListener {
    DatePicker dt;
    Calendar cal = Calendar.getInstance();
    TimePicker tp;
    Button btnDate, btnTime, btnReserve;
    EditText txtDate, txtTime;
    TextView txtMovieName;

    private int mYear, mMonth, mDay, mHour, mMinute;
    String movieName, userID, movieID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        final Mov app;
        Intent i = getIntent();
        app = (Mov) i.getSerializableExtra("app");
        movieName = app.getTitle();
        movieID = "12340";
        userID="12345";

        btnDate = (Button) findViewById(R.id.btnDate);
        btnTime = (Button) findViewById(R.id.btnTime);
        btnReserve = (Button) findViewById(R.id.btnReserve);
        txtDate = (EditText) findViewById(R.id.txtDate);
        txtTime = (EditText) findViewById(R.id.txtTime);
        txtMovieName = (TextView) findViewById(R.id.txtMovieName);

        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnReserve.setOnClickListener(this);
        txtMovieName.setText(movieName);


    }

    public void onClick(View v) {

        if (v == btnDate) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, mYear, mMonth, mDay);
            dpd.show();
        }

        if (v == btnTime)
        {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener(){

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            String period = "AM";
                            if (hourOfDay >= 12){
                                period = "PM";
                                hourOfDay = (hourOfDay == 12) ? hourOfDay : (hourOfDay - 12);
                            }
                            txtTime.setText(hourOfDay + ":" + minute + " " + period);
                        }
                    }, mHour, mMinute, false);
            tpd.show();
        }

        if (v == btnReserve)
        {
            if (txtDate.equals("") || txtDate.equals("Date")|| txtTime.equals("") || txtDate.equals("Time") ) {
                Toast toast = Toast.makeText(this, "Enter Valid Input", Toast.LENGTH_LONG);
                toast.show();
            }
                else{
                String message = "Scheduled a watch time for " + movieName + " and user: " + userID;
                Toast toast1 = Toast.makeText(this, message, Toast.LENGTH_LONG);
                toast1.show();
                SaveTime();
                Intent i = new Intent(SaveActivity.this, MovieListActivity.class);
                startActivity(i);
                finish();
            }
        }
    }
    //  private int mYear, mMonth, mDay, mHour, mMinute;
    public void SaveTime(){ //STORES THE MOVIE WATCH TIME IN THE LOCAL SQLITE DB
        DataManager dm = new DataManager(this);
        Watch watch = new Watch();
        watch.setUser(userID);
        watch.setMovieName(movieName);
        watch.setMovieID(Long.parseLong(movieID));
        watch.setDate(mDay + "/" + (mMonth + 1) + "/" + mYear);
        watch.setTime(mHour + ":" + mMinute);
        dm.saveWatch(watch);

        dm.close();
        super.onDestroy();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save, menu);
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
