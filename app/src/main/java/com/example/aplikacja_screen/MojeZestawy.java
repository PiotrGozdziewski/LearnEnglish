package com.example.aplikacja_screen;

import android.content.Intent;
import android.database.Cursor;
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
        //loadRecyclerViewItem();
        Cursor cursor=db.getSets();
        String s=cursor.getString(3);
        //String s=String.valueOf(c);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
//        while(cursor.moveToNext()){
//            Zestaw myList=new Zestaw(cursor.getInt(1),cursor.getInt(2),cursor.getString(3));
//            list.add(myList);
//        }
        cursor.close();
//        adapter=new ZestawAdapter(list,this);
//        recyclerView.setAdapter(adapter);

        ///////////////////////////////////////////
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MojeZestawy.this, TworzenieZestawu.class));
            }
        });
    }

    public void loadRecyclerViewItem(){

        for(int i=1;i<=3;i++)
        {
            //Zestaw myList=new Zestaw("Zestaw "+ i,"Ilosz fiszek");
            //list.add(myList);
        }
        adapter=new ZestawAdapter(list,this);
        recyclerView.setAdapter(adapter);

    }

    public void onBackPressed(){
        startActivity(new Intent(MojeZestawy.this,BocznyPasekLewy.class));
    }
}