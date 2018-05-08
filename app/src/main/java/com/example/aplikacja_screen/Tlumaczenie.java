package com.example.aplikacja_screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.m.aplikacja_screen.R;

public class Tlumaczenie extends AppCompatActivity {

    Button sprawdz_poprawnosc,dalej;
    EditText pl,en;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tlumaczenie);
        sprawdz_poprawnosc=(Button)findViewById(R.id.button17);
        dalej=(Button)findViewById(R.id.button20);
        pl=(EditText) findViewById(R.id.textView21);
        en=(EditText)findViewById(R.id.textView22);

        sprawdz_poprawnosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if en.getText== słowo z bazy to komunikat poprawna odpowiedź
                
            }
        });
    }
}
