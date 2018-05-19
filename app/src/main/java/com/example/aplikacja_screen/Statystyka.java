package com.example.aplikacja_screen;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Statystyka extends AppCompatActivity {

    TableLayout tableLayout;
    Database db;
    ArrayList<Integer> Data;
    int poprawne=0;
    int bledne=0;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statystyka);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        db = new Database(getContentResolver());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userID = prefs.getString("id", "0");


        Cursor cursor=db.getLessons(Integer.parseInt(userID));
        while(cursor.moveToNext()){
            String id=cursor.getString(1);
            if(userID.equals(id))
            {
                int ilosc_poprawnych = cursor.getInt(3);
                poprawne+=ilosc_poprawnych;
            }

        }

        Cursor cursor1=db.getLessons(Integer.parseInt(userID));
        while(cursor1.moveToNext()){
            String id=cursor1.getString(1);
            if(userID.equals(id))
            {
                int ilosc_blednych = cursor1.getInt(4);
                bledne += ilosc_blednych;
            }
        }
        Data = new ArrayList<>();
        Data.add(bledne);
        Data.add(poprawne);

        pieChart = (PieChart) findViewById(R.id.IdPieChart);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(40);
        pieChart.setTransparentCircleAlpha(10);
        pieChart.setDrawEntryLabels(true);
        pieChart.getDescription().setText("");
        addDataSet(pieChart);


        //moze jakis scroll view dorobic
        TableRow row = new TableRow(this);
        TextView kategoria = new TextView(this);
        kategoria.setTypeface(null, Typeface.BOLD);
        kategoria.setTextSize(15);
        kategoria.setTextColor(Color.parseColor("#FF991C1E"));
        kategoria.setText(" Kategoria ");
        row.addView(kategoria);
        TextView typ_zadania = new TextView(this);
        typ_zadania.setTypeface(null, Typeface.BOLD);
        typ_zadania.setTextSize(15);
        typ_zadania.setTextColor(Color.parseColor("#FF991C1E"));
        typ_zadania.setText(" Typ ");
        row.addView(typ_zadania);
        TextView poprawna = new TextView(this);
        poprawna.setTypeface(null, Typeface.BOLD);
        poprawna.setTextSize(15);
        poprawna.setTextColor(Color.parseColor("#FF991C1E"));
        poprawna.setText(" Poprawne ");
        row.addView(poprawna);
        TextView błędna = new TextView(this);
        błędna.setTypeface(null, Typeface.BOLD);
        błędna.setTextSize(15);
        błędna.setTextColor(Color.parseColor("#FF991C1E"));
        błędna.setText(" Błędne ");
        row.addView(błędna);
        TextView data = new TextView(this);
        data.setTypeface(null, Typeface.BOLD);
        data.setTextSize(15);
        data.setTextColor(Color.parseColor("#FF991C1E"));
        data.setText(" Data ");
        row.addView(data);

        tableLayout.addView(row);

        for (int i = 0; i < 5; i++) {
            TableRow row1 = new TableRow(this);
            TextView kategoria1 = new TextView(this);
            kategoria1.setText(" Dom ");  //jakoś po tym i trzeba będzie się z bazy odwoływać do konkretnych elementów
            kategoria1.setGravity(Gravity.CENTER);
            row1.addView(kategoria1);
            TextView typ_zadania1 = new TextView(this);
            typ_zadania1.setText(" Dopasowanie ");
            typ_zadania1.setGravity(Gravity.CENTER);
            row1.addView(typ_zadania1);
            TextView poprawna1 = new TextView(this);
            poprawna1.setText(" 10 ");
            poprawna1.setGravity(Gravity.CENTER);
            row1.addView(poprawna1);
            TextView błędna1 = new TextView(this);
            błędna1.setText(" 5 ");
            błędna1.setGravity(Gravity.CENTER);
            row1.addView(błędna1);
            tableLayout.addView(row1);
        }
    }

    private void addDataSet(PieChart pieChart) {
        ArrayList<PieEntry> yEntry = new ArrayList<>();
        yEntry.add(new PieEntry(Data.get(0),"Błędne"));
        yEntry.add(new PieEntry(Data.get(1),"Poprawne"));

        PieDataSet pieDataSet = new PieDataSet(yEntry,"");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(15);

        ArrayList<Integer> kolor = new ArrayList<>();
        kolor.add(Color.argb(200,250,20,20));
        kolor.add(Color.argb(200,91,230,80));

        pieDataSet.setColors(kolor);
        Legend leg = pieChart.getLegend();
        leg.setForm(Legend.LegendForm.SQUARE);
        leg.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}