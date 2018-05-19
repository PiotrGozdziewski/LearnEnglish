package com.example.aplikacja_screen;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
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

    ////////
    int ID_cat;
    int ID_type;
    String Cat_name;
    String Type_name;

    public class Lekcja
    {
        String typ;
        String kategoria;
        Integer poprawne;
        Integer bledne;
        public Lekcja(String typ, String kat, Integer pop, Integer bl){
            this.typ = typ;
            this.kategoria = kat;
            this.poprawne = pop;
            this.bledne = bl;
        }
    }
    ArrayList<Lekcja> lekcja = new ArrayList<>();

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

        Cursor cursor3 = db.getLessons(Integer.parseInt(userID));
        while(cursor3.moveToNext()) {

            String id = cursor3.getString(1);
            if (userID.equals(id)) {
                int zad = cursor3.getInt(2);
                int popr = cursor3.getInt(3);
                int bl = cursor3.getInt(4);
                String data = cursor3.getString(5);
                Cursor cursor4=db.getIDCategorie(zad);
                while(cursor4.moveToNext())
                {
                    ID_cat = cursor4.getInt(1);
                }
                Cursor cursor5=db.getIDCategorie(zad);
                while(cursor5.moveToNext())
                {
                    ID_type = cursor5.getInt(2);
                }
                Cursor cursor6=db.getCategorieName(ID_cat);
                while(cursor6.moveToNext())
                {
                    Cat_name = cursor6.getString(1);
                }
                Cursor cursor7=db.getExerciceTypeName(ID_type);
                while(cursor7.moveToNext())
                {
                    Type_name = cursor7.getString(1);
                }

                Lekcja l = new Lekcja(Type_name,Cat_name,popr,bl);
                lekcja.add(l);
            }
        }


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
        kategoria.setTextSize(17);
        kategoria.setTextColor(Color.parseColor("#FF991C1E"));
        kategoria.setText(" Kategoria ");
        kategoria.setGravity(Gravity.CENTER);
        row.addView(kategoria);
        TextView typ_zadania = new TextView(this);
        typ_zadania.setTypeface(null, Typeface.BOLD);
        typ_zadania.setTextSize(17);
        typ_zadania.setTextColor(Color.parseColor("#FF991C1E"));
        typ_zadania.setText(" Typ ");
        typ_zadania.setGravity(Gravity.CENTER);
        row.addView(typ_zadania);
        TextView poprawna = new TextView(this);
        poprawna.setTypeface(null, Typeface.BOLD);
        poprawna.setTextSize(17);
        poprawna.setTextColor(Color.parseColor("#FF991C1E"));
        poprawna.setText(" Poprawne ");
        poprawna.setGravity(Gravity.CENTER);
        row.addView(poprawna);
        TextView błędna = new TextView(this);
        błędna.setTypeface(null, Typeface.BOLD);
        błędna.setTextSize(17);
        błędna.setTextColor(Color.parseColor("#FF991C1E"));
        błędna.setText(" Błędne ");
        błędna.setGravity(Gravity.CENTER);
        row.addView(błędna);

        tableLayout.addView(row);

        for (int i = lekcja.size()-1; i > lekcja.size()-6; i--) {
            TableRow row1 = new TableRow(this);
            TextView kategoria1 = new TextView(this);
            kategoria1.setText(String.valueOf(lekcja.get(i).kategoria));
            kategoria1.setGravity(Gravity.CENTER);
            kategoria1.setTextSize(15);
            row1.addView(kategoria1);
            TextView typ_zadania1 = new TextView(this);
            typ_zadania1.setText(String.valueOf(lekcja.get(i).typ));
            typ_zadania1.setGravity(Gravity.CENTER);
            typ_zadania1.setTextSize(15);
            row1.addView(typ_zadania1);
            TextView poprawna1 = new TextView(this);
            poprawna1.setText(String.valueOf(lekcja.get(i).poprawne));
            poprawna1.setGravity(Gravity.CENTER);
            poprawna1.setTextSize(15);
            row1.addView(poprawna1);
            TextView błędna1 = new TextView(this);
            błędna1.setText(String.valueOf(lekcja.get(i).bledne));
            błędna1.setGravity(Gravity.CENTER);
            błędna1.setTextSize(15);
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