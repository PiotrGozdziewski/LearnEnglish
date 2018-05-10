package com.example.aplikacja_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.m.aplikacja_screen.R;

import java.util.ArrayList;
import java.util.List;

public class MojeZestawy extends AppCompatActivity {

    Button dodaj;
    TextView option;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;


    List<Zestaw> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moje_zestawy);
        dodaj = (Button) findViewById(R.id.button);
       // option=(TextView)findViewById(R.id.textViewOptions);

        ////////////////////////////////////////////
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        loadRecyclerViewItem();

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
            Zestaw myList=new Zestaw("Zestaw "+i,"Ilość fiszek ");
            list.add(myList);
        }
        adapter=new ZestawAdapter(list,this);
        recyclerView.setAdapter(adapter);

    }

    public void onBackPressed(){
        startActivity(new Intent(MojeZestawy.this,BocznyPasekLewy.class));
    }
}