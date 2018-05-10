package com.example.aplikacja_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.m.aplikacja_screen.R;

import java.util.ArrayList;

public class EdytowanieZestawu extends AppCompatActivity {

    String[] pl = {"dom", "noc", "słońce"};
    String[] en = {"home", "night", "sun"};
    String polskie, angielskie;
    int liczba; //indeks edytowanej fiszki
    ArrayList<String> zaznaczone_el = new ArrayList<>();
    ListView lv;
    Button edytuj_fiszke;
    String s;
    CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edytowanie_zestawu);

        edytuj_fiszke = (Button) findViewById(R.id.edytuj_fiszke);
        lv = (ListView) findViewById(R.id.listView);
        lv.setChoiceMode(lv.CHOICE_MODE_SINGLE);

        //polaczenie danych (słówka pl+en) z dwóch tablic
        String[] polaczenie = new String[pl.length];
        for (int i = 0; i < pl.length; i++) {
            polaczenie[i] = pl[i] + " - " + en[i];
        }
        //arrayList w adapterze i wyswietlanie w listView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_view_checkbox, R.id.checkedTextView, polaczenie);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                 s= (String) lv.getItemAtPosition(i); //tutaj dostajemy całą linijkę
                polskie = pl[i];
                angielskie = en[i];
                liczba = i;
            }
        });

        edytuj_fiszke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EdytowanieZestawu.this, EdytowanieFiszki.class);
                intent.putExtra("pl", polskie);
                intent.putExtra("en", angielskie);
                intent.putExtra("liczba", liczba);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed() {
        startActivity(new Intent(EdytowanieZestawu.this, MojeZestawy.class));
    }
}
