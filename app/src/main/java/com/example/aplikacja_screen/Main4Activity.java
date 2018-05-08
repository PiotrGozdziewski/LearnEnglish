package com.example.aplikacja_screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.m.aplikacja_screen.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        GridView kategorie;
        kategorie=(GridView)findViewById(R.id.gridView1);

        String[] kat={"Człowiek","Dom","Szkoła","Praca","Zdrowie","Sport","Kultura","Swiat przyrody",
                "Podróżowanie","Żywienie","Zakupy i usługi","Życie rodzinne i towarzyskie"};
        String[] kat_ang={"Human","Home","School","Work","Health","Sport","Culture","World of nature",
                "Travel","Food","Shopping & services","Family life"};

        ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
        for(int i=0;i<kat.length;i++)
        {
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap=new HashMap();
            hashMap.put("KategoriaANG",kat_ang[i]);
            hashMap.put("Kategoria",kat[i]);
            arrayList.add(hashMap);
        }

        String[] from={"KategoriaANG","Kategoria"};
        int[] to={R.id.KategoriaANG,R.id.Kategoria};
        SimpleAdapter adapter=new SimpleAdapter(this,arrayList,R.layout.item_categories,from,to);
        kategorie.setAdapter(adapter);
    }
}
