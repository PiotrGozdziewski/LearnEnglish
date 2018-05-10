package com.example;

import java.io.Serializable;

public class Uzytkownik implements Serializable {
    private int id;
    private String login;
    private String imie;

    public Uzytkownik(int id, String login, String imie) {
        this.id = id;
        this.login = login;
        this.imie = imie;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getImie() {
        return imie;
    }
}
