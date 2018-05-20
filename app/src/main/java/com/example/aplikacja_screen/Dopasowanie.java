package com.example.aplikacja_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
    ArrayList<String>en;
    ArrayList<String> en_randomowe;
    ArrayList<byte[]> photos;
    ArrayAdapter<String> adapter;
    int i=0;
    //wartosci od wybranego item w spinerze
    int random_sp1 = 0;
    int random_sp2 = 0;
    int random_sp3 = 0;
    int random_sp4 = 0;
    Database db;
    int ilosc_iteracji=0;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dopasowanie);
        db = new Database(getContentResolver());
        en = new ArrayList<String>();
        en_randomowe = new ArrayList<String>();
        photos = new ArrayList<byte[]>();
        //zdjecia
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

        Cursor cursor = db.getPhoto(idKategorii);
        en_randomowe.add(0," ");
        while(cursor.moveToNext()){
                if(cursor.getBlob(4)!=null)
                {
                    photos.add(cursor.getBlob(4));
                    en.add(cursor.getString(3));
                }
        }

        //wyświetlanie zdjęć
        wyswietl_zdjecia();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, en_randomowe);
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
                    Toast.makeText(getApplicationContext(), "Zgadza się", Toast.LENGTH_SHORT).show();
                }else if(sp1.getSelectedItem().toString()==" "|sp2.getSelectedItem().toString()==" "
                        |sp3.getSelectedItem().toString()==" "|sp4.getSelectedItem().toString()==" "){
                    Toast.makeText(getApplicationContext(),"Pozostawiono puste pola",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Nie zgadza sie", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dalej = (Button) findViewById(R.id.button33);
        dalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ilosc_iteracji==3){
                    startActivity(new Intent(Dopasowanie.this, BocznyPasekLewy.class));
                }
                if(ilosc_iteracji<=2) {
                    en_randomowe.clear();
                    en_randomowe.add(0," ");
                    wyswietl_zdjecia();
                    adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, en_randomowe);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp1.setAdapter(adapter);
                    sp2.setAdapter(adapter);
                    sp3.setAdapter(adapter);
                    sp4.setAdapter(adapter);
                    ilosc_iteracji++;
                }
                if(ilosc_iteracji==3){
                    dalej.setText("Zakończ lekcję");
                }
            }
        });
    }

    public void wyswietl_zdjecia()
    {
        int ilosc=photos.size();
        int random=0;
        int random_en0=0;
        int random_en1=0;
        int random_en2=0;
        int random_en3=0;
        Random r = new Random();
        ArrayList<Integer> lista_randomowych=new ArrayList<Integer>();
        ArrayList<Integer> lista_random_dla_slow=new ArrayList<Integer>();
        for(int i=0;i<4;i++) {
            do {
                random = r.nextInt(ilosc) + 1;
            } while (lista_randomowych.contains(random));
            lista_randomowych.add(random);
        }

        int random_dla_slow=0;
        en_randomowe.add("a");
        en_randomowe.add("b");
        en_randomowe.add("c");
        en_randomowe.add("d");
        while(i<4) {
            do {
                random_dla_slow = r.nextInt(4) + 1; // od 1 do 4
            }while(lista_random_dla_slow.contains(random_dla_slow));
            if (photos.get(lista_randomowych.get(i)) != null) {
                 if (i == 0) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(photos.get(lista_randomowych.get(0)), 0, photos.get(lista_randomowych.get(0)).length);
                    en_randomowe.set(random_dla_slow, en.get(lista_randomowych.get(0)));
                    img1.setImageBitmap(bmp);
                    random_sp1 = lista_randomowych.get(0);
                }
                if (i == 1) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(photos.get(lista_randomowych.get(1)), 0, photos.get(lista_randomowych.get(1)).length);
                    en_randomowe.set(random_dla_slow, en.get(lista_randomowych.get(1)));
                    img2.setImageBitmap(bmp);
                    random_sp2 = lista_randomowych.get(1);
                }
                if (i == 2) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(photos.get(lista_randomowych.get(2)), 0, photos.get(lista_randomowych.get(2)).length);
                    en_randomowe.set(random_dla_slow, en.get(lista_randomowych.get(2)));
                    img3.setImageBitmap(bmp);
                    random_sp3 = lista_randomowych.get(2);
                }
                if (i == 3) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(photos.get(lista_randomowych.get(3)), 0, photos.get(lista_randomowych.get(3)).length);
                    en_randomowe.set(random_dla_slow, en.get(lista_randomowych.get(3)));
                    img4.setImageBitmap(bmp);
                    random_sp4 = lista_randomowych.get(3);
                }
                lista_random_dla_slow.add(random_dla_slow);
                i++;
            }
        }
        lista_randomowych.clear();
        lista_random_dla_slow.clear();
        i=0;
    }
}