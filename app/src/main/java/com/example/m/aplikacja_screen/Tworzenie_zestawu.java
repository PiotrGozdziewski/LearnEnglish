package com.example.m.aplikacja_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class Tworzenie_zestawu extends AppCompatActivity {

    Button tworzenie_zestawu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tworzenie_zestawu);
        tworzenie_zestawu = (Button) findViewById(R.id.button2);

         tworzenie_zestawu.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //doadanie do bazy
                 startActivity(new Intent(Tworzenie_zestawu.this,Moje_zestawy.class));
             }
         });




    }
}
