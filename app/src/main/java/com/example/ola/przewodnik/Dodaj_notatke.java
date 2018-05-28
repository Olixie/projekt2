package com.example.ola.przewodnik;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Dodaj_notatke extends AppCompatActivity  {

    EditText title, content;
    Button buttonDOdaj;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_notatke);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        buttonDOdaj = (Button) findViewById(R.id.buttonDodaj);

        buttonDOdaj.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View view){
            addNote();
        }
        });
    }

    private void addNote(){
        String tytul = title.getText().toString().trim();
        String tresc = content.getText().toString().trim();

        if(tytul.isEmpty() == true || tresc.isEmpty() == true){
            Toast.makeText(this, "Uzupełnij dane", Toast.LENGTH_LONG).show();
        }
        else{

            String id = databaseReference.push().getKey();
            Notka notka = new Notka(tytul,tresc);

            databaseReference.child("notatki").child(id).setValue(notka);

            Toast.makeText(this, "Dodano notatke", Toast.LENGTH_LONG).show();



        }
    }
}

