package com.example.ola.przewodnik;

public class Notka {

    String tytul;
    String tresc;
  //  String id;

    public Notka(){

    }

    public Notka(String tytul, String tresc) {
        this.tytul = tytul;
        this.tresc = tresc;
      //  this.id = id;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }
}
