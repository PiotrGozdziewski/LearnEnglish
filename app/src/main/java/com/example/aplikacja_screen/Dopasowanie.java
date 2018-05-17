package com.example.aplikacja_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;

import java.util.ArrayList;
import java.util.Random;

public class Dopasowanie extends AppCompatActivity {

    ImageView img1, img2, img3, img4;
    Spinner sp1, sp2, sp3, sp4;
    Button sprawdz, dalej;
    ArrayList<String> en;
    ArrayList<String> en_randomowe;
    ArrayList<byte[]> photos;
    int i=0;
    //wartosci od wybranego item w spinerze
    int random_sp1 = 0;
    int random_sp2 = 0;
    int random_sp3 = 0;
    int random_sp4 = 0;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dopasowanie);
        db = new Database(getContentResolver());
        en = new ArrayList<String>();
        en_randomowe = new ArrayList<String>();
        photos = new ArrayList<byte[]>();
        //zdjecia --ale to raczej nie bedzie potrzbe
        img1 = (ImageView) findViewById(R.id.imageView2);
        img2 = (ImageView) findViewById(R.id.imageView6);
        img3 = (ImageView) findViewById(R.id.imageView7);
        img4 = (ImageView) findViewById(R.id.imageView4);

        sp1 = (Spinner) findViewById(R.id.spinner2);
        sp2 = (Spinner) findViewById(R.id.spinner3);
        sp3 = (Spinner) findViewById(R.id.spinner4);
        sp4 = (Spinner) findViewById(R.id.spinner5);

        //pobranie wartości aktualnie wybranej kategorii
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int idKategorii = p.getInt("idKategorii",0);
        //Toast.makeText(getApplicationContext(),String.valueOf(idKategorii),Toast.LENGTH_SHORT).show();

        Cursor cursor = db.getPhoto(idKategorii);
        en_randomowe.add("Wybierz");

        while(cursor.moveToNext()){
            //zrobić random

                if(cursor.getBlob(4)!=null)
                {
                    photos.add(cursor.getBlob(4));
                    en.add(cursor.getString(3));
                }

        }

        //wyświetlanie zdjęć
        wyswietl_zdjecia();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, en_randomowe);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        sp2.setAdapter(adapter);
        sp3.setAdapter(adapter);
        sp4.setAdapter(adapter);


        sprawdz = (Button) findViewById(R.id.button32);
        sprawdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sprawdzenie czy poprawnie wybrano
                if (sp1.getSelectedItem().toString() == en.get(random_sp1).toString()
                        && sp2.getSelectedItem().toString() == en.get(random_sp2).toString()
                        && sp3.getSelectedItem().toString() == en.get(random_sp3).toString()
                        && sp4.getSelectedItem().toString() == en.get(random_sp4).toString()) {
                    Toast.makeText(getApplicationContext(), "Zgadza sie", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Nie zgadza sie", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dalej = (Button) findViewById(R.id.button33);
        dalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dopasowanie.this,Dopasowanie.class) );

            }
        });
    }

    public void wyswietl_zdjecia()
    {
        int ilosc=photos.size();
        Random r = new Random();
        ArrayList<Integer> lista_randomowych=new ArrayList<Integer>();
        for(int i=0;i<4;i++)
        {
            int random = r.nextInt(ilosc)+1;
            if(photos.get(random)!=null && !lista_randomowych.contains(random)){
                Bitmap bmp = BitmapFactory.decodeByteArray(photos.get(random), 0, photos.get(random).length);
                if(i==0)
                {
                    en_randomowe.add(en.get(random));
                    img1.setImageBitmap(bmp);
                    random_sp1=random;
                }if(i==1)
                {
                    en_randomowe.add(en.get(random));
                    img2.setImageBitmap(bmp);
                    random_sp2=random;
                }if(i==2)
                {
                    en_randomowe.add(en.get(random));
                    img3.setImageBitmap(bmp);
                    random_sp3=random;
                }if(i==3)
                {
                    en_randomowe.add(en.get(random));
                    img4.setImageBitmap(bmp);
                    random_sp4=random;
                }
                lista_randomowych.add(random);
            }
        }
    }
}
