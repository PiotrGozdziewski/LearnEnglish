package com.example.aplikacja_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Dopasowanie extends AppCompatActivity {

    ImageView img1, img2, img3, img4;
    Spinner sp1, sp2, sp3, sp4;
    Button sprawdz, dalej;

    ArrayList<String> en;
    ArrayList<String> en_randomowe;
    ArrayAdapter<String> adapter;
    ArrayList<byte[]> photos;

    int i = 0;
    //wartosci od wybranego item w spinerze
    int random_sp1 = 0;
    int random_sp2 = 0;
    int random_sp3 = 0;
    int random_sp4 = 0;
    int ilosc_iteracji = 0;

    Database db;

    Bitmap bmp;
    Handler h = new Handler();

    ///Statystyki
    Cursor cursor1, cursor2;
    int poprawne = 0;
    int bledne = 0;
    boolean bl = false;
    int id_typu_zadania;
    int id_zadania;

    ProgressBar pb;
    int progress=0;

    Handler handler=new Handler();
    RelativeLayout r1,r2,r3,r4;

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

        dalej = (Button) findViewById(R.id.button33);
        sprawdz = (Button) findViewById(R.id.button32);

        r1 = (RelativeLayout)findViewById(R.id.relative1);
        r2 = (RelativeLayout)findViewById(R.id.relative2);
        r3 = (RelativeLayout)findViewById(R.id.relative3);
        r4 = (RelativeLayout)findViewById(R.id.relative4);

        pb = (ProgressBar)findViewById(R.id.progressBar2);
        pb.setMax(3);
        pb.getProgressDrawable().setColorFilter(Color.parseColor("#10961d"), android.graphics.PorterDuff.Mode.SRC_IN);

        //pobranie wartości aktualnie wybranej kategorii
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int idKategorii = p.getInt("idKategorii", 0);

        Cursor cursor = db.getPhoto(idKategorii);
        en_randomowe.add(0, " ");
        while (cursor.moveToNext()) {
            if (cursor.getBlob(4) != null) {
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
        dalej.setEnabled(false);

        sprawdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sprawdzenie czy poprawnie wybrano
                if(sp1.getSelectedItem().toString() == en.get(random_sp1).toString())
                {
                    r1.setBackgroundColor(Color.parseColor("#a5f49c"));
                }
                else
                {
                    r1.setBackgroundColor(Color.parseColor("#fc7171"));
                }
                if(sp2.getSelectedItem().toString() == en.get(random_sp2).toString())
                {
                    r2.setBackgroundColor(Color.parseColor("#a5f49c"));
                }
                else
                {
                    r2.setBackgroundColor(Color.parseColor("#fc7171"));
                }
                if(sp3.getSelectedItem().toString() == en.get(random_sp3).toString())
                {
                    r3.setBackgroundColor(Color.parseColor("#a5f49c"));
                }
                else
                {
                    r3.setBackgroundColor(Color.parseColor("#fc7171"));
                }
                if(sp4.getSelectedItem().toString() == en.get(random_sp4).toString())
                {
                    r4.setBackgroundColor(Color.parseColor("#a5f49c"));
                }
                else
                {
                    r4.setBackgroundColor(Color.parseColor("#fc7171"));
                }

                if (sp1.getSelectedItem().toString() == en.get(random_sp1).toString()
                        && sp2.getSelectedItem().toString() == en.get(random_sp2).toString()
                        && sp3.getSelectedItem().toString() == en.get(random_sp3).toString()
                        && sp4.getSelectedItem().toString() == en.get(random_sp4).toString()) {
                    if (bl == false) poprawne++;
                    bl = true;
                    sprawdz.setText("Poprawna odpowiedź");
                    sprawdz.setClickable(false);
                    sp1.setEnabled(false);
                    sp2.setEnabled(false);
                    sp3.setEnabled(false);
                    sp4.setEnabled(false);
                    sprawdz.setBackgroundColor(Color.parseColor("#38ea3e"));
                    dalej.setEnabled(true);
                } else if (sp1.getSelectedItem().toString() == " " | sp2.getSelectedItem().toString() == " "
                        | sp3.getSelectedItem().toString() == " " | sp4.getSelectedItem().toString() == " ") {
                    Toast.makeText(getApplicationContext(), "Pozostawiono puste pola", Toast.LENGTH_LONG).show();
                } else {
                    sprawdz.setText("Niepoprawna odpowiedź");
                    sprawdz.setBackgroundColor(Color.parseColor("#d11f34"));
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sprawdz.setText("Sprawdź");
                            sprawdz.setBackgroundResource(R.drawable.zestawy_screen);
                        }
                    }, 3000);
                    bledne++;
                }
            }
        });

        dalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress++;
                pb.setProgress(progress);
                sprawdz.setText("Sprawdź");
                sprawdz.setClickable(true);
                sp1.setEnabled(true);
                sp2.setEnabled(true);
                sp3.setEnabled(true);
                sp4.setEnabled(true);
                sprawdz.setBackgroundResource(R.drawable.zestawy_screen);
                r1.setBackgroundColor(Color.parseColor("#eaeae1"));
                r2.setBackgroundColor(Color.parseColor("#eaeae1"));
                r3.setBackgroundColor(Color.parseColor("#eaeae1"));
                r4.setBackgroundColor(Color.parseColor("#eaeae1"));

                if (ilosc_iteracji == 3) {
                    zapisz_statytyski();
                    startActivity(new Intent(Dopasowanie.this, WyborKategorii.class));
                }

                if (ilosc_iteracji <= 2) {
                    en_randomowe.clear();
                    en_randomowe.add(0, " ");
                    wyswietl_zdjecia();
                    sp1.setAdapter(adapter);
                    sp2.setAdapter(adapter);
                    sp3.setAdapter(adapter);
                    sp4.setAdapter(adapter);
                    ilosc_iteracji++;
                }
                if (ilosc_iteracji == 3) {
                    dalej.setText("Zakończ lekcję");
                }
                bl = false;
                dalej.setEnabled(false);
            }
        });

        //pobranie id wybranego zadania
        SharedPreferences p1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String wybranyTypZadania = p1.getString("NazwaTypuZadania", "0");
        cursor1 = db.getExerciceType(wybranyTypZadania);
        while (cursor1.moveToNext()) {
            String nazwa_baza = cursor1.getString(1);
            if (wybranyTypZadania.equals(nazwa_baza)) {
                id_typu_zadania = cursor1.getInt(0);
            }
        }

        //pobranie id zadania
        SharedPreferences p2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        cursor2 = db.getExercise(idKategorii, id_typu_zadania);
        while (cursor2.moveToNext()) {
            id_zadania = cursor2.getInt(0);
        }
    }

    public void wyswietl_zdjecia() {
        int ilosc = photos.size(); //ilość zdjęć
        int random = 0;
        Random r = new Random();
        ArrayList<Integer> lista_randomowych = new ArrayList<Integer>();
        ArrayList<Integer> lista_random_dla_slow = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++) {  //mamy 4 zdjęcia
            do {
                random = r.nextInt(ilosc-1) + 1;
            } while (lista_randomowych.contains(random));
            lista_randomowych.add(random); // lista_randomowych przchowuje 4 wartosc z ilosci dostepnych zdjec
        }

        int random_dla_slow = 0;
        en_randomowe.add("a");
        en_randomowe.add("b");
        en_randomowe.add("c");
        en_randomowe.add("d");
        while (i < 4) {
            do {
                random_dla_slow = r.nextInt(4) + 1; // od 1 do 4
            } while (lista_random_dla_slow.contains(random_dla_slow));
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
            } else {
                Toast.makeText(getApplicationContext(), "Zdjęcie == null", Toast.LENGTH_SHORT).show();
            }
        }
        lista_randomowych.clear();
        lista_random_dla_slow.clear();
        i = 0;
    }

    public void zapisz_statytyski() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userID = prefs.getString("id", "0");
        Date currentTime = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        String date = DateFormat.format("yyyy-MM-dd", cal).toString();
        db.insertIntoLessons(Integer.parseInt(userID), id_zadania, String.valueOf(poprawne), String.valueOf(bledne), date);
    }

    public void onBackPressed() {
        startActivity(new Intent(Dopasowanie.this, WyborKategorii.class));
    }
}