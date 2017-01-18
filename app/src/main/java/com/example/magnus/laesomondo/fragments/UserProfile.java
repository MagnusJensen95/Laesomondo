package com.example.magnus.laesomondo.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.magnus.laesomondo.R;
import com.example.magnus.laesomondo.dataclasses.CustomList;
import com.example.magnus.laesomondo.dataclasses.DBHandler;
import com.example.magnus.laesomondo.dataclasses.GraphController;
import com.example.magnus.laesomondo.dataclasses.Result;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends Fragment {

    TextView resultater;
    GraphView graph;
    GraphController gc;
    DBHandler db;
    ListView listView;
    String[] text = {"Historie","Krimi","Mad","Natur","Sport","Tilfældig"};
    String[] subText = {"Lix : 40","Lix : 42","Lix : 30","Lix : 50","Lix : 20","Lix : 38"};
    Integer[] imageId = {R.drawable.his,R.drawable.krimi,R.drawable.mad,R.drawable.natur,R.drawable.sport,R.drawable.random};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);


        db = new DBHandler(getActivity());

        graph = (GraphView) v.findViewById(R.id.progressGraph);

        listView = (ListView) v.findViewById(R.id.listView);
        CustomList adapter = new CustomList(getActivity(),text,imageId,subText);

        listView.setAdapter(adapter);
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
            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(points);
            BarGraphSeries<DataPoint> avgSeries = new BarGraphSeries<>(avgPoints);
            avgSeries.setColor(Color.GREEN);

            graph.getGridLabelRenderer().setHorizontalAxisTitle("Tekst");
            graph.getGridLabelRenderer().setNumHorizontalLabels(10);
            //graph.getGridLabelRenderer().setVerticalAxisTitle("ratio");
            //series.setSpacing(50);
            //avgSeries.setSpacing(50);
            graph.addSeries(series);
            graph.addSeries(avgSeries);

        }



        Log.i("DEBUG ARRAY LENGTH: ", ""+data.size());

        String titelsInDB = "";


        //resultater.setText(titelsInDB);

        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK)
                {
                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                                    R.animator.exit_to_right, R.animator.enter_from_right )
                            .replace(R.id.container_main,
                                    new MainMenuUser()).addToBackStack(null).commit();
                    return true;
                }
                return false;
            }
        });
        return v;
    }


}
