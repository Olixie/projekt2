package com.example.ola.przewodnik;

public class Wiadomosc {

    private String odbiorca;
    private String tytul;
    private String tresc;

    public Wiadomosc() {

    }

    public Wiadomosc(String odbiorca, String tytul, String tresc) {
        this.odbiorca = odbiorca;
        this.tytul = tytul;
        this.tresc = tresc;
    }

    public String getOdbiorca() {
        return odbiorca;
    }

    public String getTytulWiad() {
        return tytul;
    }

    public String getTrescWiad() {
        return tresc;
    }

    public void setOdbiorca(String odbiorca) {
        this.odbiorca = odbiorca;
    }

    public void setTytulWiad(String tytul) {
        this.tytul = tytul;
    }

    public void setTrescWiad(String tresc) {
        this.tresc = tresc;
    }
}
