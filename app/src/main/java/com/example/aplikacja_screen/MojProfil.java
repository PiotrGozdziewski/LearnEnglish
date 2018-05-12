package com.example.aplikacja_screen;

import android.content.SharedPreferences;
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
    EditText login;
    Button zmien_login;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moj_profil);
        db = new Database(getContentResolver());
        tb = (ToggleButton) findViewById(R.id.toggleButton);
        login = (EditText)findViewById(R.id.textView15);
        zmien_login = (Button)findViewById(R.id.button8);

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
