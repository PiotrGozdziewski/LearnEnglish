package com.example.aplikacja_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.m.aplikacja_screen.R;

public class WyborZadania extends AppCompatActivity {

    RadioButton dopasowanie, wybór, rozsypanka, plansza_kwadratowa, tłumaczenie, uzupełnianie;
    Button rozpocznij_lekcję;
    String tekst;
    TextView multiLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor_zadania);


        dopasowanie = (RadioButton) findViewById(R.id.dopasowanie);
        wybór = (RadioButton) findViewById(R.id.wybór);
        rozsypanka = (RadioButton) findViewById(R.id.rozsypanka);
        plansza_kwadratowa = (RadioButton) findViewById(R.id.plansza_kwadratowa);
        tłumaczenie = (RadioButton) findViewById(R.id.tłumaczenie);
        uzupełnianie = (RadioButton) findViewById(R.id.uzupełnianie);
        rozpocznij_lekcję = (Button) findViewById(R.id.lekcja);
        multiLine = (TextView) findViewById(R.id.multiLine);

        dopasowanie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (dopasowanie.isChecked()) {
                    multiLine.setVisibility(View.VISIBLE);
                    multiLine.setText("\tDopasowanie\n\tZadanie polega na dopasowaniu obrazka do jego jego znaczenia w języku angielskim.");
                }
            }
        });
        wybór.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (wybór.isChecked()) {
                    multiLine.setVisibility(View.VISIBLE);
                    multiLine.setText("\tWybór\n\tZadanie polega na wybraniu,\nodpowiednio przetlumaczonego slowa.");
                }
            }
        });
        rozsypanka.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (rozsypanka.isChecked()) {
                    multiLine.setVisibility(View.VISIBLE);
                    multiLine.setText("\tRozsypanka\n\tZadanie polega na utworzeniu zdania\nz rozsypanki wyrazowej.");
                }
            }
        });
        tłumaczenie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (tłumaczenie.isChecked()) {
                    multiLine.setVisibility(View.VISIBLE);
                    multiLine.setText("\tTłumaczenie\n\tZadanie polega na przetłumaczeniu słowa\nnapisanego w języku polskim na jego\nodpowiednik w języku angielskim.");
                }
            }
        });


        plansza_kwadratowa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (plansza_kwadratowa.isChecked()) {
                    multiLine.setVisibility(View.VISIBLE);
                    multiLine.setText("\tPlansza kwadratowa\n\tZadanie polega na znalezieniu\nodpowiednich słów.");
                }
            }
        });
        uzupełnianie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (uzupełnianie.isChecked()) {
                    multiLine.setVisibility(View.VISIBLE);
                    multiLine.setText("\tUzupełnianie\n\tZadanie polega na uzupełnieniu brakujących liter\nw słowie.");
                }
            }
        });


        rozpocznij_lekcję.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dopasowanie.isChecked()) {
                    tekst = dopasowanie.getText().toString();
                    //start activity
                    SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor e = p.edit();
                    e.putString("NazwaTypuZadania", tekst);
                    e.commit();
                    startActivity(new Intent(WyborZadania.this, Dopasowanie.class));
                } else if (wybór.isChecked()) {
                    tekst = wybór.getText().toString();
                    SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor e = p.edit();
                    e.putString("NazwaTypuZadania", tekst);
                    e.commit();
                    startActivity(new Intent(WyborZadania.this, Wybor.class));
                } else if (rozsypanka.isChecked()) {
                    tekst = rozsypanka.getText().toString();
                    SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor e = p.edit();
                    e.putString("NazwaTypuZadania", tekst);
                    e.commit();
                    startActivity(new Intent(WyborZadania.this, Rozsypanka.class));
                } else if (plansza_kwadratowa.isChecked()) {
                    tekst = plansza_kwadratowa.getText().toString();
                    SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor e = p.edit();
                    e.putString("NazwaTypuZadania", tekst);
                    e.commit();
                    startActivity(new Intent(WyborZadania.this, PlanszaKwadratowa.class));
                } else if (tłumaczenie.isChecked()) {
                    tekst = tłumaczenie.getText().toString();
                    SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor e = p.edit();
                    e.putString("NazwaTypuZadania", tekst);
                    e.commit();
                    startActivity(new Intent(WyborZadania.this, Tlumaczenie.class));
                } else if (uzupełnianie.isChecked()) {
                    tekst = uzupełnianie.getText().toString();
                    SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor e = p.edit();
                    e.putString("NazwaTypuZadania", tekst);
                    e.commit();
                    startActivity(new Intent(WyborZadania.this, Uzupelnianie.class));
                }
                //Toast.makeText(getApplicationContext(), "Wybrano " + tekst, Toast.LENGTH_LONG).show();
            }
        });
    }
}
