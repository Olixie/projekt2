package com.example.ola.przewodnik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class Dodaj_wiadomosc extends AppCompatActivity {

    EditText title_wiad, content_wiad, odbiorca_wiad;
    Button send;
    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wiadomosc);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseMessaging.getInstance().subscribeToTopic("pushNotifications");

        odbiorca_wiad = (EditText) findViewById(R.id.nadawca);
        title_wiad = (EditText) findViewById(R.id.title_wiad);
        content_wiad = (EditText) findViewById(R.id.content_wiad);
        send = (Button) findViewById(R.id.buttonSend);

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                //wywołać dodawanie wiadomości
                addMessage();
            }
        });
    }

    private void addMessage(){
        String odbiorca = odbiorca_wiad.getText().toString().trim();
        String tytul = title_wiad.getText().toString().trim();
        String tresc = content_wiad.getText().toString().trim();


        if(tytul.isEmpty() == true || tresc.isEmpty() == true || odbiorca.isEmpty() == true){
            Toast.makeText(this, "Uzupełnij dane", Toast.LENGTH_LONG).show();
        }
        else{

            String id = databaseReference.push().getKey();
            Wiadomosc wiadomosc = new Wiadomosc(odbiorca, tytul, tresc);

            databaseReference.child("wiadomosci").child(id).setValue(wiadomosc);

            Toast.makeText(this, "Dodano wiadomosc", Toast.LENGTH_LONG).show();



        }
    }

    @Override
    public void onBackPressed(){
        this.startActivity(new Intent(getApplicationContext(), Ekran_glowny_przewodnik.class));
    }
}
