package com.example.pedram.movietime;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactActivity extends ActionBarActivity {
    @Override
    public void onBackPressed() {
        Intent i = new Intent(ContactActivity.this,MovieListActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }

    Button button,btnSearch;
    ListView listView;
    ArrayAdapter<String> adapter;
    EditText search;
    String output = new String();
    ArrayList<String> sports= new ArrayList<String>();
    ArrayList<String> namesList = new ArrayList<String>();
    ArrayList<String> numList = new ArrayList<String>();
    ArrayList<String> nam = new ArrayList<String>();
    ArrayList<String> num = new ArrayList<String>();
    String sear = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        findViewsById();
        fetchContacts();
//        if (getIntent() != null) {
//            sear = getIntent().getExtras().getString("search1");
//        }

        //	String[] sports = getResources().getStringArray(R.array.sports_array);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, sports);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checked = listView.getCheckedItemPositions();
                ArrayList<String> selectedItems = new ArrayList<String>();
                for (int i = 0; i < checked.size(); i++) {
                    // Item position in adapter
                    int position = checked.keyAt(i);
                    // Add sport if it is checked i.e.) == TRUE!
                    if (checked.valueAt(i))
                        nam.add(namesList.get(position));
                    num.add(numList.get(position));
                    //selectedItems.add(adapter.getItem(position));
                }
                //String[] outputStrArr = new String[selectedItems.size()];

//        for (int i = 0; i < selectedItems.size(); i++) {
//            //nam.add()
//            outputStrArr[i] = selectedItems.get(i);
//        }

                Intent intent = new Intent(getApplicationContext(),
                        MsgActivity.class);

                // Create a bundle object
//        Bundle b = new Bundle();
//        b.putStringArray("selectedItems", outputStrArr);

                // Add the bundle to the intent.
                intent.putStringArrayListExtra("Names",nam);
                intent.putStringArrayListExtra("Numbers",num);

                // start the ResultActivity
                startActivity(intent);
                finish();
            }
        });

    }


    public void fetchContacts() {
        String phoneNumber = null;
        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null,
                null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String contact_id = cursor
                        .getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor
                        .getColumnIndex(DISPLAY_NAME));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor
                        .getColumnIndex(HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    //output.append("\n First Name:" + name);
                    Cursor phoneCursor = contentResolver.query(
                            PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?",
                            new String[] { contact_id }, null);
                    if (name.contains(sear)) {
                        while (phoneCursor.moveToNext()) {
                            phoneNumber = phoneCursor.getString(phoneCursor
                                    .getColumnIndex(NUMBER));
                            output =  name + "\n" + phoneNumber;
                        }
                        namesList.add(name);
                        numList.add(phoneNumber);
                        sports.add(output.toString());
                    }
                    //output = null;
                    phoneCursor.close();

                }
            }
        }
    }
    private void findViewsById() {
        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.testbutton);

    }
}
