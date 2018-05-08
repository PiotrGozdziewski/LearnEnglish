package com.example.m.aplikacja_screen;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
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
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView zalogowany_jako;
    //nav_header_main_2 --żeby się dostać do textView -> zalogowany jako
    //bo ten przycisk nie jest w zwykłym Activity
    NavigationView navigationView;
    View headerView;

    //od animacji
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;
    private View mCardFrontLayout;
    private View mCardBackLayout;
    TextView animacja_przod, animacja_tyl;
    View view;

    //od wątków
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //NAVIGATION VIEW
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        headerView=navigationView.getHeaderView(0);
        //ZALOGOWANY JAKO... --POBIERANIE WARTOSCI
        zalogowany_jako=(TextView)headerView.findViewById(R.id.zalogowany_jako);
        Intent intent1=getIntent();
        Bundle b=intent1.getExtras();
        if(b!=null)
        {
            String z=getIntent().getExtras().getString("keyName","");
            //DODATKOWE OPCJE DO WYSWIETLANIA
            SpannableString sp= new SpannableString(z);
            //sp.setSpan(new UnderlineSpan(),0,sp.length(),0);
            sp.setSpan(new StyleSpan(Typeface.BOLD),0,sp.length(),0);
            sp.setSpan(new StyleSpan(Typeface.ITALIC),0,sp.length(),0);
            sp.setSpan(new RelativeSizeSpan(1.5f),0,sp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            zalogowany_jako.append(sp);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        animacja_przod=(TextView)findViewById(R.id.animacja_przod);
        animacja_tyl=(TextView)findViewById(R.id.animacja_tyl);

        findViews();
        loadAnimations();
        changeCameraDistance();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            flipCard(view); //create view
            }
        },2000);
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
        if (id ==R.id.nav_zestaw) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(),"tutaj",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Main2Activity.this, Moje_zestawy.class));       //dodano
        } else if (id == R.id.nav_kategorie) {
            startActivity(new Intent(Main2Activity.this, Wybor_kategorii.class));
        } else if (id == R.id.nav_wyloguj) {
            startActivity(new Intent(Main2Activity.this,MainActivity.class));
            //wylogowanie z konta
        }else if(id==R.id.nav_profil){
            startActivity(new Intent(Main2Activity.this,Moj_profil.class));
        }else if(id==R.id.nav_stat){
            startActivity(new Intent(Main2Activity.this,Statystyka.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
    }

    @SuppressLint("ResourceType")
    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.anim.animacja);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.anim.animacja2);
    }

    private void findViews() {
        mCardBackLayout = findViewById(R.id.card_back);
        mCardFrontLayout = findViewById(R.id.card_front);
    }

    public void flipCard(View view) {
            if (!mIsBackVisible) {
                mSetRightOut.setTarget(mCardFrontLayout);
                mSetLeftIn.setTarget(mCardBackLayout);
                mSetRightOut.start();
                mSetLeftIn.start();
                mIsBackVisible = true;
            } else {
                mSetRightOut.setTarget(mCardBackLayout);
                mSetLeftIn.setTarget(mCardFrontLayout);
                mSetRightOut.start();
                mSetLeftIn.start();
                mIsBackVisible = false;
            }
        }
    }
