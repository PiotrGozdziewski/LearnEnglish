package com.example.aplikacja_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import android.widget.Toast;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;

public class PlanszaKwadratowa extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, dalej;

    ArrayList<String> pl;
    ArrayList<String> en;

    Database db;

    boolean click1 = false;
    boolean click2 = false;
    boolean click3 = false;
    boolean click4 = false;
    boolean click5 = false;
    boolean click6 = false;
    boolean click7 = false;
    boolean click8 = false;
    int random0 = 0;
    int random1 = 0;
    int random2 = 0;
    int random3 = 0;

    int b1, b2, b3, b4, b5, b6, b7, b8 = 0;

    int index_lista_randomowych;
    ArrayList<Integer> lista_randomowych;
    ArrayList<Integer> lista_wykorzystanych_pl;
    ArrayList<Integer> lista_wykorzystanych_en;
    Random random_lista;
    //nastepny ekran
    boolean c1=false,c2=false,c3=false,c4=false,c5=false,c6=false,c7=false,c8=false;
    //od wątków
    Handler handler = new Handler();
    Handler handler1 = new Handler();
    static int i=0;

    public void ustawWartosci() {
        do {
            index_lista_randomowych = random_lista.nextInt(lista_randomowych.size()) + 0;
        } while (lista_wykorzystanych_pl.contains(index_lista_randomowych));
        lista_wykorzystanych_pl.add(index_lista_randomowych);
        b1 = index_lista_randomowych;

        do {
            index_lista_randomowych = random_lista.nextInt(lista_randomowych.size()) + 0;
        } while (lista_wykorzystanych_en.contains(index_lista_randomowych));
        lista_wykorzystanych_en.add(index_lista_randomowych);
        b2 = index_lista_randomowych;

        do {
            index_lista_randomowych = random_lista.nextInt(lista_randomowych.size()) + 0;
        } while (lista_wykorzystanych_pl.contains(index_lista_randomowych));
        lista_wykorzystanych_pl.add(index_lista_randomowych);
        b3 = index_lista_randomowych;

        do {
            index_lista_randomowych = random_lista.nextInt(lista_randomowych.size()) + 0;
        } while (lista_wykorzystanych_en.contains(index_lista_randomowych));
        lista_wykorzystanych_en.add(index_lista_randomowych);
        b4 = index_lista_randomowych;

        do {
            index_lista_randomowych = random_lista.nextInt(lista_randomowych.size()) + 0;
        } while (lista_wykorzystanych_pl.contains(index_lista_randomowych));
        lista_wykorzystanych_pl.add(index_lista_randomowych);
        b5 = index_lista_randomowych;

        do {
            index_lista_randomowych = random_lista.nextInt(lista_randomowych.size()) + 0;
        } while (lista_wykorzystanych_en.contains(index_lista_randomowych));
        lista_wykorzystanych_en.add(index_lista_randomowych);
        b6 = index_lista_randomowych;

        do {
            index_lista_randomowych = random_lista.nextInt(lista_randomowych.size()) + 0;
        } while (lista_wykorzystanych_pl.contains(index_lista_randomowych));
        lista_wykorzystanych_pl.add(index_lista_randomowych);
        b7 = index_lista_randomowych;

        do {
            index_lista_randomowych = random_lista.nextInt(lista_randomowych.size()) + 0;
        } while (lista_wykorzystanych_en.contains(index_lista_randomowych));
        lista_wykorzystanych_en.add(index_lista_randomowych);
        b8 = index_lista_randomowych;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plansza_kwadratowa);

        btn1 = (Button) findViewById(R.id.button10);
        btn2 = (Button) findViewById(R.id.button12);
        btn3 = (Button) findViewById(R.id.button13);
        btn4 = (Button) findViewById(R.id.button31);
        btn5 = (Button) findViewById(R.id.button34);
        btn6 = (Button) findViewById(R.id.button35);
        btn7 = (Button) findViewById(R.id.button36);
        btn8 = (Button) findViewById(R.id.button37);
        dalej = (Button) findViewById(R.id.button38);
        db = new Database(getContentResolver());
        pl = new ArrayList<String>();
        en = new ArrayList<String>();
        //następny ekran
        dalej.setVisibility(View.INVISIBLE);

        //pobranie id Kategorii
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int idKategorii = p.getInt("idKategorii", 0);
        Cursor cursor = db.getWords(idKategorii);
        while (cursor.moveToNext()) {
            pl.add(cursor.getString(2));
            en.add(cursor.getString(3));
        }

        if(i<=4)
        {
            dalej.setText("Dalej");
        }
        if(i==5)
        {
            dalej.setText("Zakończ");
        }

        int ilosc = pl.size();
        Random r = new Random();
        random0 = r.nextInt(ilosc) + 0;
        random1 = r.nextInt(ilosc) + 0;
        random2 = r.nextInt(ilosc) + 0;
        random3 = r.nextInt(ilosc) + 0;

        lista_randomowych = new ArrayList<Integer>();
        lista_wykorzystanych_pl = new ArrayList<Integer>();
        lista_wykorzystanych_en = new ArrayList<Integer>();
        index_lista_randomowych = 0;
        lista_randomowych.add(random0);
        lista_randomowych.add(random1);
        lista_randomowych.add(random2);
        lista_randomowych.add(random3);

        random_lista = new Random();
        ustawWartosci();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click1 = true;
                btn1.setText(pl.get(lista_randomowych.get(b1)));

                // btn1.setText(zakreskowaneSlowoTV.get(random0));

                if (click7 == true) {
                    if ((btn1.getText() == pl.get(random0) && btn7.getText() == en.get(random0)) || (btn1.getText() == en.get(random0) && btn7.getText() == pl.get(random0))) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        click1 = false;
                        click7 = false;
                        c1=true;
                        c7=true;
                    } else if ((btn1.getText() == pl.get(random1) && btn7.getText() == en.get(random1)) || (btn1.getText() == en.get(random1) && btn7.getText() == pl.get(random1))) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        click1 = false;
                        click7 = false;
                        c1=true;
                        c7=true;
                    } else if ((btn1.getText() == pl.get(random2) && btn7.getText() == en.get(random2)) || (btn1.getText() == en.get(random2) && btn7.getText() == pl.get(random2))) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        click1 = false;
                        click7 = false;
                        c1=true;
                        c7=true;
                    } else if ((btn1.getText() == pl.get(random3) && btn7.getText() == en.get(random3)) || (btn1.getText() == en.get(random3) && btn7.getText() == pl.get(random3))) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        click1 = false;
                        click7 = false;
                        c1=true;
                        c7=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn7.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click1 = false;
                        click7 = false;
                    }
                } else if (click2 == true) {
                    if ((btn2.getText() == pl.get(random0) && btn1.getText() == en.get(random0)) || (btn2.getText() == en.get(random0) && btn1.getText() == pl.get(random0))) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click1 = false;
                        c1=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random1) && btn1.getText() == en.get(random1)) || (btn2.getText() == en.get(random1) && btn1.getText() == pl.get(random1))) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click1 = false;
                        c1=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random2) && btn1.getText() == en.get(random2)) || (btn2.getText() == en.get(random2) && btn1.getText() == pl.get(random2))) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click1 = false;
                        c1=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random3) && btn1.getText() == en.get(random3)) || (btn2.getText() == en.get(random3) && btn1.getText() == pl.get(random3))) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click1 = false;
                        c1=true;
                        c2=true;
                    } else {
                        btn7.setClickable(false);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn2.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click2 = false;
                        click1 = false;
                    }
                } else if (click3 == true) {
                    if ((btn3.getText() == pl.get(random0) && btn1.getText() == en.get(random0)) || (btn3.getText() == en.get(random0) && btn1.getText() == pl.get(random0))) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click1 = false;
                        c1=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random1) && btn1.getText() == en.get(random1)) || (btn3.getText() == en.get(random1) && btn1.getText() == pl.get(random1))) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click1 = false;
                        c1=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random2) && btn1.getText() == en.get(random2)) || (btn3.getText() == en.get(random2) && btn1.getText() == pl.get(random2))) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click1 = false;
                        c1=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random3) && btn1.getText() == en.get(random3)) || (btn3.getText() == en.get(random3) && btn1.getText() == pl.get(random3))) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click1 = false;
                        c1=true;
                        c3=true;
                    } else {
                        btn2.setClickable(false);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn3.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click3 = false;
                        click1 = false;
                    }
                } else if (click4 == true) {
                    if ((btn4.getText() == pl.get(random0) && btn1.getText() == en.get(random0)) || (btn4.getText() == en.get(random0) && btn1.getText() == pl.get(random0))) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click1 = false;
                        c1=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random1) && btn1.getText() == en.get(random1)) || (btn4.getText() == en.get(random1) && btn1.getText() == pl.get(random1))) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click1 = false;
                        c1=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random2) && btn1.getText() == en.get(random2)) || (btn4.getText() == en.get(random2) && btn1.getText() == pl.get(random2))) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click1 = false;
                        c1=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random3) && btn1.getText() == en.get(random3)) || (btn4.getText() == en.get(random3) && btn1.getText() == pl.get(random3))) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click1 = false;
                        c1=true;
                        c4=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn7.setClickable(false);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn4.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click4 = false;
                        click1 = false;
                    }
                } else if (click5 == true) {
                    if ((btn5.getText() == pl.get(random0) && btn1.getText() == en.get(random0)) || (btn5.getText() == en.get(random0) && btn1.getText() == pl.get(random0))) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click1 = false;
                        c1=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random1) && btn1.getText() == en.get(random1)) || (btn5.getText() == en.get(random1) && btn1.getText() == pl.get(random1))) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click1 = false;
                        c1=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random2) && btn1.getText() == en.get(random2)) || (btn5.getText() == en.get(random2) && btn1.getText() == pl.get(random2))) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click1 = false;
                        c1=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random3) && btn1.getText() == en.get(random3)) || (btn5.getText() == en.get(random3) && btn1.getText() == pl.get(random3))) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click1 = false;
                        c1=true;
                        c5=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn5.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click5 = false;
                        click1 = false;
                    }
                } else if (click6 == true) {
                    if ((btn6.getText() == pl.get(random0) && btn1.getText() == en.get(random0)) || (btn6.getText() == en.get(random0) && btn1.getText() == pl.get(random0))) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click1 = false;
                        c1=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random1) && btn1.getText() == en.get(random1)) || (btn6.getText() == en.get(random1) && btn1.getText() == pl.get(random1))) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click1 = false;
                        c1=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random2) && btn1.getText() == en.get(random2)) || (btn6.getText() == en.get(random2) && btn1.getText() == pl.get(random2))) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click1 = false;
                        c1=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random3) && btn1.getText() == en.get(random3)) || (btn6.getText() == en.get(random3) && btn1.getText() == pl.get(random3))) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click1 = false;
                        c1=true;
                        c6=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn6.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click6 = false;
                        click1 = false;
                    }
                } else if (click8 == true) {
                    if ((btn8.getText() == pl.get(random0) && btn1.getText() == en.get(random0)) || (btn8.getText() == en.get(random0) && btn1.getText() == pl.get(random0))) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn1.setClickable(false);
                        btn8.setClickable(false);
                        click1 = false;
                        click8 = false;
                        c1=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random1) && btn1.getText() == en.get(random1)) || (btn8.getText() == en.get(random1) && btn1.getText() == pl.get(random1))) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn1.setClickable(false);
                        btn8.setClickable(false);
                        click1 = false;
                        click8 = false;
                        c1=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random2) && btn1.getText() == en.get(random2)) || (btn8.getText() == en.get(random2) && btn1.getText() == pl.get(random2))) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn1.setClickable(false);
                        btn8.setClickable(false);
                        click1 = false;
                        click8 = false;
                        c1=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random3) && btn1.getText() == en.get(random3)) || (btn8.getText() == en.get(random3) && btn1.getText() == pl.get(random3))) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn1.setClickable(false);
                        btn8.setClickable(false);
                        click1 = false;
                        click8 = false;
                        c1=true;
                        c8=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        btn7.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn8.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click1 = false;
                        click8 = false;
                    }
                }
                sprawdz();
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click2 = true;

                btn2.setText(en.get(lista_randomowych.get(b2)));

                //btn2.setText(brakujaceLiteryET.get(random2));

                if (click1 == true) {
                    if ((btn1.getText() == pl.get(random0) && btn2.getText() == en.get(random0)) || (btn1.getText() == en.get(random0) && btn2.getText() == pl.get(random0))) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click2 = false;
                        c2=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random1) && btn2.getText() == en.get(random1)) || (btn1.getText() == en.get(random1) && btn2.getText() == pl.get(random1))) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click2 = false;
                        c2=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random2) && btn2.getText() == en.get(random2)) || (btn1.getText() == en.get(random2) && btn2.getText() == pl.get(random2))) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click2 = false;
                        c2=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random3) && btn2.getText() == en.get(random3)) || (btn1.getText() == en.get(random3) && btn2.getText() == pl.get(random3))) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        click2 = false;
                        click2 = false;
                        c2=true;
                        c1=true;
                    } else {
                        btn7.setClickable(false);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn2.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);

                        click2 = false;
                        click2 = false;
                    }
                } else if (click7 == true) {
                    if ((btn2.getText() == pl.get(random0) && btn7.getText() == en.get(random0)) || (btn2.getText() == en.get(random0) && btn7.getText() == pl.get(random0))) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setClickable(false);
                        btn7.setClickable(false);
                        click2 = false;
                        click7 = false;
                        c2=true;
                        c7=true;
                    } else if ((btn2.getText() == pl.get(random1) && btn7.getText() == en.get(random1)) || (btn2.getText() == en.get(random1) && btn7.getText() == pl.get(random1))) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setClickable(false);
                        btn7.setClickable(false);
                        click2 = false;
                        click7 = false;
                        c2=true;
                        c7=true;
                    } else if ((btn2.getText() == pl.get(random2) && btn7.getText() == en.get(random2)) || (btn2.getText() == en.get(random2) && btn7.getText() == pl.get(random2))) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setClickable(false);
                        btn7.setClickable(false);
                        click2 = false;
                        click7 = false;
                        c2=true;
                        c7=true;
                    } else if ((btn2.getText() == pl.get(random3) && btn7.getText() == en.get(random3)) || (btn2.getText() == en.get(random3) && btn7.getText() == pl.get(random3))) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setClickable(false);
                        btn7.setClickable(false);
                        click2 = false;
                        click7 = false;
                        c2=true;
                        c7=true;
                    } else {
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn2.setText(" ");
                                btn7.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click2 = false;
                        click7 = false;
                    }
                } else if (click3 == true) {
                    if ((btn3.getText() == pl.get(random0) && btn2.getText() == en.get(random0)) || (btn3.getText() == en.get(random0) && btn2.getText() == pl.get(random0))) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click2 = false;
                        c2=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random1) && btn2.getText() == en.get(random1)) || (btn3.getText() == en.get(random1) && btn2.getText() == pl.get(random1))) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click2 = false;
                        c2=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random2) && btn2.getText() == en.get(random2)) || (btn3.getText() == en.get(random2) && btn2.getText() == pl.get(random2))) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click2 = false;
                        c2=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random3) && btn2.getText() == en.get(random3)) || (btn3.getText() == en.get(random3) && btn2.getText() == pl.get(random3))) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click2 = false;
                        c2=true;
                        c3=true;
                    } else {
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn3.setText(" ");
                                btn2.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click3 = false;
                        click2 = false;
                    }
                } else if (click4 == true) {
                    if ((btn4.getText() == pl.get(random0) && btn2.getText() == en.get(random0)) || (btn4.getText() == en.get(random0) && btn2.getText() == pl.get(random0))) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click2 = false;
                        c2=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random1) && btn2.getText() == en.get(random1)) || (btn4.getText() == en.get(random1) && btn2.getText() == pl.get(random1))) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click2 = false;
                        c2=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random2) && btn2.getText() == en.get(random2)) || (btn4.getText() == en.get(random2) && btn2.getText() == pl.get(random2))) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click2 = false;
                        c2=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random3) && btn2.getText() == en.get(random3)) || (btn4.getText() == en.get(random3) && btn2.getText() == pl.get(random3))) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click2 = false;
                        c2=true;
                        c4=true;
                    } else {
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        btn7.setClickable(false);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn2.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click4 = false;
                        click2 = false;
                    }
                } else if (click5 == true) {
                    if ((btn5.getText() == pl.get(random0) && btn2.getText() == en.get(random0)) || (btn5.getText() == en.get(random0) && btn2.getText() == pl.get(random0))) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click2 = false;
                        c2=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random1) && btn2.getText() == en.get(random1)) || (btn5.getText() == en.get(random1) && btn2.getText() == pl.get(random1))) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click2 = false;
                        c2=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random2) && btn2.getText() == en.get(random2)) || (btn5.getText() == en.get(random2) && btn2.getText() == pl.get(random2))) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click2 = false;
                        c2=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random3) && btn2.getText() == en.get(random3)) || (btn5.getText() == en.get(random3) && btn2.getText() == pl.get(random3))) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click2 = false;
                        c2=true;
                        c5=true;
                    } else {
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn2.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);

                        click5 = false;
                        click2 = false;
                    }
                } else if (click6 == true) {
                    if ((btn6.getText() == pl.get(random0) && btn2.getText() == en.get(random0)) || (btn6.getText() == en.get(random0) && btn2.getText() == pl.get(random0))) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click2 = false;
                        c2=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random1) && btn2.getText() == en.get(random1)) || (btn6.getText() == en.get(random1) && btn2.getText() == pl.get(random1))) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click2 = false;
                        c2=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random2) && btn2.getText() == en.get(random2)) || (btn6.getText() == en.get(random2) && btn2.getText() == pl.get(random2))) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click2 = false;
                        c2=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random3) && btn2.getText() == en.get(random3)) || (btn6.getText() == en.get(random3) && btn2.getText() == pl.get(random3))) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click2 = false;
                        c2=true;
                        c6=true;
                    } else {
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn2.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click6 = false;
                        click2 = false;
                    }
                } else if (click8 == true) {
                    if ((btn8.getText() == pl.get(random0) && btn2.getText() == en.get(random0)) || (btn8.getText() == en.get(random0) && btn2.getText() == pl.get(random0))) {
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn2.setClickable(false);
                        btn8.setClickable(false);
                        click8 = false;
                        click2 = false;
                        c2=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random1) && btn2.getText() == en.get(random1)) || (btn8.getText() == en.get(random1) && btn2.getText() == pl.get(random1))) {
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn2.setClickable(false);
                        btn8.setClickable(false);
                        click8 = false;
                        click2 = false;
                        c2=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random2) && btn2.getText() == en.get(random2)) || (btn8.getText() == en.get(random2) && btn2.getText() == pl.get(random2))) {
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn2.setClickable(false);
                        btn8.setClickable(false);
                        click8 = false;
                        click2 = false;
                        c2=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random3) && btn2.getText() == en.get(random3)) || (btn8.getText() == en.get(random3) && btn2.getText() == pl.get(random3))) {
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn2.setClickable(false);
                        btn8.setClickable(false);
                        click8 = false;
                        click2 = false;
                        c2=true;
                        c8=true;
                    } else {
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        btn7.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn2.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click8 = false;
                        click2 = false;
                    }
                }
                sprawdz();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click3 = true;

                btn3.setText(pl.get(lista_randomowych.get(b3)));

                //btn3.setText(zakreskowaneSlowoTV.get(random3));

                if (click1 == true) {
                    if ((btn1.getText() == pl.get(random0) && btn3.getText() == en.get(random0)) || (btn1.getText() == en.get(random0) && btn3.getText() == pl.get(random0))) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click3 = false;
                        c3=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random1) && btn3.getText() == en.get(random1)) || (btn1.getText() == en.get(random1) && btn3.getText() == pl.get(random1))) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click3 = false;
                        c3=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random2) && btn3.getText() == en.get(random2)) || (btn1.getText() == en.get(random2) && btn3.getText() == pl.get(random2))) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click3 = false;
                        c3=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random3) && btn3.getText() == en.get(random3)) || (btn1.getText() == en.get(random3) && btn3.getText() == pl.get(random3))) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click3 = false;
                        c3=true;
                        c1=true;
                    } else {
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn1.setText(" ");
                                btn3.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click1 = false;
                        click3 = false;
                    }
                } else if (click2 == true) {
                    if ((btn2.getText() == pl.get(random0) && btn3.getText() == en.get(random0)) || (btn2.getText() == en.get(random0) && btn3.getText() == pl.get(random0))) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click3 = false;
                        c3=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random1) && btn3.getText() == en.get(random1)) || (btn2.getText() == en.get(random1) && btn3.getText() == pl.get(random1))) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click3 = false;
                        c3=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random2) && btn3.getText() == en.get(random2)) || (btn2.getText() == en.get(random2) && btn3.getText() == pl.get(random2))) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click3 = false;
                        c3=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random3) && btn3.getText() == en.get(random3)) || (btn2.getText() == en.get(random3) && btn3.getText() == pl.get(random3))) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click3 = false;
                        c3=true;
                        c2=true;
                    } else {
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn2.setText(" ");
                                btn3.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click2 = false;
                        click3 = false;
                    }
                } else if (click7 == true) {
                    if ((btn3.getText() == pl.get(random0) && btn7.getText() == en.get(random0)) || (btn3.getText() == en.get(random0) && btn7.getText() == pl.get(random0))) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setClickable(false);
                        btn7.setClickable(false);
                        click3 = false;
                        click7 = false;
                        c3=true;
                        c7=true;
                    } else if ((btn3.getText() == pl.get(random1) && btn7.getText() == en.get(random1)) || (btn3.getText() == en.get(random1) && btn7.getText() == pl.get(random1))) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setClickable(false);
                        btn7.setClickable(false);
                        click3 = false;
                        click7 = false;
                        c3=true;
                        c7=true;
                    } else if ((btn3.getText() == pl.get(random2) && btn7.getText() == en.get(random2)) || (btn3.getText() == en.get(random2) && btn7.getText() == pl.get(random2))) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setClickable(false);
                        btn7.setClickable(false);
                        click3 = false;
                        click7 = false;
                        c3=true;
                        c7=true;
                    } else if ((btn3.getText() == pl.get(random3) && btn7.getText() == en.get(random3)) || (btn3.getText() == en.get(random3) && btn7.getText() == pl.get(random3))) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setClickable(false);
                        btn7.setClickable(false);
                        click3 = false;
                        click7 = false;
                        c3=true;
                        c7=true;
                    } else {
                        btn1.setClickable(false);
                        btn2.setClickable(false);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn3.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click3 = false;
                        click7 = false;
                    }
                } else if (click4 == true) {
                    if ((btn4.getText() == pl.get(random0) && btn3.getText() == en.get(random0)) || (btn4.getText() == en.get(random0) && btn3.getText() == pl.get(random0))) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click3 = false;
                        c3=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random1) && btn3.getText() == en.get(random1)) || (btn4.getText() == en.get(random1) && btn3.getText() == pl.get(random1))) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click3 = false;
                        c3=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random2) && btn3.getText() == en.get(random2)) || (btn4.getText() == en.get(random2) && btn3.getText() == pl.get(random2))) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click3 = false;
                        c3=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random3) && btn3.getText() == en.get(random3)) || (btn4.getText() == en.get(random3) && btn3.getText() == pl.get(random3))) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click3 = false;
                        c3=true;
                        c4=true;
                    } else {
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        btn2.setClickable(false);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn3.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click4 = false;
                        click3 = false;
                    }
                } else if (click5 == true) {
                    if ((btn5.getText() == pl.get(random0) && btn3.getText() == en.get(random0)) || (btn5.getText() == en.get(random0) && btn3.getText() == pl.get(random0))) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click3 = false;
                        c3=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random1) && btn3.getText() == en.get(random1)) || (btn5.getText() == en.get(random1) && btn3.getText() == pl.get(random1))) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click3 = false;
                        c3=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random2) && btn3.getText() == en.get(random2)) || (btn5.getText() == en.get(random2) && btn3.getText() == pl.get(random2))) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click3 = false;
                        c3=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random3) && btn3.getText() == en.get(random3)) || (btn5.getText() == en.get(random3) && btn3.getText() == pl.get(random3))) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click3 = false;
                        c3=true;
                        c5=true;
                    } else {
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        btn2.setClickable(false);
                        btn4.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn3.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click5 = false;
                        click3 = false;
                    }
                } else if (click6 == true) {
                    if ((btn6.getText() == pl.get(random0) && btn3.getText() == en.get(random0)) || (btn6.getText() == en.get(random0) && btn3.getText() == pl.get(random0))) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click3 = false;
                        c3=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random1) && btn3.getText() == en.get(random1)) || (btn6.getText() == en.get(random1) && btn3.getText() == pl.get(random1))) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click3 = false;
                        c3=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random2) && btn3.getText() == en.get(random2)) || (btn6.getText() == en.get(random2) && btn3.getText() == pl.get(random2))) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click3 = false;
                        c3=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random3) && btn3.getText() == en.get(random3)) || (btn6.getText() == en.get(random3) && btn3.getText() == pl.get(random3))) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click3 = false;
                        c3=true;
                        c6=true;
                    } else {
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        btn2.setClickable(false);
                        btn5.setClickable(false);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn3.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click6 = false;
                        click3 = false;
                    }
                } else if (click8 == true) {
                    if ((btn8.getText() == pl.get(random0) && btn3.getText() == en.get(random0)) || (btn8.getText() == en.get(random0) && btn3.getText() == pl.get(random0))) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn3.setClickable(false);
                        btn8.setClickable(false);
                        click3 = false;
                        click8 = false;
                        c3=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random1) && btn3.getText() == en.get(random1)) || (btn8.getText() == en.get(random1) && btn3.getText() == pl.get(random1))) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn3.setClickable(false);
                        btn8.setClickable(false);
                        click3 = false;
                        click8 = false;
                        c3=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random2) && btn3.getText() == en.get(random2)) || (btn8.getText() == en.get(random2) && btn3.getText() == pl.get(random2))) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn3.setClickable(false);
                        btn8.setClickable(false);
                        click3 = false;
                        click8 = false;
                        c3=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random3) && btn3.getText() == en.get(random3)) || (btn8.getText() == en.get(random3) && btn3.getText() == pl.get(random3))) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn3.setClickable(false);
                        btn8.setClickable(false);
                        click3 = false;
                        click8 = false;
                        c3=true;
                        c8=true;
                    } else {
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        btn2.setClickable(false);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn3.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click3 = false;
                        click8 = false;
                    }
                }
                sprawdz();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click4 = true;

                btn4.setText(en.get(lista_randomowych.get(b4)));

                //btn4.setText(brakujaceLiteryET.get(random3));

                if (click1 == true) {
                    if ((btn1.getText() == pl.get(random0) && btn4.getText() == en.get(random0)) || (btn1.getText() == en.get(random0) && btn4.getText() == pl.get(random0))) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click4 = false;
                        c4=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random1) && btn4.getText() == en.get(random1)) || (btn1.getText() == en.get(random1) && btn4.getText() == pl.get(random1))) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click4 = false;
                        c4=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random2) && btn4.getText() == en.get(random2)) || (btn1.getText() == en.get(random2) && btn4.getText() == pl.get(random2))) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click4 = false;
                        c4=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random3) && btn4.getText() == en.get(random3)) || (btn1.getText() == en.get(random3) && btn4.getText() == pl.get(random3))) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click4 = false;
                        c4=true;
                        c1=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn1.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click1 = false;
                        click4 = false;
                    }
                } else if (click2 == true) {
                    if ((btn2.getText() == pl.get(random0) && btn4.getText() == en.get(random0)) || (btn2.getText() == en.get(random0) && btn4.getText() == pl.get(random0))) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click4 = false;
                        c4=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random1) && btn4.getText() == en.get(random1)) || (btn2.getText() == en.get(random1) && btn4.getText() == pl.get(random1))) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click4 = false;
                        c4=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random2) && btn4.getText() == en.get(random2)) || (btn2.getText() == en.get(random2) && btn4.getText() == pl.get(random2))) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click4 = false;
                        c4=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random3) && btn4.getText() == en.get(random3)) || (btn2.getText() == en.get(random3) && btn4.getText() == pl.get(random3))) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click4 = false;
                        c4=true;
                        c2=true;
                    } else {
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn2.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click2 = false;
                        click4 = false;
                    }
                } else if (click3 == true) {
                    if ((btn3.getText() == pl.get(random0) && btn4.getText() == en.get(random0)) || (btn3.getText() == en.get(random0) && btn4.getText() == pl.get(random0))) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click4 = false;
                        c4=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random1) && btn4.getText() == en.get(random1) || (btn3.getText() == en.get(random1) && btn4.getText() == pl.get(random1)))) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click4 = false;
                        c4=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random2) && btn4.getText() == en.get(random2)) || (btn3.getText() == en.get(random2) && btn4.getText() == pl.get(random2))) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click4 = false;
                        c4=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random3) && btn4.getText() == en.get(random3)) || (btn3.getText() == en.get(random3) && btn4.getText() == pl.get(random3))) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click4 = false;
                        c4=true;
                        c3=true;
                    } else {
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn3.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click3 = false;
                        click4 = false;
                    }
                } else if (click7 == true) {
                    if ((btn4.getText() == pl.get(random0) && btn7.getText() == en.get(random0)) || (btn4.getText() == en.get(random0) && btn7.getText() == pl.get(random0))) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setClickable(false);
                        btn7.setClickable(false);
                        click4 = false;
                        click7 = false;
                        c4=true;
                        c7=true;
                    } else if ((btn4.getText() == pl.get(random1) && btn7.getText() == en.get(random1)) || (btn4.getText() == en.get(random1) && btn7.getText() == pl.get(random1))) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setClickable(false);
                        btn7.setClickable(false);
                        click4 = false;
                        click7 = false;
                        c4=true;
                        c7=true;
                    } else if ((btn4.getText() == pl.get(random2) && btn7.getText() == en.get(random2)) || (btn4.getText() == en.get(random2) && btn7.getText() == pl.get(random2))) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setClickable(false);
                        btn7.setClickable(false);
                        click4 = false;
                        click7 = false;
                        c4=true;
                        c7=true;
                    } else if ((btn4.getText() == pl.get(random3) && btn7.getText() == en.get(random3)) || (btn4.getText() == en.get(random3) && btn7.getText() == pl.get(random3))) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setClickable(false);
                        btn7.setClickable(false);
                        click4 = false;
                        click7 = false;
                        c4=true;
                        c7=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn1.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn7.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click4 = false;
                        click7 = false;
                    }
                } else if (click5 == true) {
                    if ((btn5.getText() == pl.get(random0) && btn4.getText() == en.get(random0)) || (btn5.getText() == en.get(random0) && btn4.getText() == pl.get(random0))) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click4 = false;
                        c4=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random1) && btn4.getText() == en.get(random1)) || (btn5.getText() == en.get(random1) && btn4.getText() == pl.get(random1))) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click4 = false;
                        c4=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random2) && btn4.getText() == en.get(random2)) || (btn5.getText() == en.get(random2) && btn4.getText() == pl.get(random2))) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click4 = false;
                        c4=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random3) && btn4.getText() == en.get(random3)) || (btn5.getText() == en.get(random3) && btn4.getText() == pl.get(random3))) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click4 = false;
                        c4=true;
                        c5=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn5.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click5 = false;
                        click4 = false;
                    }
                } else if (click6 == true) {
                    if ((btn6.getText() == pl.get(random0) && btn4.getText() == en.get(random0)) || (btn6.getText() == en.get(random0) && btn4.getText() == pl.get(random0))) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click4 = false;
                        c4=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random1) && btn4.getText() == en.get(random1)) || (btn6.getText() == en.get(random1) && btn4.getText() == pl.get(random1))) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click4 = false;
                        c4=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random2) && btn4.getText() == en.get(random2)) || (btn6.getText() == en.get(random2) && btn4.getText() == pl.get(random2))) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click4 = false;
                        c4=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random3) && btn4.getText() == en.get(random3)) || (btn6.getText() == en.get(random3) && btn4.getText() == pl.get(random3))) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click4 = false;
                        c4=true;
                        c6=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn1.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn6.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click6 = false;
                        click4 = false;
                    }
                } else if (click8 == true) {
                    if ((btn8.getText() == pl.get(random0) && btn4.getText() == en.get(random0)) || (btn8.getText() == en.get(random0) && btn4.getText() == pl.get(random0))) {
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        click8 = false;
                        click4 = false;
                        c4=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random1) && btn4.getText() == en.get(random1)) || (btn8.getText() == en.get(random1) && btn4.getText() == pl.get(random1))) {
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        click8 = false;
                        click4 = false;
                        c4=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random2) && btn4.getText() == en.get(random2)) || (btn8.getText() == en.get(random2) && btn4.getText() == pl.get(random2))) {
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        click8 = false;
                        click4 = false;
                        c4=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random3) && btn4.getText() == en.get(random3)) || (btn8.getText() == en.get(random3) && btn4.getText() == pl.get(random3))) {
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        click8 = false;
                        click4 = false;
                        c4=true;
                        c8=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn1.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn4.setText(" ");
                                btn8.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click8 = false;
                        click4 = false;
                    }
                }
                sprawdz();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click5 = true;

                btn5.setText(pl.get(lista_randomowych.get(b5)));

                //btn5.setText(zakreskowaneSlowoTV.get(random1));

                if (click1 == true) {
                    if ((btn1.getText() == pl.get(random0) && btn5.getText() == en.get(random0)) || (btn1.getText() == en.get(random0) && btn5.getText() == pl.get(random0))) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click5 = false;
                        c5=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random1) && btn5.getText() == en.get(random1)) || (btn1.getText() == en.get(random1) && btn5.getText() == pl.get(random1))) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click5 = false;
                        c5=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random2) && btn5.getText() == en.get(random2)) || (btn1.getText() == en.get(random2) && btn5.getText() == pl.get(random2))) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click5 = false;
                        c5=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random3) && btn5.getText() == en.get(random3)) || (btn1.getText() == en.get(random3) && btn5.getText() == pl.get(random3))) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click5 = false;
                        c5=true;
                        c1=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn1.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click1 = false;
                        click5 = false;
                    }
                } else if (click2 == true) {
                    if ((btn2.getText() == pl.get(random0) && btn5.getText() == en.get(random0)) || (btn2.getText() == en.get(random0) && btn5.getText() == pl.get(random0))) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click5 = false;
                        c5=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random1) && btn5.getText() == en.get(random1)) || (btn2.getText() == en.get(random1) && btn5.getText() == pl.get(random1))) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click5 = false;
                        c5=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random2) && btn5.getText() == en.get(random2)) || (btn2.getText() == en.get(random2) && btn5.getText() == pl.get(random2))) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click5 = false;
                        c5=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random3) && btn5.getText() == en.get(random3)) || (btn2.getText() == en.get(random3) && btn5.getText() == pl.get(random3))) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click5 = false;
                        c5=true;
                        c2=true;
                    } else {
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn2.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click2 = false;
                        click5 = false;
                    }
                } else if (click3 == true) {
                    if ((btn3.getText() == pl.get(random0) && btn5.getText() == en.get(random0)) || (btn3.getText() == en.get(random0) && btn5.getText() == pl.get(random0))) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click5 = false;
                        c5=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random1) && btn5.getText() == en.get(random1)) || (btn3.getText() == en.get(random1) && btn5.getText() == pl.get(random1))) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click5 = false;
                        c5=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random2) && btn5.getText() == en.get(random2)) || (btn3.getText() == en.get(random2) && btn5.getText() == pl.get(random2))) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click5 = false;
                        c5=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random3) && btn5.getText() == en.get(random3)) || (btn3.getText() == en.get(random3) && btn5.getText() == pl.get(random3))) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click5 = false;
                        c5=true;
                        c3=true;
                    } else {
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        btn4.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn3.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click3 = false;
                        click5 = false;
                    }
                } else if (click4 == true) {
                    if ((btn4.getText() == pl.get(random0) && btn5.getText() == en.get(random0)) || (btn4.getText() == en.get(random0) && btn5.getText() == pl.get(random0))) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click5 = false;
                        c5=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random1) && btn5.getText() == en.get(random1)) || (btn4.getText() == en.get(random1) && btn5.getText() == pl.get(random1))) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click5 = false;
                        c5=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random2) && btn5.getText() == en.get(random2)) || (btn4.getText() == en.get(random2) && btn5.getText() == pl.get(random2))) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click5 = false;
                        c5=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random3) && btn5.getText() == en.get(random3)) || (btn4.getText() == en.get(random3) && btn5.getText() == pl.get(random3))) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click5 = false;
                        c5=true;
                        c4=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn4.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click4 = false;
                        click5 = false;
                    }
                } else if (click7 == true) {
                    if ((btn5.getText() == pl.get(random0) && btn7.getText() == en.get(random0)) || (btn5.getText() == en.get(random0) && btn7.getText() == pl.get(random0))) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        click5 = false;
                        click7 = false;
                        c5=true;
                        c7=true;
                    } else if ((btn5.getText() == pl.get(random1) && btn7.getText() == en.get(random1)) || (btn5.getText() == en.get(random1) && btn7.getText() == pl.get(random1))) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        click5 = false;
                        click7 = false;
                        c5=true;
                        c7=true;
                    } else if ((btn5.getText() == pl.get(random2) && btn7.getText() == en.get(random2)) || (btn5.getText() == en.get(random2) && btn7.getText() == pl.get(random2))) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        click5 = false;
                        click7 = false;
                        c5=true;
                        c7=true;
                    } else if ((btn5.getText() == pl.get(random3) && btn7.getText() == en.get(random3)) || (btn5.getText() == en.get(random3) && btn7.getText() == pl.get(random3))) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        click5 = false;
                        click7 = false;
                        c5=true;
                        c7=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn1.setClickable(false);
                        btn4.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn7.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click5 = false;
                        click7 = false;
                    }
                } else if (click6 == true) {
                    if ((btn6.getText() == pl.get(random0) && btn5.getText() == en.get(random0)) || (btn6.getText() == en.get(random0) && btn5.getText() == pl.get(random0))) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click5 = false;
                        c5=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random1) && btn5.getText() == en.get(random1)) || (btn6.getText() == en.get(random1) && btn5.getText() == pl.get(random1))) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click5 = false;
                        c5=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random2) && btn5.getText() == en.get(random2)) || (btn6.getText() == en.get(random2) && btn5.getText() == pl.get(random2))) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click5 = false;
                        c5=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random3) && btn5.getText() == en.get(random3)) || (btn6.getText() == en.get(random3) && btn5.getText() == pl.get(random3))) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click5 = false;
                        c5=true;
                        c6=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn6.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click6 = false;
                        click5 = false;
                    }
                } else if (click8 == true) {
                    if ((btn8.getText() == pl.get(random0) && btn5.getText() == en.get(random0)) || (btn8.getText() == en.get(random0) && btn5.getText() == pl.get(random0))) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn5.setClickable(false);
                        btn8.setClickable(false);
                        click5 = false;
                        click8 = false;
                        c5=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random1) && btn5.getText() == en.get(random1)) || (btn8.getText() == en.get(random1) && btn5.getText() == pl.get(random1))) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn5.setClickable(false);
                        btn8.setClickable(false);
                        click5 = false;
                        click8 = false;
                        c5=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random2) && btn5.getText() == en.get(random2)) || (btn8.getText() == en.get(random2) && btn5.getText() == pl.get(random2))) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn5.setClickable(false);
                        btn8.setClickable(false);
                        click5 = false;
                        click8 = false;
                        c5=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random3) && btn5.getText() == en.get(random3)) || (btn8.getText() == en.get(random3) && btn5.getText() == pl.get(random3))) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn5.setClickable(false);
                        btn8.setClickable(false);
                        click5 = false;
                        click8 = false;
                        c5=true;
                        c8=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn5.setText(" ");
                                btn8.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click5 = false;
                        click8 = false;
                    }
                }
                sprawdz();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click6 = true;

                btn6.setText(en.get(lista_randomowych.get(b6)));

                //btn6.setText(brakujaceLiteryET.get(random0));

                if (click1 == true) {
                    if ((btn1.getText() == pl.get(random0) && btn6.getText() == en.get(random0)) || (btn1.getText() == en.get(random0) && btn6.getText() == pl.get(random0))) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click6 = false;
                        c6=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random1) && btn6.getText() == en.get(random1)) || (btn1.getText() == en.get(random1) && btn6.getText() == pl.get(random1))) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click6 = false;
                        c6=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random2) && btn6.getText() == en.get(random2)) || (btn1.getText() == en.get(random2) && btn6.getText() == pl.get(random2))) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click6 = false;
                        c6=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random3) && btn6.getText() == en.get(random3)) || (btn1.getText() == en.get(random3) && btn6.getText() == pl.get(random3))) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click6 = false;
                        c6=true;
                        c1=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn1.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click1 = false;
                        click6 = false;
                    }
                } else if (click2 == true) {
                    if ((btn2.getText() == pl.get(random0) && btn6.getText() == en.get(random0)) || (btn2.getText() == en.get(random0) && btn6.getText() == pl.get(random0))) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click6 = false;
                        c6=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random1) && btn6.getText() == en.get(random1)) || (btn2.getText() == en.get(random1) && btn6.getText() == pl.get(random1))) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click6 = false;
                        c6=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random2) && btn6.getText() == en.get(random2)) || (btn2.getText() == en.get(random2) && btn6.getText() == pl.get(random2))) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click6 = false;
                        c6=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random3) && btn6.getText() == en.get(random3)) || (btn2.getText() == en.get(random3) && btn6.getText() == pl.get(random3))) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click6 = false;
                        c6=true;
                        c2=true;
                    } else {
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn2.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click2 = false;
                        click6 = false;
                    }
                } else if (click3 == true) {
                    if ((btn3.getText() == pl.get(random0) && btn6.getText() == en.get(random0)) || (btn3.getText() == en.get(random0) && btn6.getText() == pl.get(random0))) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click6 = false;
                        c6=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random1) && btn6.getText() == en.get(random1)) || (btn3.getText() == en.get(random1) && btn6.getText() == pl.get(random1))) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click6 = false;
                        c6=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random2) && btn6.getText() == en.get(random2)) || (btn3.getText() == en.get(random2) && btn6.getText() == pl.get(random2))) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click6 = false;
                        c6=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random3) && btn6.getText() == en.get(random3)) || (btn3.getText() == en.get(random3) && btn6.getText() == pl.get(random3))) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click6 = false;
                        c6=true;
                        c3=true;
                    } else {
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn3.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click3 = false;
                        click6 = false;
                    }
                } else if (click4 == true) {
                    if ((btn4.getText() == pl.get(random0) && btn6.getText() == en.get(random0)) || (btn4.getText() == en.get(random0) && btn6.getText() == pl.get(random0))) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click6 = false;
                        c6=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random1) && btn6.getText() == en.get(random1)) || (btn4.getText() == en.get(random1) && btn6.getText() == pl.get(random1))) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click6 = false;
                        c6=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random2) && btn6.getText() == en.get(random2)) || (btn4.getText() == en.get(random2) && btn6.getText() == pl.get(random2))) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click6 = false;
                        c6=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random3) && btn6.getText() == en.get(random3)) || (btn4.getText() == en.get(random3) && btn6.getText() == pl.get(random3))) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click6 = false;
                        c6=true;
                        c4=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn1.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn4.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click4 = false;
                        click6 = false;
                    }
                } else if (click5 == true) {
                    if ((btn5.getText() == pl.get(random0) && btn6.getText() == en.get(random0)) || (btn5.getText() == en.get(random0) && btn6.getText() == pl.get(random0))) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click6 = false;
                        c6=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random1) && btn6.getText() == en.get(random1)) || (btn5.getText() == en.get(random1) && btn6.getText() == pl.get(random1))) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click6 = false;
                        c6=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random2) && btn6.getText() == en.get(random2)) || (btn5.getText() == en.get(random2) && btn6.getText() == pl.get(random2))) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click6 = false;
                        c6=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random3) && btn6.getText() == en.get(random3)) || (btn5.getText() == en.get(random3) && btn6.getText() == pl.get(random3))) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click6 = false;
                        c6=true;
                        c5=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn1.setClickable(false);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn5.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click5 = false;
                        click6 = false;
                    }
                } else if (click7 == true) {
                    if ((btn7.getText() == pl.get(random0) && btn6.getText() == en.get(random0)) || (btn7.getText() == en.get(random0) && btn6.getText() == pl.get(random0))) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setClickable(false);
                        btn7.setClickable(false);
                        click6 = false;
                        click7 = false;
                        c6=true;
                        c7=true;
                    } else if ((btn7.getText() == pl.get(random1) && btn6.getText() == en.get(random1)) || (btn7.getText() == en.get(random1) && btn6.getText() == pl.get(random1))) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setClickable(false);
                        btn7.setClickable(false);
                        click6 = false;
                        click7 = false;
                        c6=true;
                        c7=true;
                    } else if ((btn7.getText() == pl.get(random2) && btn6.getText() == en.get(random2)) || (btn7.getText() == en.get(random2) && btn6.getText() == pl.get(random2))) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setClickable(false);
                        btn7.setClickable(false);
                        click6 = false;
                        click7 = false;
                        c6=true;
                        c7=true;
                    } else if ((btn7.getText() == pl.get(random3) && btn6.getText() == en.get(random3)) || (btn7.getText() == en.get(random3) && btn6.getText() == pl.get(random3))) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setClickable(false);
                        btn7.setClickable(false);
                        click6 = false;
                        click7 = false;
                        c6=true;
                        c7=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn1.setClickable(false);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn7.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click6 = false;
                        click7 = false;
                    }
                } else if (click8 == true) {
                    if ((btn8.getText() == pl.get(random0) && btn6.getText() == en.get(random0)) || (btn8.getText() == en.get(random0) && btn6.getText() == pl.get(random0))) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        click6 = false;
                        click8 = false;
                        c6=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random1) && btn6.getText() == en.get(random1)) || (btn8.getText() == en.get(random1) && btn6.getText() == pl.get(random1))) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        click6 = false;
                        click8 = false;
                        c6=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random2) && btn6.getText() == en.get(random2)) || (btn8.getText() == en.get(random2) && btn6.getText() == pl.get(random2))) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        click6 = false;
                        click8 = false;
                        c6=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random3) && btn6.getText() == en.get(random3)) || (btn8.getText() == en.get(random3) && btn6.getText() == pl.get(random3))) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        click6 = false;
                        click8 = false;
                        c6=true;
                        c8=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn1.setClickable(false);
                        btn4.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn6.setText(" ");
                                btn8.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click6 = false;
                        click8 = false;
                    }
                }
                sprawdz();
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click7 = true;

                btn7.setText(pl.get(lista_randomowych.get(b7)));

                //btn7.setText(zakreskowaneSlowoTV.get(random2));

                if (click1 == true) {
                    if ((btn1.getText() == pl.get(random0) && btn7.getText() == en.get(random0)) || (btn1.getText() == en.get(random0) && btn7.getText() == pl.get(random0))) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click7 = false;
                        c7=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random1) && btn7.getText() == en.get(random1)) || (btn1.getText() == en.get(random1) && btn7.getText() == pl.get(random1))) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click7 = false;
                        c7=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random2) && btn7.getText() == en.get(random2)) || (btn1.getText() == en.get(random2) && btn7.getText() == pl.get(random2))) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click7 = false;
                        c7=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random3) && btn7.getText() == en.get(random3)) || (btn1.getText() == en.get(random3) && btn7.getText() == pl.get(random3))) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click7 = false;
                        c7=true;
                        c1=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn4.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn1.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click1 = false;
                        click7 = false;
                    }
                } else if (click2 == true) {
                    if ((btn2.getText() == pl.get(random0) && btn7.getText() == en.get(random0)) || (btn2.getText() == en.get(random0) && btn7.getText() == pl.get(random0))) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click7 = false;
                        c7=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random1) && btn7.getText() == en.get(random1)) || (btn2.getText() == en.get(random1) && btn7.getText() == pl.get(random1))) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click7 = false;
                        c7=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random2) && btn7.getText() == en.get(random2)) || (btn2.getText() == en.get(random2) && btn7.getText() == pl.get(random2))) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click7 = false;
                        c7=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random3) && btn7.getText() == en.get(random3)) || (btn2.getText() == en.get(random3) && btn7.getText() == pl.get(random3))) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click7 = false;
                        c7=true;
                        c2=true;
                    } else {
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn4.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn2.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click2 = false;
                        click7 = false;
                    }
                } else if (click3 == true) {
                    if ((btn3.getText() == pl.get(random0) && btn7.getText() == en.get(random0)) || (btn3.getText() == en.get(random0) && btn7.getText() == pl.get(random0))) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click7 = false;
                        c7=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random1) && btn7.getText() == en.get(random1)) || (btn3.getText() == en.get(random1) && btn7.getText() == pl.get(random1))) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click7 = false;
                        c7=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random2) && btn7.getText() == en.get(random2)) || (btn3.getText() == en.get(random2) && btn7.getText() == pl.get(random2))) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click7 = false;
                        c7=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random3) && btn7.getText() == en.get(random3)) || (btn3.getText() == en.get(random3) && btn7.getText() == pl.get(random3))) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click7 = false;
                        c7=true;
                        c3=true;
                    } else {
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        btn5.setClickable(false);
                        btn4.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn3.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click3 = false;
                        click7 = false;
                    }
                } else if (click4 == true) {
                    if ((btn4.getText() == pl.get(random0) && btn7.getText() == en.get(random0)) || (btn4.getText() == en.get(random0) && btn7.getText() == pl.get(random0))) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click7 = false;
                        c7=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random1) && btn7.getText() == en.get(random1)) || (btn4.getText() == en.get(random1) && btn7.getText() == pl.get(random1))) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click7 = false;
                        c7=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random2) && btn7.getText() == en.get(random2)) || (btn4.getText() == en.get(random2) && btn7.getText() == pl.get(random2))) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click7 = false;
                        c7=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random3) && btn7.getText() == en.get(random3)) || (btn4.getText() == en.get(random3) && btn7.getText() == pl.get(random3))) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click7 = false;
                        c7=true;
                        c4=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn1.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn4.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click4 = false;
                        click7 = false;
                    }
                } else if (click5 == true) {
                    if ((btn5.getText() == pl.get(random0) && btn7.getText() == en.get(random0)) || (btn5.getText() == en.get(random0) && btn7.getText() == pl.get(random0))) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click7 = false;
                        c7=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random1) && btn7.getText() == en.get(random1)) || (btn5.getText() == en.get(random1) && btn7.getText() == pl.get(random1))) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click7 = false;
                        c7=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random2) && btn7.getText() == en.get(random2)) || (btn5.getText() == en.get(random2) && btn7.getText() == pl.get(random2))) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click7 = false;
                        c7=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random3) && btn7.getText() == en.get(random3)) || (btn5.getText() == en.get(random3) && btn7.getText() == pl.get(random3))) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click7 = false;
                        c7=true;
                        c5=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn1.setClickable(false);
                        btn4.setClickable(false);
                        btn6.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn5.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click5 = false;
                        click7 = false;
                    }
                } else if (click6 == true) {
                    if ((btn6.getText() == pl.get(random0) && btn7.getText() == en.get(random0)) || (btn6.getText() == en.get(random0) && btn7.getText() == pl.get(random0))) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click7 = false;
                        c7=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random1) && btn7.getText() == en.get(random1)) || (btn6.getText() == en.get(random1) && btn7.getText() == pl.get(random1))) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click7 = false;
                        c7=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random2) && btn7.getText() == en.get(random2)) || (btn6.getText() == en.get(random2) && btn7.getText() == pl.get(random2))) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click7 = false;
                        c7=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random3) && btn7.getText() == en.get(random3)) || (btn6.getText() == en.get(random3) && btn7.getText() == pl.get(random3))) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click7 = false;
                        c7=true;
                        c6=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn1.setClickable(false);
                        btn4.setClickable(false);
                        btn8.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn6.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click6 = false;
                        click7 = false;
                    }
                } else if (click8 == true) {
                    if ((btn8.getText() == pl.get(random0) && btn7.getText() == en.get(random0)) || (btn8.getText() == en.get(random0) && btn7.getText() == pl.get(random0))) {
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn7.setClickable(false);
                        btn8.setClickable(false);
                        click7 = false;
                        click8 = false;
                        c7=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random1) && btn7.getText() == en.get(random1)) || (btn8.getText() == en.get(random1) && btn7.getText() == pl.get(random1))) {
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn7.setClickable(false);
                        btn8.setClickable(false);
                        click7 = false;
                        click8 = false;
                        c7=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random2) && btn7.getText() == en.get(random2)) || (btn8.getText() == en.get(random2) && btn7.getText() == pl.get(random2))) {
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn7.setClickable(false);
                        btn8.setClickable(false);
                        click7 = false;
                        click8 = false;
                        c7=true;
                        c8=true;
                    } else if ((btn8.getText() == pl.get(random3) && btn7.getText() == en.get(random3)) || (btn8.getText() == en.get(random3) && btn7.getText() == pl.get(random3))) {
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn7.setClickable(false);
                        btn8.setClickable(false);
                        click7 = false;
                        click8 = false;
                        c7=true;
                        c8=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn1.setClickable(false);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn7.setText(" ");
                                btn8.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click7 = false;
                        click8 = false;
                    }
                }
                sprawdz();
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click8 = true;

                btn8.setText(en.get(lista_randomowych.get(b8)));

                //btn8.setText(brakujaceLiteryET.get(random1));

                if (click1 == true) {
                    if ((btn1.getText() == pl.get(random0) && btn8.getText() == en.get(random0)) || (btn1.getText() == en.get(random0) && btn8.getText() == pl.get(random0))) {
                        btn1.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click8 = false;
                        c8=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random1) && btn8.getText() == en.get(random1)) || (btn1.getText() == en.get(random1) && btn8.getText() == pl.get(random1))) {
                        btn1.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click8 = false;
                        c8=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random2) && btn8.getText() == en.get(random2)) || (btn1.getText() == en.get(random2) && btn8.getText() == pl.get(random2))) {
                        btn1.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click8 = false;
                        c8=true;
                        c1=true;
                    } else if ((btn1.getText() == pl.get(random3) && btn8.getText() == en.get(random3)) || (btn1.getText() == en.get(random3) && btn8.getText() == pl.get(random3))) {
                        btn1.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setClickable(false);
                        btn1.setClickable(false);
                        click1 = false;
                        click8 = false;
                        c8=true;
                        c1=true;
                    } else {
                        btn2.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn1.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click1 = false;
                        click8 = false;
                    }
                } else if (click2 == true) {
                    if ((btn2.getText() == pl.get(random0) && btn8.getText() == en.get(random0)) || (btn2.getText() == en.get(random0) && btn8.getText() == pl.get(random0))) {
                        btn2.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click8 = false;
                        c8=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random1) && btn8.getText() == en.get(random1)) || (btn2.getText() == en.get(random1) && btn8.getText() == pl.get(random1))) {
                        btn2.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click8 = false;
                        c8=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random2) && btn8.getText() == en.get(random2)) || (btn2.getText() == en.get(random2) && btn8.getText() == pl.get(random2))) {
                        btn2.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click8 = false;
                        c8=true;
                        c2=true;
                    } else if ((btn2.getText() == pl.get(random3) && btn8.getText() == en.get(random3)) || (btn2.getText() == en.get(random3) && btn8.getText() == pl.get(random3))) {
                        btn2.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setClickable(false);
                        btn2.setClickable(false);
                        click2 = false;
                        click8 = false;
                        c8=true;
                        c2=true;
                    } else {
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn2.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click2 = false;
                        click8 = false;
                    }
                } else if (click3 == true) {
                    if ((btn3.getText() == pl.get(random0) && btn8.getText() == en.get(random0)) || (btn3.getText() == en.get(random0) && btn8.getText() == pl.get(random0))) {
                        btn3.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click8 = false;
                        c8=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random1) && btn8.getText() == en.get(random1)) || (btn3.getText() == en.get(random1) && btn8.getText() == pl.get(random1))) {
                        btn3.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click8 = false;
                        c8=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random2) && btn8.getText() == en.get(random2)) || (btn3.getText() == en.get(random2) && btn8.getText() == pl.get(random2))) {
                        btn3.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click8 = false;
                        c8=true;
                        c3=true;
                    } else if ((btn3.getText() == pl.get(random3) && btn8.getText() == en.get(random3)) || (btn3.getText() == en.get(random3) && btn8.getText() == pl.get(random3))) {
                        btn3.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setClickable(false);
                        btn3.setClickable(false);
                        click3 = false;
                        click8 = false;
                        c8=true;
                        c3=true;
                    } else {
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn3.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click3 = false;
                        click8 = false;
                    }
                } else if (click4 == true) {
                    if ((btn4.getText() == pl.get(random0) && btn8.getText() == en.get(random0)) || (btn4.getText() == en.get(random0) && btn8.getText() == pl.get(random0))) {
                        btn4.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click8 = false;
                        c8=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random1) && btn8.getText() == en.get(random1)) || (btn4.getText() == en.get(random1) && btn8.getText() == pl.get(random1))) {
                        btn4.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click8 = false;
                        c8=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random2) && btn8.getText() == en.get(random2)) || (btn4.getText() == en.get(random2) && btn8.getText() == pl.get(random2))) {
                        btn4.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click8 = false;
                        c8=true;
                        c4=true;
                    } else if ((btn4.getText() == pl.get(random3) && btn8.getText() == en.get(random3)) || (btn4.getText() == en.get(random3) && btn8.getText() == pl.get(random3))) {
                        btn4.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setClickable(false);
                        btn4.setClickable(false);
                        click4 = false;
                        click8 = false;
                        c8=true;
                        c4=true;
                    } else {
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn3.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn4.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click4 = false;
                        click8 = false;
                    }
                } else if (click5 == true) {
                    if ((btn5.getText() == pl.get(random0) && btn8.getText() == en.get(random0)) || (btn5.getText() == en.get(random0) && btn8.getText() == pl.get(random0))) {
                        btn5.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click8 = false;
                        c8=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random1) && btn8.getText() == en.get(random1)) || (btn5.getText() == en.get(random1) && btn8.getText() == pl.get(random1))) {
                        btn5.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click8 = false;
                        c8=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random2) && btn8.getText() == en.get(random2)) || (btn5.getText() == en.get(random2) && btn8.getText() == pl.get(random2))) {
                        btn5.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click8 = false;
                        c8=true;
                        c5=true;
                    } else if ((btn5.getText() == pl.get(random3) && btn8.getText() == en.get(random3)) || (btn5.getText() == en.get(random3) && btn8.getText() == pl.get(random3))) {
                        btn5.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setClickable(false);
                        btn5.setClickable(false);
                        click5 = false;
                        click8 = false;
                        c8=true;
                        c5=true;
                    } else {
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        btn3.setClickable(false);
                        btn7.setClickable(false);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn5.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click5 = false;
                        click8 = false;
                    }
                } else if (click6 == true) {
                    if ((btn6.getText() == pl.get(random0) && btn8.getText() == en.get(random0)) || (btn6.getText() == en.get(random0) && btn8.getText() == pl.get(random0))) {
                        btn6.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click8 = false;
                        c8=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random1) && btn8.getText() == en.get(random1)) || (btn6.getText() == en.get(random1) && btn8.getText() == pl.get(random1))) {
                        btn6.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click8 = false;
                        c8=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random2) && btn8.getText() == en.get(random2)) || (btn6.getText() == en.get(random2) && btn8.getText() == pl.get(random2))) {
                        btn6.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click8 = false;
                        c8=true;
                        c6=true;
                    } else if ((btn6.getText() == pl.get(random3) && btn8.getText() == en.get(random3)) || (btn6.getText() == en.get(random3) && btn8.getText() == pl.get(random3))) {
                        btn6.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setClickable(false);
                        btn6.setClickable(false);
                        click6 = false;
                        click8 = false;
                        c8=true;
                        c6=true;
                    } else {
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        btn5.setClickable(false);
                        btn7.setClickable(false);
                        btn3.setClickable(false);
                        btn4.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn6.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click6 = false;
                        click8 = false;
                    }
                } else if (click7 == true) {
                    if ((btn7.getText() == pl.get(random0) && btn8.getText() == en.get(random0)) || (btn7.getText() == en.get(random0) && btn8.getText() == pl.get(random0))) {
                        btn7.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setBackgroundResource(R.drawable.button_border_blue);
                        btn8.setClickable(false);
                        btn7.setClickable(false);
                        click7 = false;
                        click8 = false;
                        c8=true;
                        c7=true;
                    } else if ((btn7.getText() == pl.get(random1) && btn8.getText() == en.get(random1)) || (btn7.getText() == en.get(random1) && btn8.getText() == pl.get(random1))) {
                        btn7.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setBackgroundResource(R.drawable.button_border_pink);
                        btn8.setClickable(false);
                        btn7.setClickable(false);
                        click7 = false;
                        click8 = false;
                        c8=true;
                        c7=true;
                    } else if ((btn7.getText() == pl.get(random2) && btn8.getText() == en.get(random2)) || (btn7.getText() == en.get(random2) && btn8.getText() == pl.get(random2))) {
                        btn7.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setBackgroundResource(R.drawable.button_border_yellow);
                        btn8.setClickable(false);
                        btn7.setClickable(false);
                        click7 = false;
                        click8 = false;
                        c8=true;
                        c7=true;
                    } else if ((btn7.getText() == pl.get(random3) && btn8.getText() == en.get(random3)) || (btn7.getText() == en.get(random3) && btn8.getText() == pl.get(random3))) {
                        btn7.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setBackgroundResource(R.drawable.button_border_purple);
                        btn8.setClickable(false);
                        btn7.setClickable(false);
                        click7 = false;
                        click8 = false;
                        c8=true;
                        c7=true;
                    } else {
                        btn2.setClickable(false);
                        btn1.setClickable(false);
                        btn5.setClickable(false);
                        btn3.setClickable(false);
                        btn6.setClickable(false);
                        btn4.setClickable(false);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                btn8.setText(" ");
                                btn7.setText(" ");
                                btn1.setClickable(true);
                                btn2.setClickable(true);
                                btn3.setClickable(true);
                                btn4.setClickable(true);
                                btn5.setClickable(true);
                                btn6.setClickable(true);
                                btn7.setClickable(true);
                                btn8.setClickable(true);
                            }
                        }, 1000);
                        click7 = false;
                        click8 = false;
                    }
                }
                sprawdz();
            }
        });


        dalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==5){
                    startActivity(new Intent(PlanszaKwadratowa.this,WyborKategorii.class));
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            i=0;
                        }
                    }, 300);
                }
                if(i<=4) {
                    startActivity(new Intent(PlanszaKwadratowa.this, PlanszaKwadratowa.class));
                }
                i++; }
        });
    }

    public void sprawdz()
    {
        if(c1==true&&c2==true&&c3==true&&c4==true&&c5==true&&c6==true&&c7==true&&c8==true)
        {
            dalej.setVisibility(View.VISIBLE);

        }
    }
    public void onBackPressed() {
        startActivity(new Intent(PlanszaKwadratowa.this, WyborKategorii.class));
    }
}
