package com.example.ola.przewodnik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Ekran_glowny_przewodnik extends AppCompatActivity {
    //tworzenie listy wpisow
    private ArrayList<String> target;
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran_glowny_przewodnik);





    }


    public void saveButtonUser(View view) {
        Intent intencja = new Intent(this, Dodaj_uczestnika.class);
        startActivity(intencja);
    }
    public void showUserList(View view){
        Intent intencja = new Intent(this, Lista_Uczestnikow.class);
        startActivity(intencja);
    }
    public void showWycieczkaList(View view){
        Intent intencja = new Intent(this, Lista_Wycieczek.class);
        startActivity(intencja);
    }

    public void runMap (View view) {
        Intent intencja = new Intent(this, MapsActivity.class);
        startActivity(intencja);
    }

    public void addNote (View view) {
        Intent intencja = new Intent(this, Dodaj_notatke.class);
        startActivity(intencja);
    }
    public void showAparat(View view){
        Intent intencja = new Intent(this, Zrob_Zdjecie.class);
        startActivity(intencja);
    }
}
