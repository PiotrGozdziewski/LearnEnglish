package com.example.aplikacja_screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.ToggleButton;

import com.example.m.aplikacja_screen.R;

//co tu ma byc
//zmienianie loginu,hasla
//wybor czy chcemy powiadomieniatry
public class Moj_profil extends AppCompatActivity {

    TabHost tabHost;
    ToggleButton tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moj_profil);
        tb=(ToggleButton)findViewById(R.id.toggleButton);

        tabHost=(TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec s1=tabHost.newTabSpec("Login");
        s1.setContent(R.id.tab1);
        s1.setIndicator("Ustawianie loginu");

        tabHost.addTab(s1);

        s1=tabHost.newTabSpec("Hasło");
        s1.setContent(R.id.tab2);
        s1.setIndicator("Ustawianie hasła");
        tabHost.addTab(s1);

        s1=tabHost.newTabSpec("Inne");
        s1.setContent(R.id.tab3);
        s1.setIndicator("Inne");
        tabHost.addTab(s1);
    }

    public void onToggleClicked(View v){
        boolean on = ((ToggleButton) v).isChecked();

        if (on) {
        //wlacz przypomnienie
        } else {
        //wylacz przypomnienie
        }
    }
}
