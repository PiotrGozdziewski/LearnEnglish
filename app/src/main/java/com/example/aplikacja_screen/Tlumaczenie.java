package com.example.aplikacja_screen;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;

import java.util.ArrayList;

public class Tlumaczenie extends AppCompatActivity {

    Button sprawdz_poprawnosc, dalej;
    EditText en;
    TextView pl;

    ArrayList<String> polskie;
    ArrayList<String> angielskie;

    Database db;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tlumaczenie);
        sprawdz_poprawnosc = (Button) findViewById(R.id.button17);
        dalej = (Button) findViewById(R.id.button20);
        pl = (TextView) findViewById(R.id.textView21);
        en = (EditText) findViewById(R.id.textView22);
        polskie = new ArrayList<String>();
        angielskie = new ArrayList<String>();
        db = new Database(getContentResolver());

        Cursor cursor = db.getWords(7);
        while(cursor.moveToNext())
        {
            polskie.add(cursor.getString(2));
            angielskie.add(cursor.getString(3));
        }


        pl.setText(polskie.get(0));
        sprawdz_poprawnosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),en.getText().toString(),Toast.LENGTH_SHORT).show();
                if (en.getText().toString().equals(angielskie.get(i).toString())) {

                    sprawdz_poprawnosc.setText("Poprawna odpowiedź");
                    sprawdz_poprawnosc.setBackgroundColor(Color.parseColor("#38ea3e"));
                } else {
                    // dialogAlertNegative();
                    //Toast.makeText(getApplicationContext(),"Niepoprawna odpowiedź",Toast.LENGTH_SHORT).show();
                    sprawdz_poprawnosc.setText("Niepoprawna odpowiedź");
                    sprawdz_poprawnosc.setBackgroundColor(Color.parseColor("#d11f34"));
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
                }
            }
        });

        dalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==9)
                {
                    startActivity(new Intent(Tlumaczenie.this,WyborKategorii.class));
                }
                en.setText("");
                sprawdz_poprawnosc.setText("Sprawdź poprawność");
                sprawdz_poprawnosc.setBackgroundColor(Color.parseColor("#e6e1e1"));
                i++;
                if(i<=9){wczytaj_slowa(i);}
                if(i==9)
                {
                    dalej.setText("Zakończ lekcje");
                }
            }
        });
    }

    public void wczytaj_slowa(int index)
    {
        pl.setText(polskie.get(index));
    }
}
