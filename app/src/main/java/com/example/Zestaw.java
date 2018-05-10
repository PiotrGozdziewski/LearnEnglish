package com.example;

import java.io.Serializable;

public class Zestaw implements Serializable{

    private int id;
    private int id_uzytkownika;
    private String nazwa;
    private String opis;

    public Zestaw(int id, int id_uzytkownika,String nazwa) {
        this.id=id;
        this.nazwa = nazwa;
        this.id_uzytkownika=id_uzytkownika;
    }
    public String getNazwa() {
        return nazwa;
    }

    public int getIdUzytkownika() { return id_uzytkownika; }

    public int getId() { return id; }

}
