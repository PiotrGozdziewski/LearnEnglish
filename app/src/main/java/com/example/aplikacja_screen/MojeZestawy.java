package com.example.aplikacja_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.Database.Database;
import com.example.Database.SetsContract;
import com.example.Uzytkownik;
import com.example.Zestaw;
import com.example.m.aplikacja_screen.R;

import java.util.ArrayList;
import java.util.List;

public class MojeZestawy extends AppCompatActivity {

    private static final String TAG ="MojeZestawy";
    Button dodaj;
    Database db;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<Zestaw> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moje_zestawy);
        dodaj = (Button) findViewById(R.id.button);
        db=new Database(getContentResolver());
        ////////////////////////////////////////////
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        loadRecyclerViewItem();

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MojeZestawy.this, TworzenieZestawu.class));
            }
        });
    }

    public void loadRecyclerViewItem(){

        int nr=0;
        String nazwa="";
        //pobranie id_uzytkownika aktualnie zalogowanego
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userID=prefs.getString("id","0"); //id zalogowanego

        Cursor cursor=db.getSets();
        while(cursor.moveToNext()){
            int nr_uzytkownika=cursor.getInt(1); //pobranie id z bazy
            String nr_uzytkownika_baza=String.valueOf(nr_uzytkownika);
            if(userID.equals(nr_uzytkownika_baza)){
                nr=cursor.getInt(0);
                nazwa=cursor.getString(2);
                Zestaw myList=new Zestaw(nr,nr_uzytkownika,nazwa);
                list.add(myList);
            }
        }

        adapter=new ZestawAdapter(list,this);
        recyclerView.setAdapter(adapter);
    }

    public void onBackPressed(){
        startActivity(new Intent(MojeZestawy.this,BocznyPasekLewy.class));
    }
}