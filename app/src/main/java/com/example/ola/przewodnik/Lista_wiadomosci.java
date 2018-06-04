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

public class Lista_wiadomosci extends AppCompatActivity {

    ListView lv;
    FirebaseListAdapter adapterlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_wiadomosci);

        lv = (ListView) findViewById(R.id.listaWiadomosci);

        Query query = FirebaseDatabase.getInstance().getReference().child("wiadomosci");
        FirebaseListOptions<Wiadomosc> options = new FirebaseListOptions.Builder<Wiadomosc>()
                .setLayout(R.layout.wiadomosc)
                .setQuery(query, Wiadomosc.class)
                .build();

        adapterlist = new FirebaseListAdapter(options){
            @Override
            protected void populateView(View v, Object model, int position) {
                //TextView userID = v.findViewById(R.id.userID);
                TextView nadawca = v.findViewById(R.id.odkogo);
                TextView tytul = v.findViewById(R.id.tytulW);
                TextView tresc = v.findViewById(R.id.trescW);

                Wiadomosc std = (Wiadomosc) model;

                // userID.setText(std.getUserID().toString());
                nadawca.setText(std.getOdbiorca().toString());
                tytul.setText( std.getTytulWiad().toString());
                tresc.setText(std.getTrescWiad().toString());

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

    public void addMessage (View view) {
        Intent intencja = new Intent(this, Dodaj_wiadomosc.class);
        startActivity(intencja);
    }
}
