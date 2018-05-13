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
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.Database.Database;
import com.example.m.aplikacja_screen.R;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.regex.Pattern;

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
        login = (EditText) findViewById(R.id.textView15);
        zmien_login = (Button) findViewById(R.id.button8);
        stare_haslo = (EditText) findViewById(R.id.textView16);
        nowe_haslo = (EditText) findViewById(R.id.textView17);
        nowe_haslo2 = (EditText) findViewById(R.id.textView18);
        zmien_haslo = (Button) findViewById(R.id.button9);

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
                String nowy_login = login.getText().toString();
                if (!nowy_login.equals("")) {
                    db.updateUserLogin(Integer.parseInt(userID), nowy_login);
                    Toast.makeText(getApplicationContext(), "Poprawnie zmieniono login", Toast.LENGTH_SHORT).show();
                    login.setText(" ");
                    startActivity(new Intent(MojProfil.this, BocznyPasekLewy.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Pole nie moze byc puste", Toast.LENGTH_SHORT).show();
                }
            }
        });

        zmien_haslo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pobranie id aktualnego uzytkownika
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String userID = prefs.getString("id", "0");
                //sprawdzenie czy stare haslo zostalo poprawnie wprowadzone
                Cursor c = db.getUser(Integer.parseInt(userID));

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
                            MessageDigest digest = MessageDigest.getInstance("SHA-256");
                            byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
                            String encodedPass = Base64.encodeToString(hash, Base64.DEFAULT);
                            //zmiana hasla w bazie
                            int count = db.updateUserPassword(Integer.parseInt(userID), encodedPass);
                            Toast.makeText(getApplicationContext(), "Poprawnie zmieniono haslo", Toast.LENGTH_SHORT).show();
                            nowe_haslo2.setText("");
                            nowe_haslo.setText("");
                            stare_haslo.setText("");
                            //wylogowanie z aplikacji po zmianie hasla
                            startActivity(new Intent(MojProfil.this, MainActivity.class));

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

    public void onToggleClicked(View v) {
        boolean on = ((ToggleButton) v).isChecked();

        if (on) {
            //wlacz przypomnienie
        } else {
            //wylacz przypomnienie
        }
    }
}
