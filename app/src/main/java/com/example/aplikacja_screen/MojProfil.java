package com.example.aplikacja_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;

//co tu ma byc
//zmienianie loginu,hasla
//wybor czy chcemy powiadomieniatry
public class MojProfil extends AppCompatActivity {

    TabHost tabHost;
    ToggleButton tb;
    EditText login, stare_haslo, nowe_haslo, nowe_haslo2;
    Button zmien_login, zmien_haslo;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moj_profil);
        db = new Database(getContentResolver());
        tb = (ToggleButton) findViewById(R.id.toggleButton);
        login = (EditText)findViewById(R.id.textView15);
        zmien_login = (Button)findViewById(R.id.button8);
        stare_haslo = (EditText)findViewById(R.id.textView16);
        nowe_haslo = (EditText)findViewById(R.id.textView17);
        nowe_haslo2 = (EditText)findViewById(R.id.textView18);
        zmien_haslo = (Button)findViewById(R.id.button9);

        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec s1 = tabHost.newTabSpec("Login");
        s1.setContent(R.id.tab1);
        s1.setIndicator("Ustawianie loginu");

        tabHost.addTab(s1);

        s1 = tabHost.newTabSpec("Hasło");
        s1.setContent(R.id.tab2);
        s1.setIndicator("Ustawianie hasła");
        tabHost.addTab(s1);

        s1 = tabHost.newTabSpec("Inne");
        s1.setContent(R.id.tab3);
        s1.setIndicator("Inne");
        tabHost.addTab(s1);

        //zmiana loginu
        zmien_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pobranie id aktualnego uzytkownika
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String userID = prefs.getString("id", "0");
                //zmiana loginu
                String nowy_login=login.getText().toString();
                db.updateUserLogin(Integer.parseInt(userID),nowy_login);
                Toast.makeText(getApplicationContext(),"Poprawnie zmieniono login",Toast.LENGTH_SHORT).show();
                login.setText(" ");
            }
        });

        zmien_haslo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pobranie id aktualnego uzytkownika
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String userID = prefs.getString("id", "0");
                //sprawdzenie czy stare haslo zostalo poprawnie wprowadzone
                Cursor c=db.getPassword(Integer.parseInt(userID));
                while(c.moveToNext())
                {
                    if(c.getString(2).equals(stare_haslo.getText().toString()))
                    {
                        if(nowe_haslo.getText().toString().equals(nowe_haslo2.getText().toString())
                                &&(!nowe_haslo.getText().toString().equals(stare_haslo.getText().toString()))
                                &&(!nowe_haslo.getText().toString().equals(""))&&(!nowe_haslo2.getText().toString().equals("")))
                        {
                            //zmiana hasla w bazie
                            db.updateUserPassword(Integer.parseInt(userID),nowe_haslo.getText().toString());
                            Toast.makeText(getApplicationContext(),"Poprawnie zmieniono haslo",Toast.LENGTH_SHORT).show();
                            nowe_haslo2.setText("");
                            nowe_haslo.setText("");
                            stare_haslo.setText("");
                            //wylogowanie z aplikacji po zmianie hasla
                            startActivity(new Intent(MojProfil.this,MainActivity.class));
                        }else if(nowe_haslo.getText().toString().equals("")||nowe_haslo2.getText().toString().equals(""))
                        {
                            Toast.makeText(getApplicationContext(), "Pola nie mogą być puste.", Toast.LENGTH_LONG).show();
                        }else if(nowe_haslo.getText().toString().equals(stare_haslo.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(), "Nowe hasło nie może być identyczne jak stare hasło.", Toast.LENGTH_SHORT).show();
                            nowe_haslo.setText("");
                            nowe_haslo2.setText("");
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Podane hasla sie roznia. Wprowadz ponownie.",Toast.LENGTH_SHORT).show();
                            nowe_haslo.setText("");
                            nowe_haslo2.setText("");
                        }
                    }
                }


            }
        });
    }

    public void onToggleClicked(View v) {
        boolean on = ((ToggleButton) v).isChecked();

        if (on) {
            //wlacz przypomnienie
        } else {
            //wylacz przypomnienie
        }
    }
}
