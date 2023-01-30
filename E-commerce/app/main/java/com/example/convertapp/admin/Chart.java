package com.example.convertapp.admin;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.convertapp.R;
import com.example.convertapp.database.Carrelloshop;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Chart extends AppCompatActivity {
    BarChart barChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        barChart=findViewById(R.id.par);

        ArrayList<BarEntry> barEntries=new ArrayList<>();
        Carrelloshop carrelloshop=new Carrelloshop(this);
      Cursor c= carrelloshop.getuniqevalue();
        ArrayList<Integer> arrid = new ArrayList<Integer>();
        ArrayList<Integer> arrqut = new ArrayList<Integer>();

        while (!c.isAfterLast()){
          int pid=c.getInt(0);
          arrid.add(pid);
int r=carrelloshop.getsumqut(pid);

            arrqut.add(r);
          c.moveToNext();


         }



        for (int i=0;i<arrid.size();i++){



            BarEntry barEntry=new BarEntry(arrid.get(i),arrqut.get(i));

            barEntries.add(barEntry);


        }
        BarDataSet barDataSet=new BarDataSet(barEntries,"bestselling");
        barChart.setData(new BarData(barDataSet));
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setDrawValues(false);

        barChart.animateY(5000);
        barChart.getDescription().setText("product");


    }
}