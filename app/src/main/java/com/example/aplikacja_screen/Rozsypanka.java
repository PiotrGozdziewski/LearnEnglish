package com.example.aplikacja_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rozsypanka extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, dalej, zdanie_pl, sprawdź, clear;
    EditText wprowadz;
    Database db;
    Cursor cursor1;
    Cursor cursor2;
    int id_typu_zadania;
    int idKategorii;
    int id_zadania;
    ArrayList<String> zdania_en;
    ArrayList<String> zdania_pl;
    ArrayList<String> słowa_ze_zdania_en;
    int ilość_iteracji = 0;
    int ilość_słów_w_zdaniu = 0;
    int poprawne_odp=0;
    int bledne_odp=0;
    boolean b1ON, b2ON, b3ON, b4ON, b5ON, b6ON, b7ON, b8ON = false;
    //od wątków
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rozsypanka);
        zdanie_pl = (Button) findViewById(R.id.button47);
        wprowadz = (EditText) findViewById(R.id.editText8);
        btn1 = (Button) findViewById(R.id.button15);
        btn2 = (Button) findViewById(R.id.button16);
        btn3 = (Button) findViewById(R.id.button18);
        btn4 = (Button) findViewById(R.id.button44);
        btn5 = (Button) findViewById(R.id.button45);
        btn6 = (Button) findViewById(R.id.button19);
        btn7 = (Button) findViewById(R.id.button49);
        btn8 = (Button) findViewById(R.id.button50);
        dalej = (Button) findViewById(R.id.button46);
        sprawdź = (Button) findViewById(R.id.button48);
        clear = (Button) findViewById(R.id.button51);
        db = new Database(getContentResolver());
        zdania_en = new ArrayList<String>();
        zdania_pl = new ArrayList<String>();
        słowa_ze_zdania_en = new ArrayList<String>();

        //pobranie id aktualnej kategorii
        SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        idKategorii = p.getInt("idKategorii", 0);

        Cursor cursor = db.getSentences(idKategorii);
        while (cursor.moveToNext()) {
            zdania_en.add(cursor.getString(3)); //pobranie zdań_en do tablicy
            zdania_pl.add(cursor.getString(2)); //pobranie zdań_pl do tablicy
        }
        zdanie_pl.setText(zdania_pl.get(0));

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


        //podzielenie zdań na poszczególne słowa i zapisanie słów w tablicy
        uzupelnij();

        dalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ilość_iteracji==5)
                {
                    zapisz_statytyski();
                    startActivity(new Intent(Rozsypanka.this, WyborKategorii.class));
                }
                sprawdź.setText("Sprawdź");
                sprawdź.setBackgroundColor(Color.parseColor("#e7e2e2"));
                wprowadz.setText("");
                btn1.setVisibility(View.INVISIBLE);
                btn2.setVisibility(View.INVISIBLE);
                btn3.setVisibility(View.INVISIBLE);
                btn4.setVisibility(View.INVISIBLE);
                btn5.setVisibility(View.INVISIBLE);
                btn6.setVisibility(View.INVISIBLE);
                btn7.setVisibility(View.INVISIBLE);
                btn8.setVisibility(View.INVISIBLE);
                uzupelnij();
                dalej.setVisibility(View.INVISIBLE);
                sprawdź.setClickable(true);
                clear.setClickable(true);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1ON = true;
                if (wprowadz.getText().toString().isEmpty()) {
                    wprowadz.setText(btn1.getText().toString());
                } else if (btn1.getText().toString() == "?") {
                    wprowadz.append(btn1.getText().toString());
                } else if (btn1.getText().toString() == ".") {
                    wprowadz.append(btn1.getText().toString());
                } else if (b2ON == true | b3ON == true | b4ON == true | b5ON == true | b6ON == true | b7ON == true | b8ON == true) {
                    wprowadz.append(" ");
                    wprowadz.append(btn1.getText().toString());
                }
                if (b1ON == true) {
                    btn1.setClickable(false);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2ON = true;
                if (wprowadz.getText().toString().isEmpty()) {
                    wprowadz.setText(btn2.getText().toString());
                } else if (btn2.getText().toString() == "?") {
                    wprowadz.append(btn2.getText().toString());
                } else if (btn2.getText().toString() == ".") {
                    wprowadz.append(btn2.getText().toString());
                } else if (b5ON == true | b3ON == true | b4ON == true | b1ON == true | b6ON == true | b7ON == true | b8ON == true) {
                    wprowadz.append(" ");
                    wprowadz.append(btn2.getText().toString());
                }
                if (b2ON == true) {
                    btn2.setClickable(false);
                }

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b3ON = true;
                if (wprowadz.getText().toString().isEmpty()) {
                    wprowadz.setText(btn3.getText().toString());
                } else if (btn3.getText().toString() == "?") {
                    wprowadz.append(btn3.getText().toString());
                } else if (btn3.getText().toString() == ".") {
                    wprowadz.append(btn3.getText().toString());
                } else if (b2ON == true | b5ON == true | b4ON == true | b1ON == true | b6ON == true | b7ON == true | b8ON == true) {
                    wprowadz.append(" ");
                    wprowadz.append(btn3.getText().toString());
                }
                if (b3ON == true) {
                    btn3.setClickable(false);
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b4ON = true;
                if (wprowadz.getText().toString().isEmpty()) {
                    wprowadz.setText(btn4.getText().toString());
                } else if (btn4.getText().toString() == "?") {
                    wprowadz.append(btn4.getText().toString());
                } else if (btn4.getText().toString() == ".") {
                    wprowadz.append(btn4.getText().toString());
                } else if (b2ON == true | b3ON == true | b5ON == true | b1ON == true | b6ON == true | b7ON == true | b8ON == true) {
                    wprowadz.append(" ");
                    wprowadz.append(btn4.getText().toString());
                }
                if (b4ON == true) {
                    btn4.setClickable(false);
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b5ON = true;
                if (wprowadz.getText().toString().isEmpty()) {
                    wprowadz.setText(btn5.getText().toString());
                } else if (btn5.getText().toString() == "?") {
                    wprowadz.append(btn5.getText().toString());
                } else if (btn5.getText().toString() == ".") {
                    wprowadz.append(btn5.getText().toString());
                } else if (b2ON == true | b3ON == true | b4ON == true | b1ON == true | b6ON == true | b7ON == true | b8ON == true) {
                    wprowadz.append(" ");
                    wprowadz.append(btn5.getText().toString());
                }
                if (b5ON == true) {
                    btn5.setClickable(false);
                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b6ON = true;
                if (wprowadz.getText().toString().isEmpty()) {
                    wprowadz.setText(btn6.getText().toString());
                } else if (btn6.getText().toString() == "?") {
                    wprowadz.append(btn6.getText().toString());
                } else if (btn6.getText().toString() == ".") {
                    wprowadz.append(btn6.getText().toString());
                } else if (b2ON == true | b3ON == true | b4ON == true | b1ON == true | b5ON == true | b7ON == true | b8ON == true) {
                    wprowadz.append(" ");
                    wprowadz.append(btn6.getText().toString());
                }
                if (b6ON == true) {
                    btn6.setClickable(false);
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b7ON = true;
                if (wprowadz.getText().toString().isEmpty()) {
                    wprowadz.setText(btn7.getText().toString());
                } else if (btn7.getText().toString() == "?") {
                    wprowadz.append(btn7.getText().toString());
                } else if (btn7.getText().toString() == ".") {
                    wprowadz.append(btn7.getText().toString());
                } else if (b2ON == true | b3ON == true | b4ON == true | b1ON == true | b5ON == true | b6ON == true | b8ON == true) {
                    wprowadz.append(" ");
                    wprowadz.append(btn7.getText().toString());
                }
                if (b7ON == true) {
                    btn7.setClickable(false);
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b8ON = true;
                if (wprowadz.getText().toString().isEmpty()) {
                    wprowadz.setText(btn8.getText().toString());
                } else if (btn8.getText().toString() == "?") {
                    wprowadz.append(btn8.getText().toString());
                } else if (btn8.getText().toString() == ".") {
                    wprowadz.append(btn8.getText().toString());
                } else if (b2ON == true | b3ON == true | b4ON == true | b1ON == true | b5ON | b6ON == true | b7ON == true) {
                    wprowadz.append(" ");
                    wprowadz.append(btn8.getText().toString());
                }
                if (b8ON == true) {
                    btn8.setClickable(false);
                }
            }
        });
        sprawdź.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String napisane_zdanie = wprowadz.getText().toString();
                String zdanie_z_bazy = zdania_en.get(ilość_iteracji).toString();
                if (napisane_zdanie.toString().equals(zdanie_z_bazy.toString())) {
                    sprawdź.setText("Poprawnie");
                    sprawdź.setBackgroundColor(Color.parseColor("#38ea3e"));
                    sprawdź.setClickable(false);
                    clear.setClickable(false);
                    poprawne_odp++;
                    ilość_iteracji++;
                } else {
                    sprawdź.setText("Błędnie");
                    sprawdź.setBackgroundColor(Color.parseColor("#d11f34"));
                    sprawdź.setClickable(false);
                    bledne_odp++;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            sprawdź.setText("Sprawdź");
                            sprawdź.setBackgroundColor(Color.parseColor("#e7e2e2"));
                            sprawdź.setClickable(true);
                        }
                    }, 3000);
                }

                słowa_ze_zdania_en.clear();
                ilość_słów_w_zdaniu = 0;
                dalej.setVisibility(View.VISIBLE);
                if (ilość_iteracji == 5) {
                    dalej.setText("Zakończ lekcję");
                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setClickable(true);
                btn2.setClickable(true);
                btn3.setClickable(true);
                btn4.setClickable(true);
                btn5.setClickable(true);
                btn6.setClickable(true);
                btn7.setClickable(true);
                btn8.setClickable(true);

                wprowadz.getText().clear();
            }
        });
    }

    public void uzupelnij() {
        if (ilość_iteracji == 16) {
            startActivity(new Intent(Rozsypanka.this, BocznyPasekLewy.class));
        }
        //utworzenie randoma do wartości do długości listy słowa_ze_zdanie_en
        int rozmiar = 0;
        Random r = new Random();
        int index1, index2, index3, index4, index5, index6, index7, index8 = 0;
        btn1.setClickable(true);
        btn2.setClickable(true);
        btn3.setClickable(true);
        btn4.setClickable(true);
        btn5.setClickable(true);
        btn6.setClickable(true);
        btn7.setClickable(true);
        btn8.setClickable(true);
        if (ilość_iteracji <= 15) {
            //if (ilość_iteracji <= zdania_en.size()) {
            zdanie_pl.setText(zdania_pl.get(ilość_iteracji));
            słowa_ze_zdania_en = pobierzSłowa(zdania_en.get(ilość_iteracji));
            rozmiar = słowa_ze_zdania_en.size();
            ilość_słów_w_zdaniu = słowa_ze_zdania_en.size();
            //przypisywanie słów do buttonów
            if (ilość_słów_w_zdaniu == 1) {
                btn1.setVisibility(View.VISIBLE);
                index1 = r.nextInt(rozmiar) + 0;
                btn1.setText(słowa_ze_zdania_en.get(index1));
            } else if (ilość_słów_w_zdaniu == 2) {
                btn1.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                index1 = r.nextInt(rozmiar) + 0;
                btn1.setText(słowa_ze_zdania_en.get(index1));
                do {
                    index2 = r.nextInt(rozmiar) + 0;
                } while (index1 == index2);
                btn2.setText(słowa_ze_zdania_en.get(index2));
            } else if (ilość_słów_w_zdaniu == 3) {
                btn1.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);
                index1 = r.nextInt(rozmiar) + 0;

                btn1.setText(słowa_ze_zdania_en.get(index1));
                do {
                    index2 = r.nextInt(rozmiar) + 0;
                } while (index1 == index2);
                btn2.setText(słowa_ze_zdania_en.get(index2));
                do {
                    index3 = r.nextInt(rozmiar) + 0;
                } while (index3 == index2 | index3 == index1);
                btn3.setText(słowa_ze_zdania_en.get(index3));
            } else if (ilość_słów_w_zdaniu == 4) {
                btn1.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.VISIBLE);
                index1 = r.nextInt(rozmiar) + 0;
                btn1.setText(słowa_ze_zdania_en.get(index1));
                do {
                    index2 = r.nextInt(rozmiar) + 0;
                } while (index1 == index2);
                btn2.setText(słowa_ze_zdania_en.get(index2));
                do {
                    index3 = r.nextInt(rozmiar) + 0;
                } while (index3 == index2 | index3 == index1);
                btn3.setText(słowa_ze_zdania_en.get(index3));
                do {
                    index4 = r.nextInt(rozmiar) + 0;
                } while (index4 == index3 | index4 == index2 | index4 == index1);
                btn4.setText(słowa_ze_zdania_en.get(index4));
            } else if (ilość_słów_w_zdaniu == 5) {
                btn1.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.VISIBLE);
                btn5.setVisibility(View.VISIBLE);
                index1 = r.nextInt(rozmiar) + 0;
                btn1.setText(słowa_ze_zdania_en.get(index1));
                do {
                    index2 = r.nextInt(rozmiar) + 0;
                } while (index1 == index2);
                btn2.setText(słowa_ze_zdania_en.get(index2));
                do {
                    index3 = r.nextInt(rozmiar) + 0;
                } while (index3 == index2 | index3 == index1);
                btn3.setText(słowa_ze_zdania_en.get(index3));
                do {
                    index4 = r.nextInt(rozmiar) + 0;
                } while (index4 == index3 | index4 == index2 | index4 == index1);
                btn4.setText(słowa_ze_zdania_en.get(index4));
                do {
                    index5 = r.nextInt(rozmiar) + 0;
                } while (index5 == index4 | index5 == index3 | index5 == index2 | index5 == index1);
                btn5.setText(słowa_ze_zdania_en.get(index5));
            } else if (ilość_słów_w_zdaniu == 6) {
                btn1.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.VISIBLE);
                btn5.setVisibility(View.VISIBLE);
                btn6.setVisibility(View.VISIBLE);
                index1 = r.nextInt(rozmiar) + 0;
                btn1.setText(słowa_ze_zdania_en.get(index1));
                do {
                    index2 = r.nextInt(rozmiar) + 0;
                } while (index1 == index2);
                btn2.setText(słowa_ze_zdania_en.get(index2));
                do {
                    index3 = r.nextInt(rozmiar) + 0;
                } while (index3 == index2 | index3 == index1);
                btn3.setText(słowa_ze_zdania_en.get(index3));
                do {
                    index4 = r.nextInt(rozmiar) + 0;
                } while (index4 == index3 | index4 == index2 | index4 == index1);
                btn4.setText(słowa_ze_zdania_en.get(index4));
                do {
                    index5 = r.nextInt(rozmiar) + 0;
                } while (index5 == index4 | index5 == index3 | index5 == index2 | index5 == index1);
                btn5.setText(słowa_ze_zdania_en.get(index5));
                do {
                    index6 = r.nextInt(rozmiar) + 0;
                }
                while (index6 == index5 | index6 == index4 | index6 == index3 | index6 == index2 | index6 == index1);
                btn6.setText(słowa_ze_zdania_en.get(index6));
            } else if (ilość_słów_w_zdaniu == 7) {
                btn1.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.VISIBLE);
                btn5.setVisibility(View.VISIBLE);
                btn6.setVisibility(View.VISIBLE);
                btn7.setVisibility(View.VISIBLE);
                index1 = r.nextInt(rozmiar) + 0;
                btn1.setText(słowa_ze_zdania_en.get(index1));
                do {
                    index2 = r.nextInt(rozmiar) + 0;
                } while (index1 == index2);
                btn2.setText(słowa_ze_zdania_en.get(index2));
                do {
                    index3 = r.nextInt(rozmiar) + 0;
                } while (index3 == index2 | index3 == index1);
                btn3.setText(słowa_ze_zdania_en.get(index3));
                do {
                    index4 = r.nextInt(rozmiar) + 0;
                } while (index4 == index3 | index4 == index2 | index4 == index1);
                btn4.setText(słowa_ze_zdania_en.get(index4));
                do {
                    index5 = r.nextInt(rozmiar) + 0;
                } while (index5 == index4 | index5 == index3 | index5 == index2 | index5 == index1);
                btn5.setText(słowa_ze_zdania_en.get(index5));
                do {
                    index6 = r.nextInt(rozmiar) + 0;
                }
                while (index6 == index5 | index6 == index4 | index6 == index3 | index6 == index2 | index6 == index1);
                btn6.setText(słowa_ze_zdania_en.get(index6));
                do {
                    index7 = r.nextInt(rozmiar) + 0;
                }
                while (index7 == index6 | index7 == index5 | index7 == index4 | index7 == index3 | index7 == index2 | index7 == index1);
                btn7.setText(słowa_ze_zdania_en.get(index7));

            } else if (ilość_słów_w_zdaniu == 8) {
                btn1.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
                btn3.setVisibility(View.VISIBLE);
                btn4.setVisibility(View.VISIBLE);
                btn5.setVisibility(View.VISIBLE);
                btn6.setVisibility(View.VISIBLE);
                btn7.setVisibility(View.VISIBLE);
                btn8.setVisibility(View.VISIBLE);
                index1 = r.nextInt(rozmiar) + 0;
                btn1.setText(słowa_ze_zdania_en.get(index1));
                do {
                    index2 = r.nextInt(rozmiar) + 0;
                } while (index1 == index2);
                btn2.setText(słowa_ze_zdania_en.get(index2));
                do {
                    index3 = r.nextInt(rozmiar) + 0;
                } while (index3 == index2 | index3 == index1);
                btn3.setText(słowa_ze_zdania_en.get(index3));
                do {
                    index4 = r.nextInt(rozmiar) + 0;
                } while (index4 == index3 | index4 == index2 | index4 == index1);
                btn4.setText(słowa_ze_zdania_en.get(index4));
                do {
                    index5 = r.nextInt(rozmiar) + 0;
                } while (index5 == index4 | index5 == index3 | index5 == index2 | index5 == index1);
                btn5.setText(słowa_ze_zdania_en.get(index5));
                do {
                    index6 = r.nextInt(rozmiar) + 0;
                }
                while (index6 == index5 | index6 == index4 | index6 == index3 | index6 == index2 | index6 == index1);
                btn6.setText(słowa_ze_zdania_en.get(index6));
                do {
                    index7 = r.nextInt(rozmiar) + 0;
                }
                while (index7 == index6 | index7 == index5 | index7 == index4 | index7 == index3 | index7 == index2 | index7 == index1);
                btn7.setText(słowa_ze_zdania_en.get(index7));
                do {
                    index8 = r.nextInt(rozmiar) + 0;
                }
                while (index8 == index7 | index8 == index6 | index8 == index5 | index8 == index4 | index8 == index3 | index8 == index2 | index8 == index1);
                btn8.setText(słowa_ze_zdania_en.get(index8));
            } else {
                Toast.makeText(getApplicationContext(), "Jeszcze inaczej", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public ArrayList<String> pobierzSłowa(String zdanie) {
        ArrayList<String> słowa = new ArrayList<String>();
        BreakIterator breakIterator = BreakIterator.getWordInstance();
        breakIterator.setText(zdanie);
        int lastIndex = breakIterator.first();
        Pattern pattern_dots = Pattern.compile("([^\\.]*)\\.");
        Matcher matcher_dots = pattern_dots.matcher(zdanie);
        while (matcher_dots.find()) {
            słowa.add(".");
        }
        Pattern pattern_question = Pattern.compile("([^?]*)\\?");
        Matcher matcher_question = pattern_question.matcher(zdanie);
        while (matcher_question.find()) {
            słowa.add("?");
        }
        while (BreakIterator.DONE != lastIndex) {
            int firstIndex = lastIndex;
            lastIndex = breakIterator.next();
            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(zdanie.charAt(firstIndex))) {
                słowa.add(zdanie.substring(firstIndex, lastIndex));
            }
        }
        return słowa;
    }

    public void zapisz_statytyski() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userID = prefs.getString("id", "0");
        Date currentTime = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        String date = DateFormat.format("yyyy-MM-dd hh:mm:ss", cal).toString();
        db.insertIntoLessons(Integer.parseInt(userID), id_zadania, String.valueOf(poprawne_odp), String.valueOf(bledne_odp), date);
    }

    public void onBackPressed() {
        startActivity(new Intent(Rozsypanka.this, WyborKategorii.class));
    }
}
