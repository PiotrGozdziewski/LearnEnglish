package com.example.m.aplikacja_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Nauka_fiszki extends AppCompatActivity {

    Button fiszka;
    Boolean odwroc=false;
    ImageButton next;
    int i=0;
    String [] pl={"Zakwaterowanie","jajko","boczek","makaron","xxxx","aaaa","Okulary przeciwsłoneczne"};
    String [] en = {"Accomodation", "egg", "bacon", "pasta", "Eksesk","EjEj","sunglasses"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nauka_fiszki);

        fiszka=(Button)findViewById(R.id.button7);
        next=(ImageButton)findViewById(R.id.imageButton);

        fiszka.setText(pl[0]);
        fiszka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (odwroc == true) {
                    fiszka.setText(pl[i]);
                    odwroc=false;
                } else {
                    fiszka.setText(en[i]);
                    odwroc = true;
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kolejna fiszka
                if(i==pl.length-1) {
                    i = -1;
                }
                fiszka.setText(pl[i+1]);
                odwroc=false;
                i++;


            }
        });
    }
    public void onBackPressed(){
        startActivity(new Intent(Nauka_fiszki.this,Moje_zestawy.class));
    }
}
