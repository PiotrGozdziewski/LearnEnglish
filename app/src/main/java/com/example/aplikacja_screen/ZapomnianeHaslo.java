package com.example.aplikacja_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.m.aplikacja_screen.R;

public class ZapomnianeHaslo extends AppCompatActivity {

    EditText podaj_login;
    EditText odpowiedz_pytanie;
    TextView login;
    TextView tresc_pytania;
    Button odzyskaj_haslo;
    Button sprawdz_odpowiedz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapomniane_haslo);

        podaj_login = (EditText) findViewById(R.id.editText);
        odpowiedz_pytanie = (EditText) findViewById(R.id.editText3);
        login = (TextView) findViewById(R.id.textView10);
        tresc_pytania = (TextView) findViewById(R.id.textView11);
        odzyskaj_haslo = (Button) findViewById(R.id.button3);
        sprawdz_odpowiedz = (Button) findViewById(R.id.button4);

        odzyskaj_haslo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tresc_pytania.setVisibility(View.VISIBLE);
                odpowiedz_pytanie.setVisibility(View.VISIBLE);
                sprawdz_odpowiedz.setVisibility(View.VISIBLE);
            }
        });

        sprawdz_odpowiedz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZapomnianeHaslo.this, NoweHaslo.class));
            }
        });
    }

    public void onBackPressed() {
        startActivity(new Intent(ZapomnianeHaslo.this, MainActivity.class));
    }


}
