package com.example.m.aplikacja_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EdytowanieFiszki extends AppCompatActivity {

    EditText pl;
    EditText en;
    Button edytuj;
    int liczba; //indeks edytowanej fiszki
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edytowanie_fiszki);

        pl=(EditText)findViewById(R.id.textView19);
        en=(EditText)findViewById(R.id.editText20);
        edytuj=(Button)findViewById(R.id.button11);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            String p=extras.getString("pl");
            pl.setText(p);
            String e=extras.getString("en");
            en.setText(e);
            liczba=extras.getInt("liczba");
        }
        edytuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tutaj kod uaktualnienia danych w bazie danych --Intent nie chciał działać
                startActivity(new Intent(EdytowanieFiszki.this,EdytowanieZestawu.class));
            }
        });
    }
    public void onBackPressed(){
        startActivity(new Intent(EdytowanieFiszki.this,Moje_zestawy.class));
    }
}
