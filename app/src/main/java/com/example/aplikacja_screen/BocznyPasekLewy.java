package com.example.aplikacja_screen;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.m.aplikacja_screen.R;

public class BocznyPasekLewy extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView zalogowany_jako;
    //nav_header_main_2 --żeby się dostać do textView -> zalogowany jako
    //bo ten przycisk nie jest w zwykłym Activity
    NavigationView navigationView;
    View headerView;
    Button zestawy, kategorie, statystyki;
    TextView nazwa_uzytkownika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //NAVIGATION VIEW
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);

        //ZALOGOWANY JAKO... --POBIERANIE WARTOSCI
        zalogowany_jako = (TextView) headerView.findViewById(R.id.zalogowany_jako);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String nickname = prefs.getString("nickname", "0");

        //DODATKOWE OPCJE DO WYSWIETLANIA
        SpannableString sp = new SpannableString(nickname);
        //sp.setSpan(new UnderlineSpan(),0,sp.length(),0);
        sp.setSpan(new StyleSpan(Typeface.BOLD), 0, sp.length(), 0);
        sp.setSpan(new StyleSpan(Typeface.ITALIC), 0, sp.length(), 0);
        sp.setSpan(new RelativeSizeSpan(1.5f), 0, sp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        zalogowany_jako.append(sp);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //ekran główny
        zestawy = (Button)findViewById(R.id.button21);
        kategorie = (Button)findViewById(R.id.button22);
        statystyki = (Button)findViewById(R.id.button23);
        nazwa_uzytkownika = (TextView)findViewById(R.id.textView27);
        nazwa_uzytkownika.setText("Witaj, "+nickname);

        kategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BocznyPasekLewy.this,WyborKategorii.class));
            }
        });

        zestawy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BocznyPasekLewy.this,MojeZestawy.class));
            }
        });

        statystyki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BocznyPasekLewy.this,Statystyka.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_zestaw) {
            // Handle the camera action
            startActivity(new Intent(BocznyPasekLewy.this, MojeZestawy.class));       //dodano
        } else if (id == R.id.nav_kategorie) {
            startActivity(new Intent(BocznyPasekLewy.this, WyborKategorii.class));
        } else if (id == R.id.nav_wyloguj) {
            startActivity(new Intent(BocznyPasekLewy.this, MainActivity.class));
            //wylogowanie z konta
        } else if (id == R.id.nav_profil) {
            startActivity(new Intent(BocznyPasekLewy.this, MojProfil.class));
        } else if (id == R.id.nav_stat) {
            startActivity(new Intent(BocznyPasekLewy.this, Statystyka.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
