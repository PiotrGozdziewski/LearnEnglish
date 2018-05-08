package com.example.aplikacja_screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.m.aplikacja_screen.R;

public class Wybor extends AppCompatActivity {

    Button b1,b2,b3,b4,nastepne_slowo;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor);

        //14,16,18,19
        b1=(Button)findViewById(R.id.button14);
        b2=(Button)findViewById(R.id.button16);
        b3=(Button)findViewById(R.id.button18);
        b4=(Button)findViewById(R.id.button19);
        nastepne_slowo=(Button)findViewById(R.id.button15);

        t1=(TextView)findViewById(R.id.textView20);
        t1.setText("DOM");
        t1.setTextSize(20);
        b1.setText("House");
        b2.setText("Cow");
        b3.setText("Mountain");
        b4.setText("Sea");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listenera od buttona
                //jeżeli tekst z buttona równa się tekstowi z bazy
                //if(b1.getText()==)
                //to wtedy otoczka buttona na kolor zielony w przeciwnym wypadku na czerwony
                b1.setBackgroundResource(R.drawable.button_border_green);
                b2.setBackgroundResource(R.drawable.button_border_red);
                b3.setBackgroundResource(R.drawable.button_border_red);
                b4.setBackgroundResource(R.drawable.button_border_red);
                //jezeli b1 to zla odpowiedz to tą na czerwono i tylko jeszcze zaznaczyć poprawną na zielono
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setBackgroundResource(R.drawable.button_border_red);
                b1.setBackgroundResource(R.drawable.button_border_green);
            }
        });

        nastepne_slowo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tutaj kod do kolejnego słówka czyli w sumie ten sam ekran, tylko wszystko poczyszczone
            }
        });
    }

}
