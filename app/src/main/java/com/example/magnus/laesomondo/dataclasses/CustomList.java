package com.example.magnus.laesomondo.dataclasses;

/**
 * Created by Tobias on 17-01-2017.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.magnus.laesomondo.R;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    private final String[] subTxt;

    public CustomList(Activity context,
                      String[] web, Integer[] imageId,String [] subTxt) {
        super(context, R.layout.list_single, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
        this.subTxt = subTxt;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        TextView txtSub = (TextView) rowView.findViewById(R.id.subTxt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);
        txtSub.setText(subTxt[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}