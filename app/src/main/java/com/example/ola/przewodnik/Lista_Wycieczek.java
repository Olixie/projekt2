package com.example.ola.przewodnik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Lista_Wycieczek extends AppCompatActivity {


    ListView lv;
    FirebaseListAdapter adapterlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__wycieczek);

        lv = (ListView) findViewById(R.id.listView);
        Query query = FirebaseDatabase.getInstance().getReference().child("wycieczka");
        FirebaseListOptions<wycieczka> options = new FirebaseListOptions.Builder<wycieczka>()
                .setLayout(R.layout.wycieczka)
                .setQuery(query, wycieczka.class)
                .build();
        adapterlist = new FirebaseListAdapter(options){
            @Override
            protected void populateView(View v, Object model, int position) {
                //TextView userID = v.findViewById(R.id.userID);
                TextView nazwa = v.findViewById(R.id.nazwa);
                TextView dataR = v.findViewById(R.id.dataR);
                TextView dataZ = v.findViewById(R.id.dataZ);
                TextView grupa = v.findViewById(R.id.grupa);
                TextView opis = v.findViewById(R.id.opis);

                wycieczka std = (wycieczka) model;

                // userID.setText(std.getUserID().toString());
                nazwa.setText("Nazwa: " + std.getNazwa().toString());
                dataR.setText("Data rozpoczęcia wycieczki: "+ std.getDataR().toString());
                dataZ.setText("Data zakończenia wycieczki: "+ std.getDataZ().toString());
                grupa.setText("Numer Grupy: "+ std.getGrupa().toString());
                opis.setText("Opis: "+ std.getOpis().toString());



            }
        };
        //ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        lv.setAdapter(adapterlist);

        final Intent intencja = new Intent(this, Plan_Wycieczki.class);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Kliknieto w osobe!", Toast.LENGTH_LONG).show();
                startActivity(intencja);
            }
        });
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
}
