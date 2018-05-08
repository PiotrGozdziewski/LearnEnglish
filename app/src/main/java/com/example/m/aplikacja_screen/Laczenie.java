package com.example.m.aplikacja_screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Laczenie extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    boolean clicked=false;
    ArrayList slowa_pl;
    ArrayList slowa_en;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laczenie);

        b1=(Button)findViewById(R.id.button21);
        b2=(Button)findViewById(R.id.button22);
        b3=(Button)findViewById(R.id.button23);
        b4=(Button)findViewById(R.id.button24);
        b5=(Button)findViewById(R.id.button25);
        b6=(Button)findViewById(R.id.button26);
        b7=(Button)findViewById(R.id.button27);
        b8=(Button)findViewById(R.id.button28);
        b9=(Button)findViewById(R.id.button29);
        b10=(Button)findViewById(R.id.button30);

        //przypisanie slowek do button
        //mozna ustalic ze od 1-5 polskie --> od 6-10 angielskie --> obojętnie w jakiej kolejności
        b1.setText("Table");
        b7.setText("Table");
        b2.setText("Dom");
        b3.setText("Dom");
        b5.setText("Holiday");
        b4.setText("Wakacje");
        b6.setText("Cat");
        b8.setText("Kot");
        b9.setText("Summer");
        b10.setText("Lato");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackgroundResource(R.drawable.button_border_green);
                if(b1.getText()==b7.getText()){
                    b7.setBackgroundResource(R.drawable.button_border_green);
                    b1.setVisibility(View.INVISIBLE);
                    b7.setVisibility(View.INVISIBLE);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setBackgroundResource(R.drawable.button_border_green);
                b5.setBackgroundResource(R.drawable.button_border_red);
            }
        });

        if(b1.isPressed()){
            String slowo1=b1.getText().toString();
            //tutaj do jakiejs zmiennej odpowiednik tego slowo1 z bazy
            String slowo_z_bazy="";
            //i nastepnie szukamy przez wszystkie buttony poprawnej odpowiedzi
            //jeżeli znajedziemy poprawną to te dwa buttony znikają
            if(slowo_z_bazy==b2.getText()){
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b3.getText()){
                b1.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b4.getText()){
                b1.setVisibility(View.INVISIBLE);
                b4.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b5.getText()){
                b1.setVisibility(View.INVISIBLE);
                b5.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b6.getText()){
                b1.setVisibility(View.INVISIBLE);
                b6.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b7.getText()){
                b1.setVisibility(View.INVISIBLE);
                b7.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b8.getText()){
                b1.setVisibility(View.INVISIBLE);
                b8.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b9.getText()){
                b1.setVisibility(View.INVISIBLE);
                b9.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b10.getText()){
                b1.setVisibility(View.INVISIBLE);
                b10.setVisibility(View.INVISIBLE);
            }
        }else if(b2.isPressed()){
            String slowo1=b1.getText().toString();
            //tutaj do jakiejs zmiennej odpowiednik tego slowo1 z bazy
            String slowo_z_bazy="";
            //i nastepnie szukamy przez wszystkie buttony poprawnej odpowiedzi
            //jeżeli znajedziemy poprawną to te dwa buttony znikają
            if(slowo_z_bazy==b1.getText()){
                b2.setVisibility(View.INVISIBLE);
                b1.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b3.getText()){
                b2.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b4.getText()){
                b2.setVisibility(View.INVISIBLE);
                b4.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b5.getText()){
                b2.setVisibility(View.INVISIBLE);
                b5.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b6.getText()){
                b2.setVisibility(View.INVISIBLE);
                b6.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b7.getText()){
                b2.setVisibility(View.INVISIBLE);
                b7.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b8.getText()){
                b2.setVisibility(View.INVISIBLE);
                b8.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b9.getText()){
                b2.setVisibility(View.INVISIBLE);
                b9.setVisibility(View.INVISIBLE);
            }else if(slowo_z_bazy==b10.getText()){
                b2.setVisibility(View.INVISIBLE);
                b10.setVisibility(View.INVISIBLE);
            }
        }
    }
}
