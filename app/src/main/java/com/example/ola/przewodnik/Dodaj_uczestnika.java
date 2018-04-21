package com.example.ola.przewodnik;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class Dodaj_uczestnika extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mDataBase;
    private ListView listView;
    //Array List
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    ProgressBar progressBar;
    EditText editTextEmailUser, editTextPasswordUser, editTextImieUser, editTextNazwiskoUser;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_uczestnika);

        editTextEmailUser = findViewById(R.id.editTextEmailUser);
        editTextPasswordUser = findViewById(R.id.editTextPasswordUser);
        editTextImieUser = findViewById(R.id.editTextImieUser);
        editTextNazwiskoUser = findViewById(R.id.editTextNazwiskoUser);
        progressBar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.buttonRegisterUser).setOnClickListener(this);

        mDataBase = FirebaseDatabase.getInstance().getReference();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        mDataBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String string = dataSnapshot.getValue(String.class);
                arrayList.add(string);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void saveUser(){
        final String email = editTextEmailUser.getText().toString().trim();
        String password = editTextPasswordUser.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmailUser.setError("Email is required");
            editTextEmailUser.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmailUser.setError("Please enter valid email");
            editTextEmailUser.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPasswordUser.setError("Password is required");
            editTextPasswordUser.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPasswordUser.setError("Min znakow 6");
            editTextPasswordUser.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    //Toast.makeText(getApplicationContext(), "User Registered Successfull", Toast.LENGTH_SHORT).show();
                    mDataBase.push().setValue(editTextEmailUser.getText().toString() +" "+ editTextImieUser.getText().toString()+" "+ editTextNazwiskoUser.getText().toString());
                    Intent intencja = new Intent(Dodaj_uczestnika.this, Ekran_glowny_przewodnik.class);
                    intencja.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intencja);
                }else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "Email zajęty", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonRegisterUser:
                saveUser();
                break;

        }
    }


}
