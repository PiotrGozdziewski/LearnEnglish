package com.example.aplikacja_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;

public class MainActivity extends AppCompatActivity {

    public static final String PATH = "/sdcard/Download/";

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

                 String l=login.getText().toString();
                 //Toast.makeText(getApplicationContext(), l, Toast.LENGTH_LONG).show();
                     login.setText("");
                     haslo.setText("");

                     Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                     intent.putExtra("keyName",l);
                     //przejscie do kolejnego ekranu
                     startActivity(intent);

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
        zapomniane_hasło.setText("Zapomniałeś hasła ?");
      //  zapomniane_hasło.setMovementMethod(LinkMovementMethod.getInstance());

        zapomniane_hasło.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Zapomniane_haslo.class));
            }
        });

     
    }
}
