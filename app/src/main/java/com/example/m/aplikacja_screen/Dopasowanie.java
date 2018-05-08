package com.example.m.aplikacja_screen;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Dopasowanie extends AppCompatActivity {

    //4 zdjęcia
    //4 słówka angielskie
    ImageView img1,img2,img3,img4;
    Spinner sp1,sp2,sp3,sp4;
    Button sprawdz,dalej;
    String []en={"green","red","orange","blue"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dopasowanie);

        //zdjecia --ale to raczej nie bedzie potrzbe
        img1=(ImageView)findViewById(R.id.imageView2);
        img2=(ImageView)findViewById(R.id.imageView4);
        img3=(ImageView)findViewById(R.id.imageView6);
        img4=(ImageView)findViewById(R.id.imageView7);

        sp1=(Spinner)findViewById(R.id.spinner2);
        sp2=(Spinner)findViewById(R.id.spinner3);
        sp3=(Spinner)findViewById(R.id.spinner4);
        sp4=(Spinner)findViewById(R.id.spinner5);


        //do tej tablicy trzeba bedzie pobrac z bazy odpowiednie slowka pasujace do zdjec
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,en);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        sp2.setAdapter(adapter);
        sp3.setAdapter(adapter);
        sp4.setAdapter(adapter);

        sp1.setPrompt("Wybierz slowo");
        sprawdz=(Button)findViewById(R.id.button32);
        sprawdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sprawdzenie czy
                if(sp1.getSelectedItem().toString()==en[0].toString()
                        && sp2.getSelectedItem().toString()==en[1].toString()
                        && sp3.getSelectedItem().toString()==en[2].toString()
                        && sp4.getSelectedItem().toString()==en[3].toString()){
                    Toast.makeText(getApplicationContext(),"Zgadza sie",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Nie zgadza sie",Toast.LENGTH_SHORT).show();
                }
            }
        });

        dalej=(Button)findViewById(R.id.button33);
        dalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //na nowo zaladowac zdjecia i napisy do tablicy
                //czylia jakas funkcja

            }
        });

    }
}
