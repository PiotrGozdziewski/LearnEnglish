package com.example.aplikacja_screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Database.Database;
import com.example.Uzytkownik;
import com.example.m.aplikacja_screen.R;

public class MainActivity extends AppCompatActivity {

    public static final String PATH = "/sdcard/Download/";

    CardView zaloguj;
    CardView zarejestruj;
    TextView login;
    TextView haslo;
    TextView zapomniane_hasło;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(getContentResolver());
        zaloguj = (CardView) findViewById(R.id.cardView);
        zarejestruj = (CardView) findViewById(R.id.cardView2);
        login = (TextView) findViewById(R.id.editText6);
        haslo = (TextView) findViewById(R.id.editText7);



        zaloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String l = login.getText().toString();
                String p = haslo.getText().toString();

                Uzytkownik uzytkownik = db.loginUser(l, p);

                if (uzytkownik != null) {
                    Intent intent = new Intent(MainActivity.this, BocznyPasekLewy.class);
                    intent.putExtra("user", uzytkownik);
                    Toast.makeText(getApplicationContext(), "good", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Nieprawidłowe dane!", Toast.LENGTH_LONG).show();
                }
            }
        });

        zarejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Rejestracja.class));
            }
        });

        String x = "login użytkownika";
        zapomniane_hasło = (TextView) findViewById(R.id.textView5);
        zapomniane_hasło.setText("Zapomniałeś hasła ?");
        //  zapomniane_hasło.setMovementMethod(LinkMovementMethod.getInstance());

        zapomniane_hasło.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ZapomnianeHaslo.class));
            }
        });


    }
}
