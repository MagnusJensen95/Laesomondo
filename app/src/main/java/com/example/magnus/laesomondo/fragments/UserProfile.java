package com.example.magnus.laesomondo.fragments;

import android.app.Fragment;
import android.opengl.GLDebugHelper;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.magnus.laesomondo.R;
import com.example.magnus.laesomondo.dataclasses.DBHandler;
import com.example.magnus.laesomondo.dataclasses.GraphController;
import com.example.magnus.laesomondo.dataclasses.Result;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class UserProfile extends Fragment {

    TextView resultater;
    GraphView graph;
    GraphController gc;
    DBHandler db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_user_profile, container, false);


        db = new DBHandler(getActivity());

        graph = (GraphView) v.findViewById(R.id.progressGraph);
        ArrayList<Result> data = db.getContent();
        gc = new GraphController(graph);
        if(data.size() > 0) {
            DataPoint[] points = new DataPoint[data.size()];
            for (int i = 0; i < data.size(); i++) {
                points[i] = (new DataPoint(i, data.get(i).getRatio()));


            }
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);


            graph.getGridLabelRenderer().setHorizontalAxisTitle("Tekst");
            graph.getGridLabelRenderer().setVerticalAxisTitle("WPM");
            graph.addSeries(series);
        }







        Log.i("DEBUG ARRAY LENGTH: ", ""+data.size());

        String titelsInDB = "";


        //resultater.setText(titelsInDB);


        return v;
    }


}
