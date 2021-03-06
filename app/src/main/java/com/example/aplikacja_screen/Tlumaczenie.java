package com.example.aplikacja_screen;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Tlumaczenie extends AppCompatActivity {

    Button sprawdz_poprawnosc, dalej, sprawdz;
    EditText en;
    TextView pl;

    ArrayList<String> polskie;
    ArrayList<String> angielskie;

    Database db;
    int i = 0;
    int poprawne_odp = 0;
    int bledne_odp = 0;
    Boolean bledna = false;
    Handler h = new Handler();
    Cursor cursor,cursor1, cursor2;
    int id_typu_zadania;
    int idKategorii;
    int id_zadania;

    ProgressBar pb;
    int progress=0;

    Random r;
    int randomowa_wartosc;
    int ilosc_slow=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tlumaczenie);
        sprawdz_poprawnosc = (Button) findViewById(R.id.button17);
        dalej = (Button) findViewById(R.id.button20);
        sprawdz = (Button) findViewById(R.id.button38);
        pl = (TextView) findViewById(R.id.textView21);
        en = (EditText) findViewById(R.id.textView22);
        pb=(ProgressBar)findViewById(R.id.progressBar);
        polskie = new ArrayList<String>();
        angielskie = new ArrayList<String>();
        db = new Database(getContentResolver());

        pb.setMax(9);
        pb.getProgressDrawable().setColorFilter(Color.parseColor("#10961d"), android.graphics.PorterDuff.Mode.SRC_IN);
        //pobranie id aktualnej kategorii
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        idKategorii = p.getInt("idKategorii", 0);

        SharedPreferences p1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String wybranyTypZadania = p1.getString("NazwaTypuZadania", "0");
        //Toast.makeText(getApplicationContext(),"nazwa: "+wybranyTypZadania,Toast.LENGTH_SHORT).show();
        cursor1 = db.getExerciceType(wybranyTypZadania);
        while (cursor1.moveToNext()) {
            String nazwa_baza = cursor1.getString(1);
            if (wybranyTypZadania.equals(nazwa_baza)) {
                id_typu_zadania = cursor1.getInt(0);
            }
        }
        cursor = db.getWords(idKategorii);
        while (cursor.moveToNext()) {
            polskie.add(cursor.getString(2));
            angielskie.add(cursor.getString(3));
        }

        //kod od randoma
        //wybrac 10 różnych elementow zakresu od 0 do polskie.size()-1
        ilosc_slow=polskie.size()-1;
        r = new Random();
        randomowa_wartosc=r.nextInt(ilosc_slow);


        //pobranie id zadania
        SharedPreferences p2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        cursor2 = db.getExercise(idKategorii, id_typu_zadania);
        while (cursor2.moveToNext()) {
            id_zadania = cursor2.getInt(0);
        }

        pl.setText(polskie.get(randomowa_wartosc));  //0
        sprawdz_poprawnosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (en.getText().toString().toLowerCase().equals(angielskie.get(randomowa_wartosc).toString().toLowerCase())) {  //i

                    sprawdz.setVisibility(View.INVISIBLE);
                    sprawdz_poprawnosc.setText("Poprawna odpowiedź");
                    sprawdz_poprawnosc.setBackgroundColor(Color.parseColor("#38ea3e"));
                    if (bledna == false) {
                        poprawne_odp++;
                    }
                    sprawdz_poprawnosc.setClickable(false);
                    dalej.setVisibility(View.VISIBLE);
                } else {
                    sprawdz_poprawnosc.setText("Niepoprawna odpowiedź");
                    sprawdz_poprawnosc.setBackgroundColor(Color.parseColor("#d11f34"));
                    if (bledna == false) {
                        bledne_odp++;
                        bledna = true;
                    }
                    sprawdz.setVisibility(View.VISIBLE);
                    sprawdz_poprawnosc.setClickable(false);
                    sprawdz.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog alertDialog = new AlertDialog.Builder(Tlumaczenie.this).create();
                            alertDialog.setTitle("Poprawna odpowiedz");
                            alertDialog.setMessage(polskie.get(randomowa_wartosc) + " - " + angielskie.get(randomowa_wartosc));
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                            sprawdz_poprawnosc.setText("Sprawdź poprawność");
                            sprawdz_poprawnosc.setBackgroundResource(R.drawable.zestawy_screen);
                        }
                    });

                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sprawdz_poprawnosc.setText("Sprawdź poprawność");
                            //podpowiedzButton.setVisibility(View.VISIBLE);
                            sprawdz_poprawnosc.setVisibility(View.VISIBLE);
                            sprawdz_poprawnosc.setClickable(true);
                            sprawdz_poprawnosc.setBackgroundResource(R.drawable.zestawy_screen);
                        }
                    }, 3000);
                }
            }
        });

        dalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                r = new Random();
                randomowa_wartosc=r.nextInt(ilosc_slow);
                progress++;
                //postep
                pb.setProgress(progress);

                if (i == 9) {
                    zapisz_statytyski();
                    startActivity(new Intent(Tlumaczenie.this, WyborKategorii.class));
                }
                en.setText("");
                sprawdz_poprawnosc.setClickable(true);
                sprawdz_poprawnosc.setText("Sprawdź poprawność");
                sprawdz_poprawnosc.setBackgroundResource(R.drawable.zestawy_screen);
                i++;
                bledna = false;
                dalej.setVisibility(View.INVISIBLE);
                if (i <= 9) {
                    wczytaj_slowa(randomowa_wartosc); //i
                }
                if (i == 9) {
                    dalej.setText("Zakończ lekcje");
                }
            }
        });


    }

    public void wczytaj_slowa(int index) {
        pl.setText(polskie.get(index));
    }

    public void zapisz_statytyski() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userID = prefs.getString("id", "0");
        Date currentTime = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        String date = DateFormat.format("yyyy-MM-dd hh:mm:ss", cal).toString();
        db.insertIntoLessons(Integer.parseInt(userID), id_zadania, String.valueOf(poprawne_odp), String.valueOf(bledne_odp), date);
    }

    public void onBackPressed() {
        startActivity(new Intent(Tlumaczenie.this, WyborKategorii.class));
    }

}
