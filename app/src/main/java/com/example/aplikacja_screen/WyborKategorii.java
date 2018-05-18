package com.example.aplikacja_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.m.aplikacja_screen.R;

import java.util.ArrayList;
import java.util.HashMap;

public class WyborKategorii extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor_kategorii);

        GridView kategorie;
        kategorie = (GridView) findViewById(R.id.gridView1);

<<<<<<< HEAD
        String[] kat = {"Człowiek", "Dom", "Szkoła", "Praca", "Zdrowie", "Sport", "Kultura", "Świat przyrody",
                "Podróżowanie", "Żywienie", "Zakupy i usługi", "Życie rodzinne i towarzyskie"};
        String[] kat_ang = {"Human", "Home", "School", "Work", "Health", "Sport", "Culture", "Nature",
                "Travel", "Food", "Shopping", "Family life"};
=======
        String[] kat = {"Człowiek", "Dom", "Szkoła", "Praca", "Żywienie", "Życie rodzinne", "Zakupy i usługi", "Podróżowanie", "Kultura", "Sport", "Zdrowie", "Świat przyrody"};
        String[] kat_ang = {"Human", "Home", "School", "Work","Food",  "Family life", "Shopping", "Travel","Culture", "Sport","Health", "Nature"};
>>>>>>> f94f7fe358f21b3274bda9e63d9881ec9c5969cf

        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        for (int i = 0; i < kat.length; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap = new HashMap();
            hashMap.put("KategoriaANG", kat_ang[i]);
            hashMap.put("Kategoria", kat[i]);
            arrayList.add(hashMap);
        }

        String[] from = {"KategoriaANG", "Kategoria"};
        int[] to = {R.id.KategoriaANG, R.id.Kategoria};
        final SimpleAdapter adapter = new SimpleAdapter(this, arrayList, R.layout.item_categories, from, to);
        kategorie.setAdapter(adapter);
        kategorie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = adapterView.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(),"wybrano"+ String.valueOf(position),Toast.LENGTH_LONG).show();
                //przeslanie wybranej pozycji
                SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor e = p.edit();
                int pozycja=position+1;
                e.putInt("idKategorii",pozycja);
                e.commit();
                startActivity(new Intent(WyborKategorii.this, WyborZadania.class));
            }
        });



    }
}
