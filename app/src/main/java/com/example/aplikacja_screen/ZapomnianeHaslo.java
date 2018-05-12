package com.example.aplikacja_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;

public class ZapomnianeHaslo extends AppCompatActivity {

    EditText podaj_login;
    EditText odpowiedz_pytanie;
    TextView login;
    TextView tresc_pytania;
    Button odzyskaj_haslo;
    Button sprawdz_odpowiedz;
    Database db;
    Cursor cursor;
    Cursor cursor2;
    Cursor cursor3;
    String login1;
    Cursor cursor_sprawdz;
    Cursor cursor_sprawdz2;

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
        db = new Database(getContentResolver());

        //sprawdzenie czy podany login istnieje w bazie
        cursor = db.getUsers();
        cursor2 = db.getHints();
        cursor3 = db.getQuestion();
        cursor_sprawdz = db.getUsers();
        cursor_sprawdz2 = db.getHints();

        odzyskaj_haslo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

            while(cursor.moveToNext())
            {
                        login1 = cursor.getString(1);
                        final int userID = cursor.getInt(0);
                        if(login1.equals(podaj_login.getText().toString()))
                        {
                            while(cursor2.moveToNext())
                            {
                                if(cursor2.getInt(2)==userID)
                                {
                                    //przeslanie wartosci userID do activity NoweHaslo
                                    SharedPreferences p= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor e=p.edit();
                                    e.putInt("userIDhaslo",userID);
                                    e.commit();
                                    int id_pytania_w_podpowiedzi=cursor2.getInt(1);
                                    while(cursor3.moveToNext())
                                    {
                                        if(id_pytania_w_podpowiedzi+1==cursor3.getInt(0))
                                        {
                                            String pytanie=cursor3.getString(1);
                                            //wczytanie pytania wybranego podczas rejestracji
                                            tresc_pytania.setText(pytanie);
                                            tresc_pytania.setVisibility(View.VISIBLE);
                                            odpowiedz_pytanie.setVisibility(View.VISIBLE);
                                            sprawdz_odpowiedz.setVisibility(View.VISIBLE);
                                        }
                                    }
                                    cursor3.close();
                                }
                            }
                            cursor2.close();
                        }
            }
                    cursor.close();
                }
            });

        sprawdz_odpowiedz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sprawdzenie udzielonej odpowiedzi
                while(cursor_sprawdz.moveToNext())
                {
                    login1 = cursor_sprawdz.getString(1);
                    final int userID = cursor_sprawdz.getInt(0);

                    if(login1.equals(podaj_login.getText().toString()))
                    {
                        while(cursor_sprawdz2.moveToNext())
                        {
                            if(cursor_sprawdz2.getInt(2)==userID)
                            {
                                String odpowiedz = cursor_sprawdz2.getString(3);
                                if(odpowiedz_pytanie.getText().toString().equals(odpowiedz))
                                {
                                    Toast.makeText(getApplicationContext(),"Poprawna odpowiedz",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ZapomnianeHaslo.this, NoweHaslo.class));
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public void onBackPressed() {
        startActivity(new Intent(ZapomnianeHaslo.this, MainActivity.class));
    }
}
