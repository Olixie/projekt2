package com.example.ola.przewodnik;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Plan_Wycieczki extends AppCompatActivity {
    ListView lv;
    FirebaseListAdapter adapterlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan__wycieczki);

        lv = (ListView) findViewById(R.id.listView);
        Query query = FirebaseDatabase.getInstance().getReference().child("plan_wycieczki").orderByChild("grupa").equalTo("4");
        //Query query = FirebaseDatabase.getInstance().getReference().child("plan_wycieczki");
        FirebaseListOptions<Plan> options = new FirebaseListOptions.Builder<Plan>()
                .setLayout(R.layout.plan_wycieczki)
                .setQuery(query, Plan.class)
                .build();
        adapterlist = new FirebaseListAdapter(options){
            @Override
            protected void populateView(View v, Object model, int position) {
                //TextView userID = v.findViewById(R.id.userID);
                TextView nazwa = v.findViewById(R.id.nazwa);
                TextView grupa = v.findViewById(R.id.grupa);
                TextView dzien1 = v.findViewById(R.id.dzien1);
                TextView time1 = v.findViewById(R.id.time1);
                TextView pkt1 = v.findViewById(R.id.pkt1);
                TextView opispkt1 = v.findViewById(R.id.opispkt1);
                TextView time2 = v.findViewById(R.id.time2);
                TextView pkt2 = v.findViewById(R.id.pkt2);
                TextView opispkt2 = v.findViewById(R.id.opispkt2);
                TextView time3 = v.findViewById(R.id.time3);
                TextView pkt3 = v.findViewById(R.id.pkt3);
                TextView opispkt3 = v.findViewById(R.id.opispkt3);
                TextView dzien2 = v.findViewById(R.id.dzien2);
                TextView d2time1 = v.findViewById(R.id.d2time1);
                TextView d2pkt1 = v.findViewById(R.id.d2pkt1);
                TextView d2opispkt1 = v.findViewById(R.id.d2opispkt1);
                TextView d2time2 = v.findViewById(R.id.d2time2);
                TextView d2pkt2 = v.findViewById(R.id.d2pkt2);
                TextView d2opispkt2 = v.findViewById(R.id.d2opispkt2);
                TextView d2time3 = v.findViewById(R.id.d2time3);
                TextView d2pkt3 = v.findViewById(R.id.d2pkt3);
                TextView d2opispkt3 = v.findViewById(R.id.d2opispkt3);


                Plan std = (Plan) model;

                // userID.setText(std.getUserID().toString());
                nazwa.setText("Nazwa: " + std.getNazwa().toString());
                grupa.setText("Grupa: "+ std.getGrupa().toString());
                dzien1.setText("Dzień 1: "+ std.getDzien1().toString());
                time1.setText("Godzina: "+ std.getTime1().toString());
                pkt1.setText("Punkt nr. 1 Wycieczki: "+std.getPkt1().toString());
                opispkt1.setText("Opis: "+std.getOpispkt1().toString());
                time2.setText("Godzina: "+ std.getTime2().toString());
                pkt2.setText("Punkt nr. 2 Wycieczki: "+std.getPkt2().toString());
                opispkt2.setText("Opis: "+std.getOpispkt2().toString());
                time3.setText("Godzina: "+ std.getTime3().toString());
                pkt3.setText("Punkt nr. 3 Wycieczki: "+std.getPkt3().toString());
                opispkt3.setText("Opis: "+std.getOpispkt3().toString());
                dzien2.setText("Dzień 2: "+ std.getDzien2().toString());
                d2time1.setText("Godzina: "+ std.getD2time1().toString());
                d2pkt1.setText("Punkt nr. 1 Wycieczki: "+std.getD2pkt1().toString());
                d2opispkt1.setText("Opis: "+std.getD2opispkt1().toString());
                d2time2.setText("Godzina: "+ std.getD2time2().toString());
                d2pkt2.setText("Punkt nr. 2 Wycieczki: "+std.getD2pkt2().toString());
                d2opispkt2.setText("Opis: "+std.getD2opispkt2().toString());
                d2time3.setText("Godzina: "+ std.getD2time3().toString());
                d2pkt3.setText("Punkt nr. 3 Wycieczki: "+std.getD2pkt3().toString());
                d2opispkt3.setText("Opis: "+std.getD2opispkt3().toString());





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