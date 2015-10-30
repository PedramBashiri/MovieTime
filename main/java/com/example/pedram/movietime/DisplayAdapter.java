package com.example.pedram.movietime;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Nitin1992 on 03-05-2015.
 */
public class DisplayAdapter  extends ArrayAdapter<Watch> {
    Context context;
    ArrayList<Watch> objects;


    public DisplayAdapter(Context context, ArrayList<Watch> watched) {
        super(context, R.layout.activity_detail_row, watched);
        this.context = context;
        this.objects = watched;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_detail_row, parent,false);
            holder = new ViewHolder();
            holder.title1 = (TextView) convertView.findViewById(R.id.textView4);
            holder.dateTime = (TextView) convertView.findViewById(R.id.textView5);
            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        Watch story = objects.get(position);
        Log.d("asd",holder.title1.toString());
        holder.title1.setText(story.getMovieName());
        holder.dateTime.setText(story.getDate() + " " + story.getTime());
        return convertView;
    }

    static class ViewHolder {
        TextView title1;
        TextView dateTime;

    }



}
