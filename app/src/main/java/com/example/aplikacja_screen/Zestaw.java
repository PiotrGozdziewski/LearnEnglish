package com.example.m.aplikacja_screen;

/**
 * Created by Piotr on 09.04.2018.
 */

public class Zestaw {
    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    private String nazwa;
    private String opis;

    public Zestaw(String nazwa, String opis)
    {
        this.nazwa=nazwa;
        this.opis=opis;
    }
}
