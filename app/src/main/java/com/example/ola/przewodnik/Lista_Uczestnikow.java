package com.example.ola.przewodnik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

public class Lista_Uczestnikow extends AppCompatActivity {



    ListView lv;
    FirebaseListAdapter adapterlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__uczestnikow);

        lv = (ListView) findViewById(R.id.listView);
        //zmiana
       // String currentuser = FirebaseAuth.getInstance().getUid();
        //Integer a = Integer.valueOf(FirebaseDatabase.getInstance().getReference().child("przewodnik").child("grupa"));
        //Query query = FirebaseDatabase.getInstance().getReference().child("users").orderByChild("grupa").equalTo("2");
        Query query = FirebaseDatabase.getInstance().getReference().child("users");
        FirebaseListOptions<User> options = new FirebaseListOptions.Builder<User>()
                .setLayout(R.layout.user)
                .setQuery(query, User.class)
                .build();

        adapterlist = new FirebaseListAdapter(options){
            @Override
            protected void populateView(View v, Object model, int position) {
                //TextView userID = v.findViewById(R.id.userID);
                TextView imie = v.findViewById(R.id.imie);
                TextView nazwisko = v.findViewById(R.id.nazwisko);
                TextView email = v.findViewById(R.id.email);
                TextView phone = v.findViewById(R.id.phone);
                TextView grupa = v.findViewById(R.id.grupa);

                User std = (User) model;

                // userID.setText(std.getUserID().toString());
                imie.setText("Imie: " + std.getImie().toString());
                nazwisko.setText("Nazwisko: "+ std.getNazwisko().toString());
                email.setText("Email: "+ std.getNazwisko().toString());
                phone.setText("Telefon: "+ std.getPhone().toString());
                grupa.setText("Numer Grupy: "+std.getGrupa().toString());



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


}


