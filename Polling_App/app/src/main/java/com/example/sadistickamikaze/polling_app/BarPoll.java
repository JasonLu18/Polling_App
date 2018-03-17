package com.example.sadistickamikaze.polling_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class BarPoll extends AppCompatActivity {
    BarChart barChart;
    public ArrayList<Integer> colors;
    public ArrayList<String> choices;
    public ArrayList<Long> votes;
    BarDataSet barDataSet;
    BarData data;
    List<BarEntry> entries;
    public BarPoll() {
        entries = new ArrayList<>();
        colors = new ArrayList<>();
        choices = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_poll);
        barChart = (BarChart) findViewById(R.id.chart);
        //barDataSet = new BarDataSet();

        //data = new BarData();








        //addDataset();

        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

              //  Log.d(TAG, "onValueSelected, Value Select from Chart");
              //  Log.d(TAG, "onvalueSelected: " + e.toString());
              //  Log.d(TAG, "onValueSelected: "+ h.toString());

               // Toast.makeText(PiePoll.this, "Response: " +answer+ "\n" + "Number of Votes: "  +h.getY() , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected() {

            }
        });
}


  private void addDataset(){
/*
        for (float i=0; i<choices.size(); i++){
            entries.add(new BarEntry());
        }
*/
  }


}
