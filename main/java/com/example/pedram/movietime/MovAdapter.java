package com.example.pedram.movietime;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Nitin1992 on 29-04-2015.
 */
public class MovAdapter extends ArrayAdapter<Mov> {
    Context context;
    ArrayList<Mov> objects;

    public MovAdapter(Context context, ArrayList<Mov> objects) {
        super(context, R.layout.activity_list, objects);
        this.context = context;
        this.objects = objects;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_list, parent,false);
            holder = new ViewHolder();
            holder.tileTxt = (TextView) convertView.findViewById(R.id.textView1);
            holder.poster = (ImageView) convertView.findViewById(R.id.imageView1);
            holder.plotTxt = (TextView) convertView.findViewById(R.id.textView2);
            holder.rateTxt= (TextView) convertView.findViewById(R.id.textView3);
            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        Mov story = objects.get(position);
        holder.tileTxt.setText(story.getTitle());
        Picasso.with(context).load(story.getImg()).into(holder.poster);
        holder.plotTxt.setText("Plot: " + story.getPlot());
        holder.rateTxt.setText("IMDB Rateing: " + story.getRatings());

        return convertView;
    }

    static class ViewHolder {
        TextView tileTxt;
        ImageView poster;
        TextView plotTxt;
        TextView rateTxt;

    }

}
