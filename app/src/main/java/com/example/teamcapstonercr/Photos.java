package com.example.teamcapstonercr;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Photos extends AppCompatActivity {

     private static final int picrequest = 1;

     Button choosepic,upload;
     TextView showupload;
     EditText filename;
     ImageView imageview;
     ProgressBar progressBar;

     Uri imageuri;

     private StorageReference mStorageRef;
     private DatabaseReference mDatabaseRef;

     private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        choosepic = findViewById(R.id.btntochoose);
        upload = findViewById(R.id.btn_upload);
        filename = findViewById(R.id.edittextfilename);
        showupload = findViewById(R.id.text_view_show_uplaod);
        imageview = findViewById(R.id.image_view);
        progressBar = findViewById(R.id.pprogress_bar);

        mStorageRef = FirebaseStorage.getInstance().getReference("Uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads");

        //on choose pic select
        choosepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              choosepic();
            }
        });

        //upload pic
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress())
                {
                    Toast.makeText(Photos.this, "Upload in Progress", Toast.LENGTH_SHORT).show();
                }
                else {
                    uploadit();
                }

            }
        });

        showupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImages();
            }
        });
    }




    private void choosepic()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,picrequest);

    }
    protected void onActivityResult(int requestcode,int resultcode,Intent data) {
        super.onActivityResult(requestcode, resultcode, data);

        if (requestcode == picrequest && resultcode == RESULT_OK && data != null && data.getData() != null)
        {
            imageuri = data.getData();

            //Picasso.get().load(mImageUri).into(mImageView)
           //Picasso.with(this).load(imageuri).into(imageview);

        }
    }

    private String getFileExtension(Uri uri)
    {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadit() {
        if (imageuri != null)
        {
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(imageuri));
           mUploadTask = fileReference.putFile(imageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            },500);
                            Toast.makeText(Photos.this, "Upload Successfull", Toast.LENGTH_SHORT).show();
                           /* Upload upload = new Upload(filename.getText().toString().trim(), Objects.requireNonNull(taskSnapshot.getMetadata().getReference()).getDownloadUrl().toString());
                            String uploadID = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadID).setValue(upload);*/
                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();

                            Log.d("TAG", "onSuccess: firebase download url: " + downloadUrl.toString());
                            Upload upload = new Upload(filename.getText().toString().trim(),downloadUrl.toString());

                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Photos.this, "Error!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressBar.setProgress((int)progress);
                }
            });
        }
        else
        {
            Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show();
        }
    }
    private void openImages() {
        Intent intent = new Intent(this,PhotoImage.class);
        startActivity(intent);
    }
    public void home(View view) {
        Intent intent = new Intent(this,HomeScreen.class);
        startActivity(intent);
    }
    public void finish(View view) {

        finish();
    }
}
