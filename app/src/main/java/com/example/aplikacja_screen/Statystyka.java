package com.example.aplikacja_screen;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.m.aplikacja_screen.R;

public class Statystyka extends AppCompatActivity {

    TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statystyka);
        tableLayout=(TableLayout)findViewById(R.id.tableLayout);

        //moze jakis scroll view dorobic
        TableRow row = new TableRow(this);
        TextView kategoria = new TextView(this);
        kategoria.setTypeface(null, Typeface.BOLD);
        kategoria.setTextSize(20);
        kategoria.setTextColor(Color.parseColor("#FF991C1E"));
        kategoria.setText(" Kategoria ");
        row.addView(kategoria);
        TextView typ_zadania = new TextView(this);
        typ_zadania.setTypeface(null, Typeface.BOLD);
        typ_zadania.setTextSize(20);
        typ_zadania.setTextColor(Color.parseColor("#FF991C1E"));
        typ_zadania.setText(" Typ ");
        row.addView(typ_zadania);
        TextView poprawna = new TextView(this);
        poprawna.setTypeface(null, Typeface.BOLD);
        poprawna.setTextSize(20);
        poprawna.setTextColor(Color.parseColor("#FF991C1E"));
        poprawna.setText(" Poprawne ");
        row.addView(poprawna);
        TextView błędna = new TextView(this);
        błędna.setTypeface(null, Typeface.BOLD);
        błędna.setTextSize(20);
        błędna.setTextColor(Color.parseColor("#FF991C1E"));
        błędna.setText(" Błędne ");
        row.addView(błędna);

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
}