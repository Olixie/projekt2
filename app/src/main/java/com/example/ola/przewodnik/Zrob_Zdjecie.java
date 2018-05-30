package com.example.ola.przewodnik;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

public class Zrob_Zdjecie extends AppCompatActivity {

    private Button mUploadBtn;
    private ImageView imageView;

    private static final int CAMERA_REQUEST_CODE = 1;

    private StorageReference mStorage;

    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zrob__zdjecie);

        mStorage = FirebaseStorage.getInstance().getReference();

        mUploadBtn = (Button) findViewById(R.id.upload);
        imageView = (ImageView) findViewById(R.id.imageView2);

        mProgress = new ProgressDialog(this);

        mUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencja = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intencja, CAMERA_REQUEST_CODE);
            }
        });
    }

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
//set the progress dialog
        mProgress.setMessage("Przesyłanie zdjęcia...");
        mProgress.show();

//get the camera image
        Bundle extras = data.getExtras();
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] dataBAOS = baos.toByteArray();

//set the image into imageview
        imageView.setImageBitmap(bitmap);

        /*************** UPLOADS THE PIC TO FIREBASE***************/
        //Firebase storage folder where you want to put the images
        StorageReference storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://projekt2-aa029.appspot.com/images");
        //StorageReference storageRef = mStorage.child("Photos");
//name of the image file (add time to have different files to avoid rewrite on the same file)
        //long a = new Date().getTime();
        StorageReference imagesRef = storageRef.child("filename" + new Date().getTime());

//upload image

        UploadTask uploadTask = imagesRef.putBytes(dataBAOS);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getApplicationContext(),"Sending failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.

//handle success


                mProgress.dismiss();
            }
        });


      }
  }}



