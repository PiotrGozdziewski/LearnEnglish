package com.example.m.aplikacja_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    CardView dodaj_uzytkownika;
    Spinner pytanie_podpowiedz;
    EditText odpowiedz;
    TextView wybierz_pytanie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        dodaj_uzytkownika=(CardView)findViewById(R.id.cardView3);
        dodaj_uzytkownika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Dodano nowego użytkownika",Toast.LENGTH_LONG).show();
                startActivity(new Intent(Main3Activity.this,MainActivity.class));
            }
        });

        pytanie_podpowiedz=(Spinner)findViewById(R.id.spinner);
      //  pytanie_podpowiedz.setOnItemClickListener((AdapterView.OnItemClickListener) this);


        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.pytania,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pytanie_podpowiedz.setAdapter(adapter);
        pytanie_podpowiedz.setPrompt("Wybierz pytanie");

        wybierz_pytanie=(TextView)findViewById(R.id.textView6);
        odpowiedz=(EditText)findViewById(R.id.textView7);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
