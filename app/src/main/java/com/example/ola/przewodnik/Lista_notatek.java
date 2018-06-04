package com.example.ola.przewodnik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Lista_notatek extends AppCompatActivity {


    ListView lv;
    FirebaseListAdapter adapterlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notatek);

        lv = (ListView) findViewById(R.id.listaWiadomosci);

        Query query = FirebaseDatabase.getInstance().getReference().child("notatki");
        FirebaseListOptions<Notka> options = new FirebaseListOptions.Builder<Notka>()
                .setLayout(R.layout.notka)
                .setQuery(query, Notka.class)
                .build();

        adapterlist = new FirebaseListAdapter(options){
            @Override
            protected void populateView(View v, Object model, int position) {
                //TextView userID = v.findViewById(R.id.userID);
                TextView tytul = v.findViewById(R.id.tytul);
                TextView tresc = v.findViewById(R.id.tresc);

                Notka std = (Notka) model;

                // userID.setText(std.getUserID().toString());
                tytul.setText(std.getTytul().toString());
                tresc.setText(std.getTresc().toString());

            }
        };

        lv.setAdapter(adapterlist);
    }

    @Override
    protected void onStart(){
        super.onStart();
        adapterlist.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapterlist.stopListening();
    }

    public void addNote (View view) {
        Intent intencja = new Intent(this, Dodaj_notatke.class);
        startActivity(intencja);
    }
}
