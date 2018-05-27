package com.example.ola.przewodnik;

public class wycieczka {
    private String nazwa;
    private String dataR;
    private String dataZ;
    private String grupa;
    private String przewodnikID;
    private String opis;

    public wycieczka() {
    }

    public wycieczka(String nazwa, String dataR, String dataZ, String grupa, String przewodnikID, String opis) {
        this.nazwa = nazwa;
        this.dataR = dataR;
        this.dataZ = dataZ;
        this.grupa = grupa;
        this.przewodnikID = przewodnikID;
        this.opis = opis;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getDataR() {
        return dataR;
    }

    public void setDataR(String dataR) {
        this.dataR = dataR;
    }

    public String getDataZ() {
        return dataZ;
    }

    public void setDataZ(String dataZ) {
        this.dataZ = dataZ;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public String getPrzewodnikID() {
        return przewodnikID;
    }

    public void setPrzewodnikID(String przewodnikID) {
        this.przewodnikID = przewodnikID;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
