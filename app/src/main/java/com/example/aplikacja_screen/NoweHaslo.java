package com.example.aplikacja_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.m.aplikacja_screen.R;

public class NoweHaslo extends AppCompatActivity {

    Button ustaw_nowe_haslo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowe_haslo);

        ustaw_nowe_haslo = (Button) findViewById(R.id.button5);
        ustaw_nowe_haslo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aktualizacja w bazie
                startActivity(new Intent(NoweHaslo.this, MainActivity.class));
            }
        });
    }
}
