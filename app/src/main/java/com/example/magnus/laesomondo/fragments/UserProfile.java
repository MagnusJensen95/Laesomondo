package com.example.magnus.laesomondo.fragments;

import android.app.Fragment;
import android.graphics.Color;
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
import com.jjoe64.graphview.series.BarGraphSeries;
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

       // graph.getViewport().setScrollable(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setScalable(true);


        ArrayList<Result> data = db.getContent();
        if (data.size() > 10){
            graph.getViewport().setMinX(data.size()-10);
            graph.getViewport().setMaxX(data.size());
        }
        else {

            graph.getViewport().setMinX(0);
            graph.getViewport().setMaxX(data.size());
        }
            graph.getViewport().setMinY(0);
            graph.getViewport().setMaxY(6);


        gc = new GraphController(graph);
        if(data.size() > 0) {
            DataPoint[] points = new DataPoint[data.size()];
            DataPoint[] avgPoints = new DataPoint[data.size()];
            for (int i = 0; i < data.size(); i++) {
                points[i] = new DataPoint(i, data.get(i).getRatio());
                avgPoints[i] = new DataPoint(i, 1);


            }
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
            LineGraphSeries<DataPoint> avgSeries = new LineGraphSeries<>(avgPoints);
            avgSeries.setColor(Color.GREEN);


           // graph.getGridLabelRenderer().setHorizontalAxisTitle("Tekst");
            graph.getGridLabelRenderer().setNumHorizontalLabels(10);
            //graph.getGridLabelRenderer().setVerticalAxisTitle("WPM");
            //series.setSpacing(50);
            //avgSeries.setSpacing(50);
            graph.addSeries(series);
            graph.addSeries(avgSeries);

        }







        Log.i("DEBUG ARRAY LENGTH: ", ""+data.size());

        String titelsInDB = "";


        //resultater.setText(titelsInDB);


        return v;
    }


}
