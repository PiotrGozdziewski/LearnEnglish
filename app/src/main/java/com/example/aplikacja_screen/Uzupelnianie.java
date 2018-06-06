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
import android.text.InputFilter;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Uzupelnianie extends AppCompatActivity {

    private static final String TAG = "Uzupelnianie";

    Button sprawdzPoprawnoscButton;
    Button dalejButton;
    Button podpowiedzButton;

    TextView zakreskowaneSlowoTV;
    TextView polskieSlowoTV;
    EditText brakujaceLiteryET;

    ArrayList<String> polskie;
    ArrayList<String> angielskie;

    Database db;
    int i = 0;
    int poprawne_odp = 0;
    int bledne_odp = 0;

    Boolean bledna = false;
    Handler h = new Handler();

    Cursor cursor;

    int id_typu_zadania;
    int idKategorii;
    int id_zadania;

    String slowoZakreskowane;
    String brakujaceLitery;
    String podpowiedz;
    String wylosowanePl;
    String wylosowaneEn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uzupelnianie);

        sprawdzPoprawnoscButton = (Button) findViewById(R.id.sprPoprawnoscButton);
        dalejButton = (Button) findViewById(R.id.dalejButton);
        podpowiedzButton = (Button) findViewById(R.id.podpowiedzButton);
        zakreskowaneSlowoTV = (TextView) findViewById(R.id.slowoZakreskowane);
        polskieSlowoTV = (TextView) findViewById(R.id.slowoPolskie);
        brakujaceLiteryET = (EditText) findViewById(R.id.brakujaceLitery);

        polskie = new ArrayList<>();
        angielskie = new ArrayList<>();
        db = new Database(getContentResolver());

        // pobranie id aktualnej kategorii
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        idKategorii = p.getInt("idKategorii", 0);
        String wybranyTypZadania = p.getString("NazwaTypuZadania", "0");

        cursor = db.getExerciceType(wybranyTypZadania);
        while (cursor.moveToNext()) {
            String nazwa_baza = cursor.getString(1);
            if (wybranyTypZadania.equals(nazwa_baza)) {
                id_typu_zadania = cursor.getInt(0);
            }
        }
        cursor.close();

        cursor = db.getWords(idKategorii);
        while (cursor.moveToNext()) {
            polskie.add(cursor.getString(2));
            angielskie.add(cursor.getString(3));
        }
        cursor.close();

        cursor = db.getExercise(idKategorii, id_typu_zadania);
        while (cursor.moveToNext()) {
            id_zadania = cursor.getInt(0);
        }

        wylosujSlowo();

        zakreskowaneSlowoTV.setText(slowoZakreskowane);
        polskieSlowoTV.setText(wylosowanePl);

        sprawdzPoprawnoscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (brakujaceLiteryET.getText().toString().equals(brakujaceLitery)) {

                    podpowiedzButton.setVisibility(View.INVISIBLE);
                    sprawdzPoprawnoscButton.setText("Poprawna odpowiedź");
                    sprawdzPoprawnoscButton.setBackgroundColor(Color.parseColor("#38ea3e"));
                    if (bledna == false) {
                        poprawne_odp++;
                    }
                    sprawdzPoprawnoscButton.setClickable(false);
                    dalejButton.setVisibility(View.VISIBLE);
                } else {
                    sprawdzPoprawnoscButton.setText("Niepoprawna odpowiedź");
                    sprawdzPoprawnoscButton.setBackgroundColor(Color.parseColor("#d11f34"));
                    if (bledna == false) {
                        bledne_odp++;
                        bledna = true;
                    }
                    podpowiedzButton.setVisibility(View.VISIBLE);
                    sprawdzPoprawnoscButton.setClickable(false);
                    podpowiedzButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog alertDialog = new AlertDialog.Builder(Uzupelnianie.this).create();
                            alertDialog.setTitle("Poprawna odpowiedz");
                            alertDialog.setMessage(podpowiedz);
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                            sprawdzPoprawnoscButton.setText("Sprawdź poprawność");
                            sprawdzPoprawnoscButton.setBackgroundColor(Color.parseColor("#e6e1e1"));
                        }
                    });

                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sprawdzPoprawnoscButton.setText("Sprawdź poprawność");
                            //podpowiedzButton.setVisibility(View.VISIBLE);
                            sprawdzPoprawnoscButton.setVisibility(View.VISIBLE);
                            sprawdzPoprawnoscButton.setClickable(true);
                            sprawdzPoprawnoscButton.setBackgroundColor(Color.parseColor("#e6e1e1"));
                        }
                    }, 3000);
                }
            }
        });

        dalejButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 9) {
                    zapisz_statytyski();
                    startActivity(new Intent(Uzupelnianie.this, WyborKategorii.class));
                }
                brakujaceLiteryET.setText("");
                sprawdzPoprawnoscButton.setClickable(true);
                sprawdzPoprawnoscButton.setText("Sprawdź poprawność");
                sprawdzPoprawnoscButton.setBackgroundColor(Color.parseColor("#e6e1e1"));
                i++;
                bledna = false;
                dalejButton.setVisibility(View.INVISIBLE);
                if (i <= 9) {
                    ustawZakreskowane();
                }
                if (i == 9) {
                    dalejButton.setText("Zakończ lekcje");
                }
            }
        });


    }

    public void wylosujSlowo() {
        Random random = new Random();
        int rand = random.nextInt(polskie.size());

        wylosowaneEn = angielskie.get(rand);
        angielskie.remove(rand);

        wylosowanePl = polskie.get(rand);
        polskie.remove(rand);

        podpowiedz = wylosowaneEn;

        zakreskuj();
    }

    private void zakreskuj() {
        int length = wylosowaneEn.length();
        Random random = new Random();
        int rand = 0;
        brakujaceLitery = "";
        ArrayList<Integer> uzyteIdx = new ArrayList<>();

        int ileZakreskowac = 1;

        if (length < 5) {
            ileZakreskowac = 1;
        } else if (length > 4 && length < 8) {
            ileZakreskowac = 2;
        } else if (length > 8 && length < 11) {
            ileZakreskowac = 3;
        } else {
            ileZakreskowac = 4;
        }

        brakujaceLiteryET.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ileZakreskowac)});

        StringBuilder sb = new StringBuilder(wylosowaneEn);

        for (int i = 0; i < ileZakreskowac; i++) {
            rand = random.nextInt(length);
            while (uzyteIdx.contains(rand) || wylosowaneEn.charAt(rand) == ' ') {
                rand = random.nextInt(length);
            }
            uzyteIdx.add(rand);
        }

        Collections.sort(uzyteIdx);

        for (int i = 0; i < ileZakreskowac; i++) {
            sb.setCharAt(uzyteIdx.get(i), '-');
            brakujaceLitery += wylosowaneEn.charAt(uzyteIdx.get(i));
        }

        slowoZakreskowane = sb.toString();

        Log.d(TAG, "zakreskuj: " + brakujaceLitery);
    }

    public void ustawZakreskowane() {
        wylosujSlowo();
        zakreskowaneSlowoTV.setText(slowoZakreskowane);
        polskieSlowoTV.setText(wylosowanePl);
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
        startActivity(new Intent(Uzupelnianie.this, WyborKategorii.class));
    }
}
