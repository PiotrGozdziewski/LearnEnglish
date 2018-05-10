package com.example.aplikacja_screen;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Database.Database;
import com.example.Database.UsersContract;
import com.example.m.aplikacja_screen.R;

import java.util.ArrayList;
import java.util.List;

public class Rejestracja extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "Rejestracja";

    CardView dodaj_uzytkownika;
    TextView wybierz_pytanie;
    EditText imie;
    EditText login;
    EditText pass;
    EditText pass2;
    Spinner pytanie_podpowiedz;
    EditText odpowiedz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        imie = (EditText) findViewById(R.id.imieRej);
        login = (EditText) findViewById(R.id.loginRej);
        pass = (EditText) findViewById(R.id.passRej);
        pass2 = (EditText) findViewById(R.id.pass2Rej);
        wybierz_pytanie = (TextView) findViewById(R.id.textView6);
        pytanie_podpowiedz = (Spinner) findViewById(R.id.spinnerRej);
        odpowiedz = (EditText) findViewById(R.id.odpRej);

        final Database db = new Database(getContentResolver());

        dodaj_uzytkownika = (CardView) findViewById(R.id.cardView3);
        dodaj_uzytkownika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sImie = imie.getText().toString();
                String sLogin = login.getText().toString();
                String sPass = pass.getText().toString();
                String sPass2 = pass2.getText().toString();
                int pytanieId = (int) pytanie_podpowiedz.getSelectedItemId();
                String sOdp = odpowiedz.getText().toString();

                if (sImie.equals("") || sLogin.equals("") || sPass.equals("") || sPass2.equals("") || sOdp.equals("")) {
                    Toast.makeText(getApplicationContext(), "Zostawiono puste pola!", Toast.LENGTH_LONG).show();
                } else if (!sPass.equals(sPass2)) {
                    Toast.makeText(getApplicationContext(), "Podane hasła nie są identyczne!", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        Uri uri = db.insertIntoUsers(sLogin, sPass, sImie);
                        int newId = (int) UsersContract.getUserId(uri);
                        db.insertIntoHints(pytanieId, newId, sOdp);
                        startActivity(new Intent(Rejestracja.this, MainActivity.class));
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Podany login jest zajety!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        Cursor cursor = db.getQuestions();
        if (cursor.getCount() == 0) {
            db.insertIntoQuestions("Jaki numer ma twoja szkoła podstawowa?");
            db.insertIntoQuestions("Jak nazywa się twoje ulubione zwierzątko?");
            db.insertIntoQuestions("Jaką czekoladę lubisz najbardziej?");
            cursor.close();
            cursor = db.getQuestions();
        }
        List<String> questions = new ArrayList<>();

        while (cursor.moveToNext()) {
            questions.add(cursor.getString(1));
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, questions);
        pytanie_podpowiedz.setPrompt("Wybierz pytanie");
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pytanie_podpowiedz.setAdapter(adapter);


        //  pytanie_podpowiedz.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        // ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pytania, android.R.layout.simple_spinner_item);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // pytanie_podpowiedz.setAdapter(adapter);
        // pytanie_podpowiedz.setPrompt("Wybierz pytanie");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onBackPressed() {
        startActivity(new Intent(Rejestracja.this, MainActivity.class));
    }
}
