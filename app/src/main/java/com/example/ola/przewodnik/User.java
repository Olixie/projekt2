package com.example.ola.przewodnik;

public class User {
    // private String userID;
    private String imie;
    private String nazwisko;
    private String email;
    private String phone;
    private String grupa;

    public User(){
    }

    public User( String imie, String nazwisko, String email, String phone, String grupa){
        //this.userID = userID;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.phone = phone;
        this.grupa = grupa;
    }

    // public String getUserID() {
    //    return userID;
    // }

    //public void setUserID(String userID) {
    //   this.userID = userID;
    //}

    public String getEmail() {
        return email;
    }

    public String getGrupa() {
        return grupa;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

