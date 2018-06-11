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
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
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
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Uzupelnianie extends AppCompatActivity {

    private static final String TAG = "Uzupelnianie";

    Button sprawdzPoprawnoscButton;
    Button dalejButton;
    Button podpowiedzButton;
    Button clear;

    TextView zakreskowaneSlowoTV;
    TextView polskieSlowoTV;
    TextView poprawnaEN;
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

    ArrayList<Integer>kreski_index;
    int zmienna=0;

    ProgressBar pb;
    int progress=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uzupelnianie);

        sprawdzPoprawnoscButton = (Button) findViewById(R.id.sprPoprawnoscButton);
        dalejButton = (Button) findViewById(R.id.dalejButton);
        podpowiedzButton = (Button) findViewById(R.id.podpowiedzButton);
        clear = (Button)findViewById(R.id.clear);
        zakreskowaneSlowoTV = (TextView) findViewById(R.id.slowoZakreskowane);
        polskieSlowoTV = (TextView) findViewById(R.id.slowoPolskie);
        poprawnaEN = (TextView)findViewById(R.id.poprawnaEN);
        brakujaceLiteryET = (EditText) findViewById(R.id.brakujaceLitery);
        pb = (ProgressBar)findViewById(R.id.progressBar4);
        pb.setMax(9);

        polskie = new ArrayList<>();
        angielskie = new ArrayList<>();
        kreski_index = new ArrayList<Integer>();
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


        brakujaceLiteryET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str = zakreskowaneSlowoTV.getText().toString();
                if(charSequence.length()!=0 && zmienna<= str.length()){
                    String literka=charSequence.toString();
                    String nowy_literka_poprawna=String.valueOf(literka.charAt(zmienna));
                    String nowy_literka_bledna=String.valueOf(literka.charAt(zmienna));

                    Spanned kolorowa_lieterka_poprawna=null;
                    Spanned kolorowa_lieterka_bledna=null;
                    Boolean poprawna=false;
                    Boolean bledna=false;

                   if(String.valueOf(brakujaceLitery.charAt(zmienna)).equals(nowy_literka_poprawna)){
                       nowy_literka_poprawna="<font color=#1de535>"+nowy_literka_poprawna+"</font>";
                       kolorowa_lieterka_poprawna=Html.fromHtml(nowy_literka_poprawna);
                       poprawna=true;

                   }else{
                       nowy_literka_bledna="<font color=#db0f38>"+nowy_literka_bledna+"</font>";
                       kolorowa_lieterka_bledna=Html.fromHtml(nowy_literka_bledna);
                        bledna=true;
                   }

                    zakreskowaneSlowoTV.setText(str.substring(0,kreski_index.get(zmienna)));
                   if(poprawna==true){
                       zakreskowaneSlowoTV.append(kolorowa_lieterka_poprawna);
                       poprawna=false;
                   }else if(bledna==true){
                       zakreskowaneSlowoTV.append(kolorowa_lieterka_bledna);
                       poprawna=false;
                   }
                    zakreskowaneSlowoTV.append(str.substring(kreski_index.get(zmienna)+1));
                    zmienna++;
                    literka="";

                }else{
                    zmienna=0;
                }

            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


//backspace
        brakujaceLiteryET.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i==KeyEvent.KEYCODE_DEL){
                    brakujaceLiteryET.setText("");
                    zakreskowaneSlowoTV.setText(slowoZakreskowane);
                }
                return false;
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                brakujaceLiteryET.setText("");
                zakreskowaneSlowoTV.setText(slowoZakreskowane);
            }
        });


        wylosujSlowo();

        zakreskowaneSlowoTV.setText(slowoZakreskowane);
        polskieSlowoTV.setText(wylosowanePl);

        sprawdzPoprawnoscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (brakujaceLiteryET.getText().toString().equals(brakujaceLitery)) {
                    clear.setVisibility(View.INVISIBLE);
                    podpowiedzButton.setVisibility(View.INVISIBLE);
                    sprawdzPoprawnoscButton.setText("Poprawna odpowiedź");
                    sprawdzPoprawnoscButton.setBackgroundColor(Color.parseColor("#38ea3e"));
                    if (bledna == false) {
                        poprawne_odp++;
                    }
                    poprawnaEN.setVisibility(View.VISIBLE);
                    poprawnaEN.setText(wylosowaneEn.toString());
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
                clear.setVisibility(View.VISIBLE);
                progress++;
                pb.setProgress(progress);
                poprawnaEN.setVisibility(View.INVISIBLE);
                kreski_index.clear();
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
            sb.setCharAt(uzyteIdx.get(i), '_');
            brakujaceLitery += wylosowaneEn.charAt(uzyteIdx.get(i));
            kreski_index.add(uzyteIdx.get(i));
           // Toast.makeText(getApplicationContext(),uzyteIdx.get(i).toString(),Toast.LENGTH_SHORT).show();
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
