package com.example.aplikacja_screen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Database.Database;
import com.example.Database.SetsContract;
import com.example.Zestaw;
import com.example.m.aplikacja_screen.R;

import java.util.List;

public class ZestawAdapter extends RecyclerView.Adapter<ZestawAdapter.ViewHolder> {

    private List<Zestaw> list;
    Context context;
    Database db;

    public ZestawAdapter(List<Zestaw> list, Context c) {
        this.list = list;
        this.context = c;
    }

    public ZestawAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_zestawy, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Zestaw z = list.get(position);
        holder.nazwa.setText(z.getNazwa()); //nazwa zestawu

        db= new Database(context.getContentResolver());
        //pobranie aktualnej nazwy zestawu
        final String nazwa_zestawu= z.getNazwa();

        //pobranie ilości fiszek w zestawie
        Cursor c=db.getFlashcards();
        Cursor c1=db.getSets();
        int numer_zestawu=0;
        int ilość_fiszek=0;
        while(c1.moveToNext())
        {
            if(nazwa_zestawu.equals(c1.getString(2))) {
                numer_zestawu = c1.getInt(0);
                while (c.moveToNext()) {
                    if (numer_zestawu == (c.getInt(1))) {
                        ilość_fiszek++;
                    }
                }
                holder.ilośćFiszek.setText("Ilość fiszek: "+String.valueOf(ilość_fiszek));
            }
        }

        holder.opcje.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                PopupMenu p = new PopupMenu(context, view);
                p.inflate(R.menu.options_menu);
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.Dodaj:
                                Cursor cursor=db.getSets();
                                while(cursor.moveToNext()){
                                    String nazwa=cursor.getString(2);
                                    if(nazwa_zestawu.equals(nazwa))
                                    {
                                        int id_zestawu=cursor.getInt(0); //id aktualnego zestawu
                                        //przeslanie id_zestawu do activity DodanieFiszki
                                        SharedPreferences p= PreferenceManager.getDefaultSharedPreferences(context);
                                        SharedPreferences.Editor e=p.edit();
                                        e.putInt("ID",id_zestawu);
                                        e.commit();
                                    }
                                }
                                ZestawAdapter.this.context.startActivity(new Intent(ZestawAdapter.this.context, DodanieFiszki.class));
                                break;
                            case R.id.Edytuj:
                                Cursor cursor3=db.getSets();
                                while(cursor3.moveToNext()){
                                    String nazwa=cursor3.getString(2);
                                    if(nazwa_zestawu.equals(nazwa))
                                    {
                                        int id_zestawu=cursor3.getInt(0); //id aktualnego zestawu
                                        //przeslanie id_zestawu do activity EdytowanieZestawu
                                        SharedPreferences p= PreferenceManager.getDefaultSharedPreferences(context);
                                        SharedPreferences.Editor e=p.edit();
                                        e.putInt("ID",id_zestawu);
                                        e.commit();
                                    }
                                }
                                ZestawAdapter.this.context.startActivity((new Intent(ZestawAdapter.this.context, EdytowanieZestawu.class)));
                                break;
                            case R.id.Usuń:
                                Cursor cursor4=db.getSets();
                                while(cursor4.moveToNext()){
                                    String nazwa=cursor4.getString(2);
                                    if(nazwa_zestawu.equals(nazwa))
                                    {
                                        int id_zestawu=cursor4.getInt(0); //id aktualnego zestawu
                                        //przeslanie id_zestawu do activity EdytowanieZestawu
                                        SharedPreferences p= PreferenceManager.getDefaultSharedPreferences(context);
                                        SharedPreferences.Editor e=p.edit();
                                        e.putInt("ID",id_zestawu);
                                        e.commit();
                                    }
                                }
                                ZestawAdapter.this.context.startActivity((new Intent(ZestawAdapter.this.context, UsuwanieFiszek.class)));
                                break;
                            case R.id.Wyświetl:
                                Cursor cursor2=db.getSets();
                                while(cursor2.moveToNext()){
                                    String nazwa=cursor2.getString(2);
                                    if(nazwa_zestawu.equals(nazwa))
                                    {
                                        int id_zestawu=cursor2.getInt(0); //id aktualnego zestawu
                                        //przeslanie id_zestawu do activity NaukaFiszki
                                        SharedPreferences p= PreferenceManager.getDefaultSharedPreferences(context);
                                        SharedPreferences.Editor e=p.edit();
                                        e.putInt("ID",id_zestawu);
                                        e.commit();
                                    }
                                }
                                ZestawAdapter.this.context.startActivity(new Intent(ZestawAdapter.this.context, NaukaFiszki.class));
                                break;
                            case R.id.Usuń_zestaw:
                                //kod od usuniecia zestawu
                                Cursor cursor1=db.getSets();
                                while(cursor1.moveToNext()){
                                    String nazwa=cursor1.getString(2);
                                    if(nazwa_zestawu.equals(nazwa))
                                    {
                                       int nr=cursor1.getInt(0);
                                       db.deleteFromSets(nr);
                                        Intent intent=new Intent(context,MojeZestawy.class);
                                        context.startActivity(intent);
                                    }
                                }
                                break;
                        }
                        return false;
                    }
                });
                p.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nazwa;
        public TextView ilośćFiszek;
        public TextView opcje;

        public ViewHolder(View itemView) {
            super(itemView);
            nazwa = (TextView) itemView.findViewById(R.id.textViewHead);
            ilośćFiszek = (TextView) itemView.findViewById(R.id.textViewDesc);
            opcje = (TextView) itemView.findViewById(R.id.textViewOptions);
        }
    }
}
