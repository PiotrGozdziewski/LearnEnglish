package com.example.aplikacja_screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.m.aplikacja_screen.R;

import java.util.ArrayList;

public class Laczenie extends AppCompatActivity {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laczenie);

        b1 = (Button) findViewById(R.id.button21);
        b2 = (Button) findViewById(R.id.button22);
        b3 = (Button) findViewById(R.id.button23);
        b4 = (Button) findViewById(R.id.button24);
        b5 = (Button) findViewById(R.id.button25);
        b6 = (Button) findViewById(R.id.button26);
        b7 = (Button) findViewById(R.id.button27);
        b8 = (Button) findViewById(R.id.button28);
        b9 = (Button) findViewById(R.id.button29);
        b10 = (Button) findViewById(R.id.button30);
    }
}
