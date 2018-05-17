package com.example.aplikacja_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Database.Database;
import com.example.SHA256;
import com.example.m.aplikacja_screen.R;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.regex.Pattern;

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
        nowe_haslo = (EditText) findViewById(R.id.editText4);
        nowe_haslo2 = (EditText) findViewById(R.id.editText5);
        db = new Database(getContentResolver());

        ustaw_nowe_haslo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aktualizacja w bazie
                //pobranie id aktualnego uzytkownika
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                int userID = prefs.getInt("userIDhaslo", 0);
                //sprawdzenie czy stare haslo zostalo poprawnie wprowadzone
                Cursor c = db.getUser(userID);

                String pass = nowe_haslo.getText().toString();
                String pass2 = nowe_haslo2.getText().toString();

                while (c.moveToNext()) {
                    if (pass.equals("") || pass2.equals("")) {
                        Toast.makeText(getApplicationContext(), "Zostawiono puste pola!", Toast.LENGTH_LONG).show();
                    } else if (!pass.equals(pass2)) {
                        Toast.makeText(getApplicationContext(), "Podane hasła nie są identyczne!", Toast.LENGTH_LONG).show();
                    } else if (!isPasswordValid(pass)) {
                        Toast.makeText(getApplicationContext(), "Hasło musi składać się z sześciu znaków, zawierać cyfry, małe/duże litery oraz znaki specjalne.", Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            SHA256 sha256 = new SHA256(pass);
                            String encodedPass = sha256.getEncoded();
                            db.updateUserPassword(userID, encodedPass);
                            Toast.makeText(getApplicationContext(), "Poprawnie zmieniono haslo", Toast.LENGTH_SHORT).show();
                            nowe_haslo2.setText("");
                            nowe_haslo.setText("");
                            //wylogowanie z aplikacji po zmianie hasla
                            startActivity(new Intent(NoweHaslo.this, MainActivity.class));
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Podany login jest zajety!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    private boolean isPasswordValid(String password) {
        Pattern specialCharsPatern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern upperCasePattern = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatern = Pattern.compile("[a-z ]");
        Pattern digitsPattern = Pattern.compile("[0-9 ]");
        int passwordMinLength = 6;

        if (password.length() < passwordMinLength) {
            return false;
        }
        if (!specialCharsPatern.matcher(password).find()) {
            return false;
        }
        if (!upperCasePattern.matcher(password).find()) {
            return false;
        }
        if (!lowerCasePatern.matcher(password).find()) {
            return false;
        }
        if (!digitsPattern.matcher(password).find()) {
            return false;
        }

        return true;
    }
}
