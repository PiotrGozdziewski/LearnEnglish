package com.example.aplikacja_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Wybor extends AppCompatActivity {

    Button b1, b2, b3, b4, nastepne_slowo;
    TextView slowo;
    Database db;
    Cursor cursor, cursor1, cursor2;
    int idKategorii;
    ArrayList<String> pl;
    ArrayList<String> en;
    Random r;
    int ilosc_slow;
    int random_pl, random_en1, random_en2, random_en3, random_en4;
    int index_pl;
    int index_en;
    ArrayList<Integer> randomowe_ustawienie_en;
    int ilosc_iteracji = 0;
    int id_typu_zadania;
    int id_zadania;
    int poprawne_odp = 0;
    int bledne_odp = 0;
    ProgressBar pb;
    int progress=0;

    boolean poprawna1=false;
    boolean poprawna2=false;
    boolean poprawna3=false;
    boolean poprawna4=false;
    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor);
        b1 = (Button) findViewById(R.id.button40);
        b2 = (Button) findViewById(R.id.button41);
        b3 = (Button) findViewById(R.id.button42);
        b4 = (Button) findViewById(R.id.button43);
        slowo = (TextView)findViewById(R.id.slovoTV);
        nastepne_slowo = (Button) findViewById(R.id.button39);
        pb = (ProgressBar)findViewById(R.id.progressBar5);
        pb.setMax(14);
        pb.getProgressDrawable().setColorFilter(Color.parseColor("#10961d"), android.graphics.PorterDuff.Mode.SRC_IN);
        db = new Database(getContentResolver());

        pl = new ArrayList<String>();
        en = new ArrayList<String>();
        randomowe_ustawienie_en = new ArrayList<Integer>();

        r = new Random();

        //pobranie id wybranego zadania
        SharedPreferences p1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String wybranyTypZadania = p1.getString("NazwaTypuZadania", "0");
        cursor1 = db.getExerciceType(wybranyTypZadania);
        while (cursor1.moveToNext()) {
            String nazwa_baza = cursor1.getString(1);
            if (wybranyTypZadania.equals(nazwa_baza)) {
                id_typu_zadania = cursor1.getInt(0);
            }
        }

        //pobranie id wybranej kategorii
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        idKategorii = p.getInt("idKategorii", 0);

        cursor = db.getWords(idKategorii);
        while (cursor.moveToNext()) {
            pl.add(cursor.getString(2));
            en.add(cursor.getString(3));
        }

        //pobranie id zadania
        SharedPreferences p2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        cursor2 = db.getExercise(idKategorii, id_typu_zadania);
        while (cursor2.moveToNext()) {
            id_zadania = cursor2.getInt(0);
        }

        ilosc_slow = pl.size();
        //ustawianie na początku
        random_pl = r.nextInt(ilosc_slow);
        random_en1 = r.nextInt(ilosc_slow);
        random_en2 = r.nextInt(ilosc_slow);
        random_en3 = r.nextInt(ilosc_slow);
        random_en4 = random_pl;


        slowo.setText(pl.get(random_pl).toString());
        b1.setText(en.get(random_en4).toString());
        b2.setText(en.get(random_en1).toString());
        b3.setText(en.get(random_en2).toString());
        b4.setText(en.get(random_en3).toString());
        nastepne_slowo.setClickable(false);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                index_en = en.indexOf(b1.getText().toString()); //liczony od zera
                index_pl = pl.indexOf(slowo.getText().toString());
                if (index_en == index_pl) {
                    nastepne_slowo.setClickable(true);
                    nastepne_slowo.setEnabled(true);
                    poprawna1=true;
                    b1.setBackgroundResource(R.drawable.zielony_gradient);
                    poprawne_odp++;
                    //blokowanie przyciskow
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                    nastepne_slowo.setClickable(true);
                } else {
                    bledne_odp++;
                    //co zrobic w przypadku zlej odpowiedzi
                    b1.setBackgroundResource(R.drawable.button_border_red);
                    //blokowanie przyciskow
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            b1.setBackgroundColor(Color.parseColor("#eaeae1"));
                            nastepne_slowo.setClickable(false);
                            b1.setClickable(true);
                            b2.setClickable(true);
                            b3.setClickable(true);
                            b4.setClickable(true);
                        }
                    }, 1200);
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                index_en = en.indexOf(b2.getText().toString()); //liczony od zera
                index_pl = pl.indexOf(slowo.getText().toString());
                if (index_en == index_pl) {
                    nastepne_slowo.setClickable(true);
                    nastepne_slowo.setEnabled(true);
                    poprawna2=true;
                    b2.setBackgroundResource(R.drawable.zielony_gradient);
                    poprawne_odp++;
                    //blokowanie przyciskow
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                    nastepne_slowo.setClickable(true);
                } else {
                    bledne_odp++;
                    b2.setBackgroundResource(R.drawable.button_border_red);
                    //blokowanie przyciskow
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            b2.setBackgroundColor(Color.parseColor("#eaeae1"));
                            nastepne_slowo.setClickable(false);
                            b1.setClickable(true);
                            b2.setClickable(true);
                            b3.setClickable(true);
                            b4.setClickable(true);
                        }
                    }, 1200);
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                index_en = en.indexOf(b3.getText().toString()); //liczony od zera
                index_pl = pl.indexOf(slowo.getText().toString());
                if (index_en == index_pl) {
                    nastepne_slowo.setClickable(true);
                    nastepne_slowo.setEnabled(true);
                    poprawna3=true;
                    b3.setBackgroundResource(R.drawable.zielony_gradient);
                    poprawne_odp++;
                    //blokowanie przyciskow
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                    nastepne_slowo.setClickable(true);
                } else {
                    bledne_odp++;
                    b3.setBackgroundResource(R.drawable.button_border_red);
                    //blokowanie przyciskow
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            b3.setBackgroundColor(Color.parseColor("#eaeae1"));
                            nastepne_slowo.setClickable(false);
                            b1.setClickable(true);
                            b2.setClickable(true);
                            b3.setClickable(true);
                            b4.setClickable(true);
                        }
                    }, 1200);
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                index_en = en.indexOf(b4.getText().toString()); //liczony od zera
                index_pl = pl.indexOf(slowo.getText().toString());
                if (index_en == index_pl) {
                    nastepne_slowo.setClickable(true);
                    nastepne_slowo.setEnabled(true);
                    poprawna4=true;
                    poprawne_odp++;
                    b4.setBackgroundResource(R.drawable.zielony_gradient);
                    //blokowanie przyciskow
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                    nastepne_slowo.setClickable(true);
                } else {
                    bledne_odp++;
                    b4.setBackgroundResource(R.drawable.button_border_red);
                    //blokowanie przyciskow
                    b1.setClickable(false);
                    b2.setClickable(false);
                    b3.setClickable(false);
                    b4.setClickable(false);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            b4.setBackgroundColor(Color.parseColor("#eaeae1"));
                            nastepne_slowo.setClickable(false);
                            b1.setClickable(true);
                            b2.setClickable(true);
                            b3.setClickable(true);
                            b4.setClickable(true);
                        }
                    }, 1200);
                }
            }
        });


        nastepne_slowo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress++;
                pb.setProgress(progress);
                //blokowanie przyciskow
                b1.setClickable(true);
                b2.setClickable(true);
                b3.setClickable(true);
                b4.setClickable(true);
                nastepne_slowo.setClickable(false);

                if (ilosc_iteracji == 13) {
                    nastepne_slowo.setText("Zakończ lekcję");
                }
                if (ilosc_iteracji == 14) {
                    zapisz_statytyski();
                    startActivity(new Intent(Wybor.this, WyborKategorii.class));
                }
                if (ilosc_iteracji < 14) {
                    random_pl = r.nextInt(ilosc_slow);
                    random_en1 = r.nextInt(ilosc_slow);
                    if (random_en1 == random_pl) {
                        do {
                            random_en1 = r.nextInt(ilosc_slow);
                        } while (random_pl == random_en1);
                    }
                    random_en2 = r.nextInt(ilosc_slow);
                    if (random_en2 == random_pl | random_en2 == random_en1) {
                        do {
                            random_en2 = r.nextInt(ilosc_slow);
                        } while (random_pl == random_en2 & random_en2 == random_en1);
                    }
                    random_en3 = r.nextInt(ilosc_slow);
                    if (random_en3 == random_pl | random_en3 == random_en1 | random_en3 == random_en2) {
                        do {
                            random_en3 = r.nextInt(ilosc_slow);
                        }
                        while (random_pl == random_en3 & random_en3 == random_en1 & random_en3 == random_en2);
                    }
                    random_en4 = random_pl;

                    randomowe_ustawienie_en.add(random_en1);
                    randomowe_ustawienie_en.add(random_en2);
                    randomowe_ustawienie_en.add(random_en3);
                    randomowe_ustawienie_en.add(random_en4);
                    int a, b, c, d;
                    a = r.nextInt(4);
                    b = r.nextInt(4);
                    if (b == a) {
                        do {
                            b = r.nextInt(4);
                        } while (b == a);
                    }
                    c = r.nextInt(4);
                    if (c == b | c == a) {
                        do {
                            c = r.nextInt(4);
                        } while (c == a | c == b);
                    }
                    d = r.nextInt(4);
                    if (d == c | d == b | d == a) {
                        do {
                            d = r.nextInt(4);
                        } while (d == a | d == b | d == c);
                    }
                    //czyszczenie buttonow background
                    b1.setBackgroundColor(Color.parseColor("#eaeae1"));
                    b2.setBackgroundColor(Color.parseColor("#eaeae1"));
                    b3.setBackgroundColor(Color.parseColor("#eaeae1"));
                    b4.setBackgroundColor(Color.parseColor("#eaeae1"));
                    slowo.setText(pl.get(random_pl).toString());
                    b1.setText(en.get(randomowe_ustawienie_en.get(b)).toString());
                    b2.setText(en.get(randomowe_ustawienie_en.get(c)).toString());
                    b3.setText(en.get(randomowe_ustawienie_en.get(d)).toString());
                    b4.setText(en.get(randomowe_ustawienie_en.get(a)).toString());
                    randomowe_ustawienie_en.clear();
                    ilosc_iteracji++;
                }
            }
        });
    }

    public void onBackPressed() {
        startActivity(new Intent(Wybor.this, WyborKategorii.class));
    }

    public void zapisz_statytyski() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userID = prefs.getString("id", "0");
        Date currentTime = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        String date = DateFormat.format("yyyy-MM-dd", cal).toString();
        db.insertIntoLessons(Integer.parseInt(userID), id_zadania, String.valueOf(poprawne_odp), String.valueOf(bledne_odp), date);
    }
}