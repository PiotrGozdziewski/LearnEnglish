package com.example.aplikacja_screen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m.aplikacja_screen.R;

import java.util.List;

/**
 * Created by Piotr on 09.04.2018.
 */

public class ZestawAdapter extends RecyclerView.Adapter<ZestawAdapter.ViewHolder> {

    private List<Zestaw> list;
    Context context;

    public ZestawAdapter(List<Zestaw> list,Context c) {
        this.list = list;
        this.context=c;
    }

    public ZestawAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_zestawy,parent,false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Zestaw z=list.get(position);
        holder.nazwa.setText(z.getNazwa());
        holder.opis.setText(z.getOpis());

        holder.opcje.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                PopupMenu p=new PopupMenu(context,view);
                p.inflate(R.menu.options_menu);
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch(menuItem.getItemId()){
                            case R.id.Dodaj:
                                ZestawAdapter.this.context.startActivity(new Intent(ZestawAdapter.this.context,Dodanie_fiszki.class));
                                break;
                            case R.id.Edytuj:
                                ZestawAdapter.this.context.startActivity((new Intent(ZestawAdapter.this.context,EdytowanieZestawu.class)));
                                break;
                            case R.id.Usuń:
                                ZestawAdapter.this.context.startActivity((new Intent(ZestawAdapter.this.context,UsuwanieFiszek.class)));
                                break;
                            case R.id.Wyświetl:
                                ZestawAdapter.this.context.startActivity(new Intent(ZestawAdapter.this.context,Nauka_fiszki.class));
                                break;
                            case R.id.Usuń_zestaw:
                                //kod od usuniecia zestawu
                                //pewnie bd jakas linijka z bazy danych, bo od razu caly zestaw usuwamy
                                //albo jakas fun
                                Toast.makeText(context.getApplicationContext(),"Usunieto zestaw",Toast.LENGTH_SHORT).show();
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nazwa;
        public TextView opis;
        public TextView opcje;

        public ViewHolder(View itemView){
            super(itemView);
            nazwa=(TextView)itemView.findViewById(R.id.textViewHead);
            opis=(TextView)itemView.findViewById(R.id.textViewDesc);
            opcje=(TextView)itemView.findViewById(R.id.textViewOptions);
        }
    }
}
