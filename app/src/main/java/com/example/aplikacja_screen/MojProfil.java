package com.example.aplikacja_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.Database.Database;
import com.example.SHA256;
import com.example.m.aplikacja_screen.R;

import java.util.regex.Pattern;

//co tu ma byc
//zmienianie loginu,hasla
//wybor czy chcemy powiadomieniatry
public class MojProfil extends AppCompatActivity {
    private static final String TAG = "MojProfil";
    TabHost tabHost;
    ToggleButton tb;
    EditText login, stare_haslo, nowe_haslo, nowe_haslo2, haslo;
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
        haslo = (EditText) findViewById(R.id.textViewHaslo);
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
                String nickname = prefs.getString("nickname", "0");
                String nowy_login = login.getText().toString();
                String pass = haslo.getText().toString();

                if (nowy_login.equals("") || pass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Zostawiono puste pola!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (nickname.equals(nowy_login)) {
                    Toast.makeText(getApplicationContext(), "Wprowadzono istniejacy login", Toast.LENGTH_LONG).show();
                    return;
                }

                //sprawdzenie czy stare haslo zostalo poprawnie wprowadzone
                Cursor c = db.getUser(Integer.parseInt(userID));

                SHA256 sha256 = new SHA256(pass);
                pass = sha256.getEncoded();

                while (c.moveToNext()) {
                    String stareHasloZBazy = c.getString(2);
                    if (!pass.equals(stareHasloZBazy)) {
                        Toast.makeText(getApplicationContext(), "Zle hasło!", Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            int count = db.updateUserLogin(Integer.parseInt(userID), nowy_login);
                            Toast.makeText(getApplicationContext(), "Poprawnie zmieniono login", Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("nickname", nowy_login);
                            editor.commit();
                            login.setText(" ");
                            startActivity(new Intent(MojProfil.this, BocznyPasekLewy.class));
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Podany login jest zajety!", Toast.LENGTH_LONG).show();
                        }
                    }
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

                String stareHasloOdUzytkownika = stare_haslo.getText().toString();
                SHA256 sha256 = new SHA256(stareHasloOdUzytkownika);
                stareHasloOdUzytkownika = sha256.getEncoded();

                while (c.moveToNext()) {
                    String stareHasloZBazy = c.getString(2);
                    if (pass.equals("") || pass2.equals("")) {
                        Toast.makeText(getApplicationContext(), "Zostawiono puste pola!", Toast.LENGTH_LONG).show();
                    } else if (!pass.equals(pass2)) {
                        Toast.makeText(getApplicationContext(), "Podane hasła nie są identyczne!", Toast.LENGTH_LONG).show();
                    } else if (!isPasswordValid(pass)) {
                        Toast.makeText(getApplicationContext(), "Hasło musi składać się z sześciu znaków, zawierać cyfry, małe/duże litery oraz znaki specjalne.", Toast.LENGTH_LONG).show();
                    } else if (!stareHasloOdUzytkownika.equals(stareHasloZBazy)) {
                        Toast.makeText(getApplicationContext(), "Zle hasło!", Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            //zmiana hasla w bazie
                            sha256 = new SHA256(pass);
                            pass = sha256.getEncoded();
                            int count = db.updateUserPassword(Integer.parseInt(userID), pass);

                            if (count == 1) {
                                Toast.makeText(getApplicationContext(), "Poprawnie zmieniono haslo", Toast.LENGTH_SHORT).show();
                                nowe_haslo2.setText("");
                                nowe_haslo.setText("");
                                stare_haslo.setText("");

                                //wylogowanie z aplikacji po zmianie hasla
                                startActivity(new Intent(MojProfil.this, MainActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Wystąpił błąd", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {

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
