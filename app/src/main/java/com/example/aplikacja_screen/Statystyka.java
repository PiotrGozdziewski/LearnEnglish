package com.example.aplikacja_screen;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Statystyka extends AppCompatActivity {

    TableLayout tableLayout;
    Database db;
    ArrayList<Integer> Data;
    int poprawne = 0;
    int bledne = 0;
    PieChart pieChart;

    ////////
    int ID_cat;
    int ID_type;
    String Cat_name;
    String Type_name;
    String userID;
    TextView ilość_lekcji;

    //zmienne od dat
    String dt0, dt1, dt2, dt3, dt4, dt5, dt6, dt7 = "";
    //lista do przechowywania dat
    ArrayList<String> datyLekcji;
    //lista do przechowywania ilości lekcji
    ArrayList<Integer> ilośćLekcji;
    Cursor cursorIloscLekcji;

    public class Lekcja {
        String typ;
        String kategoria;
        Integer poprawne;
        Integer bledne;

        public Lekcja(String typ, String kat, Integer pop, Integer bl) {
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
        ilość_lekcji = (TextView)findViewById(R.id.textView23);
        db = new Database(getContentResolver());
        //pobranie idUżytkownika
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        userID = prefs.getString("id", "0");


        datyLekcji = new ArrayList<String>();
        ilośćLekcji = new ArrayList<Integer>();

        datyLekcji = pobierz_daty(); //przypisanie dat do tablicy od 0-6 --> 0 to dzisiejsza data
        ilośćLekcji = pobierzIloscLekcji();

        Cursor cursor = db.getLessons(Integer.parseInt(userID));
        while (cursor.moveToNext()) {
            String id = cursor.getString(1);
            if (userID.equals(id)) {
                int ilosc_poprawnych = cursor.getInt(3);
                poprawne += ilosc_poprawnych;
            }
        }

        Cursor cursor1 = db.getLessons(Integer.parseInt(userID));
        while (cursor1.moveToNext()) {
            String id = cursor1.getString(1);
            if (userID.equals(id)) {
                int ilosc_blednych = cursor1.getInt(4);
                bledne += ilosc_blednych;
            }
        }
        Data = new ArrayList<>();
        Data.add(bledne);
        Data.add(poprawne);

        Cursor cursor3 = db.getLessons(Integer.parseInt(userID));
        while (cursor3.moveToNext()) {

            String id = cursor3.getString(1);
            if (userID.equals(id)) {
                int zad = cursor3.getInt(2);
                int popr = cursor3.getInt(3);
                int bl = cursor3.getInt(4);
                String data = cursor3.getString(5);
                Cursor cursor4 = db.getIDCategorie(zad);
                while (cursor4.moveToNext()) {
                    ID_cat = cursor4.getInt(1);
                }
                Cursor cursor5 = db.getIDCategorie(zad);
                while (cursor5.moveToNext()) {
                    ID_type = cursor5.getInt(2);
                }
                Cursor cursor6 = db.getCategorieName(ID_cat);
                while (cursor6.moveToNext()) {
                    Cat_name = cursor6.getString(1);
                }
                Cursor cursor7 = db.getExerciceTypeName(ID_type);
                while (cursor7.moveToNext()) {
                    Type_name = cursor7.getString(1);
                }

                Lekcja l = new Lekcja(Type_name, Cat_name, popr, bl);
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
        kategoria.setText("   Kategoria   ");
        kategoria.setGravity(Gravity.CENTER);
        row.addView(kategoria);
        TextView typ_zadania = new TextView(this);
        typ_zadania.setTypeface(null, Typeface.BOLD);
        typ_zadania.setTextSize(17);
        typ_zadania.setTextColor(Color.parseColor("#FF991C1E"));
        typ_zadania.setText("   Typ   ");
        typ_zadania.setGravity(Gravity.CENTER);
        row.addView(typ_zadania);
        TextView poprawna = new TextView(this);
        poprawna.setTypeface(null, Typeface.BOLD);
        poprawna.setTextSize(17);
        poprawna.setTextColor(Color.parseColor("#FF991C1E"));
        poprawna.setText("  Poprawne   ");
        poprawna.setGravity(Gravity.CENTER);
        row.addView(poprawna);
        TextView błędna = new TextView(this);
        błędna.setTypeface(null, Typeface.BOLD);
        błędna.setTextSize(17);
        błędna.setTextColor(Color.parseColor("#FF991C1E"));
        błędna.setText("   Błędne   ");
        błędna.setGravity(Gravity.CENTER);
        row.addView(błędna);

        tableLayout.addView(row);

        int ilosc_lekcji = lekcja.size();

        if (ilosc_lekcji > 0 && ilosc_lekcji < 5) {
            for (int i = lekcja.size() - 1; i > -1; i--) {
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
        if (ilosc_lekcji >= 5) {
            for (int i = lekcja.size() - 1; i > lekcja.size() - 6; i--) {
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
        ///////// wyświetlanie ilości lekcji w ciągu 7 ostatnich dni
        for(int i=0;i<7;i++){
            ilość_lekcji.append("Data:  "+datyLekcji.get(i).toString()+"  \t");
            ilość_lekcji.append("Ilość:  "+ilośćLekcji.get(i).toString());
            ilość_lekcji.append("\n");

        }
    }

    private void addDataSet(PieChart pieChart) {
        ArrayList<PieEntry> yEntry = new ArrayList<>();
        yEntry.add(new PieEntry(Data.get(0), "Błędne"));
        yEntry.add(new PieEntry(Data.get(1), "Poprawne"));

        PieDataSet pieDataSet = new PieDataSet(yEntry, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(15);

        ArrayList<Integer> kolor = new ArrayList<>();
        kolor.add(Color.argb(200, 250, 20, 20));
        kolor.add(Color.argb(200, 91, 230, 80));

        pieDataSet.setColors(kolor);
        Legend leg = pieChart.getLegend();
        leg.setForm(Legend.LegendForm.SQUARE);
        leg.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    public ArrayList<String> pobierz_daty() {
        //lista do przechowywania dat
        ArrayList<String> daty = new ArrayList<String>();
        Calendar cal = Calendar.getInstance(Locale.ENGLISH); //pobranie daty dzisiejszej
        dt0 = DateFormat.format("yyyy-MM-dd", cal).toString();
        daty.add(dt0);
        cal.add(Calendar.DATE, -1);
        dt1 = DateFormat.format("yyyy-MM-dd", cal).toString();
        daty.add(dt1);
        cal.add(Calendar.DATE, -1);
        dt2 = DateFormat.format("yyyy-MM-dd", cal).toString();
        daty.add(dt2);
        cal.add(Calendar.DATE, -1);
        dt3 = DateFormat.format("yyyy-MM-dd", cal).toString();
        daty.add(dt3);
        cal.add(Calendar.DATE, -1);
        dt4 = DateFormat.format("yyyy-MM-dd", cal).toString();
        daty.add(dt4);
        cal.add(Calendar.DATE, -1);
        dt5 = DateFormat.format("yyyy-MM-dd", cal).toString();
        daty.add(dt5);
        cal.add(Calendar.DATE, -1);
        dt6 = DateFormat.format("yyyy-MM-dd", cal).toString();
        daty.add(dt6);
        cal.add(Calendar.DATE, -1);
        dt7 = DateFormat.format("yyyy-MM-dd", cal).toString();
        daty.add(dt7);

        return daty;
    }

    public ArrayList<Integer> pobierzIloscLekcji() {
        //lista do przechowywania ilości lekcji
        ArrayList<Integer> ilosc = new ArrayList<Integer>();
        int ilość_lekcji = 0;
        for (int i = 0; i < 7; i++) {
            cursorIloscLekcji = db.getLessonsUser(Integer.parseInt(userID));
            while (cursorIloscLekcji.moveToNext()) {
                String id = cursorIloscLekcji.getString(4);
                String d = cursorIloscLekcji.getString(5);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dt = null;
                try {
                    dt = sdf.parse(d);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String przekonwertowane = sdf.format(dt).toString();
                if (userID.equals(id) & przekonwertowane.equals(datyLekcji.get(i).toString())) {
                    ilość_lekcji++;
                }
            }
            ilosc.add(ilość_lekcji);
            ilość_lekcji = 0;
        }

        return ilosc;
    }
}