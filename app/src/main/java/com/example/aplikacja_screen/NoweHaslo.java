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
import android.widget.Toast;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;

public class NoweHaslo extends AppCompatActivity {

    Button ustaw_nowe_haslo;
    EditText nowe_haslo;
    EditText nowe_haslo2;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowe_haslo);

        ustaw_nowe_haslo = (Button) findViewById(R.id.button5);
        nowe_haslo = (EditText)findViewById(R.id.editText4);
        nowe_haslo2 = (EditText)findViewById(R.id.editText5);
        db = new Database(getContentResolver());

        ustaw_nowe_haslo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aktualizacja w bazie
                //pobranie id aktualnego uzytkownika
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                int userID = prefs.getInt("userIDhaslo", 0);
                //sprawdzenie czy stare haslo zostalo poprawnie wprowadzone
                Cursor c=db.getUser(userID);
                while(c.moveToNext())
                {
                        if(nowe_haslo.getText().toString().equals(nowe_haslo2.getText().toString())
                                &&(!nowe_haslo.getText().toString().equals(""))&&(!nowe_haslo2.getText().toString().equals("")))
                        {
                            //zmiana hasla w bazie
                            db.updateUserPassword(userID,nowe_haslo.getText().toString());
                            Toast.makeText(getApplicationContext(),"Poprawnie zmieniono haslo",Toast.LENGTH_SHORT).show();
                            nowe_haslo2.setText("");
                            nowe_haslo.setText("");
                            //wylogowanie z aplikacji po zmianie hasla
                            startActivity(new Intent(NoweHaslo.this,MainActivity.class));
                        }else if(nowe_haslo.getText().toString().equals("")||nowe_haslo2.getText().toString().equals(""))
                        {
                            Toast.makeText(getApplicationContext(), "Pola nie mogą być puste.", Toast.LENGTH_LONG).show();
                        } else
                        {
                            Toast.makeText(getApplicationContext(),"Podane hasla sie roznia. Wprowadz ponownie.",Toast.LENGTH_SHORT).show();
                            nowe_haslo.setText("");
                            nowe_haslo2.setText("");
                        }
                    }
            }
        });
    }
}
