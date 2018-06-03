package com.example.ola.przewodnik;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class GetLocation extends AppCompatActivity {

    private FusedLocationProviderClient client;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);

        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(GetLocation.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

            return;
        }

        client.getLastLocation().addOnSuccessListener(GetLocation.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null){

                            TextView textView = findViewById(R.id.labelLocation);
                            TextView textViewlo = findViewById(R.id.lon);
                            TextView textViewla = findViewById(R.id.lat);
                            textView.setText(location.toString());

                            textViewlo.setText(Double.toString(location.getLongitude()));
                            textViewla.setText(Double.toString(location.getLatitude()));

                            showUser();
                        }
                    }
                }

        );
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    public void showUser(){
        Intent intencja = new Intent(this, MapsActivity.class);
        TextView textViewLo = (TextView) findViewById(R.id.lon);
        //intencja.putExtra("Longitude", "14.5937644");
        intencja.putExtra("Longitude", textViewLo.getText().toString());
        TextView textViewLa = (TextView) findViewById(R.id.lat);
        //intencja.putExtra("Longitude", "53.4682782");
        intencja.putExtra("Latitude", textViewLa.getText().toString());
        startActivity(intencja);
    }
}
