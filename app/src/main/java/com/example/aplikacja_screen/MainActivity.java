package com.example.aplikacja_screen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Database.Database;
import com.example.SHA256;
import com.example.Uzytkownik;
import com.example.m.aplikacja_screen.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {
    public static final String PATH = "/sdcard/Download/";

    CardView zaloguj;
    CardView zarejestruj;
    TextView login;
    TextView haslo;
    TextView zapomniane_hasło;
    Database db;

    public void exportDatabse() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "/app/src/main/assets/LearnEnglish.db";
                String backupDBPath = "learnEnglish.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(getContentResolver());
        zaloguj = (CardView) findViewById(R.id.cardView);
        zarejestruj = (CardView) findViewById(R.id.cardView2);
        login = (TextView) findViewById(R.id.editText6);
        haslo = (TextView) findViewById(R.id.editText7);
        zapomniane_hasło = (TextView) findViewById(R.id.textView5);

        //od zgrania bazy na telefon
        exportDatabse();
        //
        zaloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String l = login.getText().toString();
                String p = haslo.getText().toString();

                SHA256 sha256 = new SHA256(p);
                String encodedPass = sha256.getEncoded();

                Uzytkownik uzytkownik = db.loginUser(l, encodedPass);
                if (uzytkownik != null) {
                    Intent intent = new Intent(MainActivity.this, BocznyPasekLewy.class);
                    intent.putExtra("user", uzytkownik);

                    // pobranie wartosci id uzytkownika
                    int id = uzytkownik.getId();
                    String id_uzytkownik = String.valueOf(id);
                    String nickname = uzytkownik.getLogin();

                    // przeslanie wartosci id uzytkownika do innego activity bez otwierania activity
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("id", id_uzytkownik);
                    editor.putString("nickname", nickname);
                    editor.commit();

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

        zapomniane_hasło.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ZapomnianeHaslo.class));
            }
        });
    }
}
