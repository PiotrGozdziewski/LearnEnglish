package com.example.m.aplikacja_screen;

import android.content.Intent;
import android.media.tv.TvInputService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.PasswordAuthentication;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    CardView zaloguj;
    CardView zarejestruj;
    TextView login;
    TextView haslo;
    TextView zapomniane_hasło;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        zaloguj=(CardView)findViewById(R.id.cardView);
        zarejestruj=(CardView)findViewById(R.id.cardView2);
        login=(TextView)findViewById(R.id.editText6);
        haslo=(TextView)findViewById(R.id.editText7);
        zaloguj.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                     Toast.makeText(getApplicationContext(), "Poprawnie zalogowano.", Toast.LENGTH_LONG).show();
                     login.setText("");
                     haslo.setText("");

                     //przejscie do kolejnego ekranu
                     startActivity(new Intent(MainActivity.this, Main2Activity.class));

             }}
        );

        zarejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main3Activity.class));
            }
        });

        String x="login użytkownika";
        zapomniane_hasło=(TextView)findViewById(R.id.textView5);
      //  zapomniane_hasło.setMovementMethod(LinkMovementMethod.getInstance());
        zapomniane_hasło.setText(Html.fromHtml("<a href=\"mailto:administrator@x.com?&amp;subject=Zapomniane%20hasło\n" +
                "&amp;body=Login: "+x+"\">Zapomniałeś hasła?</a>"));
        zapomniane_hasło.setMovementMethod(LinkMovementMethod.getInstance());

        zapomniane_hasło.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

     
    }
}
