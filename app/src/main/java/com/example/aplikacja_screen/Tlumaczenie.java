package com.example.aplikacja_screen;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m.aplikacja_screen.R;

import org.w3c.dom.Text;

public class Tlumaczenie extends AppCompatActivity {

    Button sprawdz_poprawnosc,dalej;
    EditText en;
    TextView pl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tlumaczenie);
        sprawdz_poprawnosc=(Button)findViewById(R.id.button17);
        dalej=(Button)findViewById(R.id.button20);
        pl=(TextView) findViewById(R.id.textView21);
        en=(EditText)findViewById(R.id.textView22);

        pl.setText("zielony");
        sprawdz_poprawnosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if en.getText== słowo z bazy to komunikat poprawna odpowiedź
                if(en.getText().toString().equals("green")){
                  // Toast.makeText(getApplicationContext(),"Poprawna odpowiedź",Toast.LENGTH_SHORT).show();
                   sprawdz_poprawnosc.setText("Poprawna odpowiedź");
                   sprawdz_poprawnosc.setBackgroundColor(Color.parseColor("#38ea3e"));
                }else{
                   // dialogAlertNegative();
                    //Toast.makeText(getApplicationContext(),"Niepoprawna odpowiedź",Toast.LENGTH_SHORT).show();
                    sprawdz_poprawnosc.setText("Niepoprawna odpowiedź");
                    sprawdz_poprawnosc.setBackgroundColor(Color.parseColor("#d11f34"));
                    AlertDialog alertDialog = new AlertDialog.Builder(Tlumaczenie.this).create();
                    alertDialog.setTitle("Poprawna odpowiedz");
                    alertDialog.setMessage("\nzielony - green");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });

        dalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                en.setText(" ");
                sprawdz_poprawnosc.setText("Sprawdź poprawność");
                sprawdz_poprawnosc.setBackgroundColor(Color.parseColor("#e6e1e1"));
            }
        });
    }
    //alert dialog
}
