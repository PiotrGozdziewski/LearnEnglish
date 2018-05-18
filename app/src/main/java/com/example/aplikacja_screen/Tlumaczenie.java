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
import android.widget.TextView;
import android.widget.Toast;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Tlumaczenie extends AppCompatActivity {

    Button sprawdz_poprawnosc, dalej, sprawdz;
    EditText en;
    TextView pl;

    ArrayList<String> polskie;
    ArrayList<String> angielskie;

    Database db;
    int i=0;
    int poprawne_odp=0;
    int bledne_odp=0;
    Boolean bledna=false;
    Handler h = new Handler();
    Cursor cursor;
    Cursor cursor1;
    int id_typu_zadania;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tlumaczenie);
        sprawdz_poprawnosc = (Button) findViewById(R.id.button17);
        dalej = (Button) findViewById(R.id.button20);
        sprawdz = (Button) findViewById(R.id.button38);
        pl = (TextView) findViewById(R.id.textView21);
        en = (EditText) findViewById(R.id.textView22);
        polskie = new ArrayList<String>();
        angielskie = new ArrayList<String>();
        db = new Database(getContentResolver());

        //pobranie id aktualnej kategorii
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int idKategorii = p.getInt("idKategorii",0);

        SharedPreferences p1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String wybranyTypZadania = p1.getString("NazwaTypuZadania","0");
        Toast.makeText(getApplicationContext(),"nazwa: "+wybranyTypZadania,Toast.LENGTH_SHORT).show();
        cursor1=db.getExerciceType(wybranyTypZadania);
        while(cursor1.moveToNext())
        {
            String nazwa_baza=cursor1.getString(1);
            if(wybranyTypZadania.equals(nazwa_baza))
            {
                //Toast.makeText(getApplicationContext(),"jest ok",Toast.LENGTH_SHORT).show();
                id_typu_zadania=cursor1.getInt(0);
            }
        }

        Toast.makeText(getApplicationContext(),"id typu: "+String.valueOf(id_typu_zadania),Toast.LENGTH_SHORT).show();

        cursor = db.getWords(7);
        while(cursor.moveToNext())
        {
            polskie.add(cursor.getString(2));
            angielskie.add(cursor.getString(3));
        }

        pl.setText(polskie.get(0));
        sprawdz_poprawnosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(),en.getText().toString(),Toast.LENGTH_SHORT).show();
                if (en.getText().toString().equals(angielskie.get(i).toString())) {

                    sprawdz.setVisibility(View.INVISIBLE);
                    sprawdz_poprawnosc.setText("Poprawna odpowiedź");
                    sprawdz_poprawnosc.setBackgroundColor(Color.parseColor("#38ea3e"));
                    if(bledna==false)
                    {
                        poprawne_odp++;
                    }
                    sprawdz_poprawnosc.setClickable(false);
                    dalej.setVisibility(View.VISIBLE);
                } else {
                    // dialogAlertNegative();
                    //Toast.makeText(getApplicationContext(),"Niepoprawna odpowiedź",Toast.LENGTH_SHORT).show();
                    sprawdz_poprawnosc.setText("Niepoprawna odpowiedź");
                    sprawdz_poprawnosc.setBackgroundColor(Color.parseColor("#d11f34"));
                    if(bledna==false)
                    {
                        bledne_odp++;
                        bledna=true;
                    }
                    sprawdz.setVisibility(View.VISIBLE);
                    sprawdz_poprawnosc.setClickable(false);
                    sprawdz.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog alertDialog = new AlertDialog.Builder(Tlumaczenie.this).create();
                            alertDialog.setTitle("Poprawna odpowiedz");
                            alertDialog.setMessage(polskie.get(i)+" - "+angielskie.get(i));
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                            sprawdz_poprawnosc.setText("Sprawdź poprawność");
                            sprawdz_poprawnosc.setBackgroundColor(Color.parseColor("#e6e1e1"));
                        }
                    });

                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sprawdz_poprawnosc.setText("Sprawdź poprawność");
                            //sprawdz.setVisibility(View.VISIBLE);
                            sprawdz_poprawnosc.setVisibility(View.VISIBLE);
                            sprawdz_poprawnosc.setClickable(true);
                            sprawdz_poprawnosc.setBackgroundColor(Color.parseColor("#e6e1e1"));
                        }
                    },3000);

                }

            }
        });

        dalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==3)
                {
                    zapisz_statytyski();
                    startActivity(new Intent(Tlumaczenie.this,WyborKategorii.class));
                }
                en.setText("");
                sprawdz_poprawnosc.setClickable(true);
                sprawdz_poprawnosc.setText("Sprawdź poprawność");
                sprawdz_poprawnosc.setBackgroundColor(Color.parseColor("#e6e1e1"));
                i++;
                bledna=false;
                dalej.setVisibility(View.INVISIBLE);
                if(i<=3){wczytaj_slowa(i);}
                if(i==3)
                {
                    Toast.makeText(getApplicationContext(),String.valueOf(poprawne_odp),Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(),String.valueOf(bledne_odp),Toast.LENGTH_SHORT).show();
                    dalej.setText("Zakończ lekcje");
                }
            }
        });


    }

    public void wczytaj_slowa(int index)
    {
        pl.setText(polskie.get(index));
    }

    public void zapisz_statytyski()
    {

        //int userId, int exerciseId, String correctAnswer, String wrongAnswer, Str
        //pobrane ID użytkownika
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userID = prefs.getString("id", "0");
        //pobrane ID zadania
        //pobrana aktualna data

        Date currentTime = Calendar.getInstance().getTime();

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        String date = DateFormat.format("yyyy-MM-dd hh:mm:ss", cal).toString();

        Toast.makeText(getApplicationContext(),String.valueOf(currentTime),Toast.LENGTH_SHORT).show();
        db.insertIntoLessons(Integer.parseInt(userID),id_typu_zadania,String.valueOf(poprawne_odp),String.valueOf(bledne_odp),
                date);
    }
}
