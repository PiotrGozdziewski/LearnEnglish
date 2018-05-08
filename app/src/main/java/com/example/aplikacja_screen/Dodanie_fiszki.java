package com.example.aplikacja_screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.m.aplikacja_screen.R;

public class Dodanie_fiszki extends AppCompatActivity {

    Button dodaj_fiszke;
    EditText pl;
    EditText en;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodanie_fiszki);

        dodaj_fiszke=(Button)findViewById(R.id.button6);
        pl=(EditText)findViewById(R.id.editText9);
        en=(EditText)findViewById(R.id.editText10);
        dodaj_fiszke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pl.setText("");
                en.setText("");
            }
        });
    }
}
