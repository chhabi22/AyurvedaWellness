package com.example.teamcapstonercr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.print.PrinterId;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class PhotoImage extends AppCompatActivity implements PhotoAdaptor.onItemClickListner {

   private RecyclerView mRecylerView;
   private PhotoAdaptor mAdaptor;
   private ProgressBar dProgressbar;

   private FirebaseStorage mStorage;
   private DatabaseReference mDatabaseRef;
   private ValueEventListener mdblistener;
   private List<Upload> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_image);

        dProgressbar = findViewById(R.id.dprogressbar);

        mRecylerView = findViewById(R.id.recyler_view);
        mRecylerView.setHasFixedSize(true);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mUploads = new ArrayList<>();

        mAdaptor = new PhotoAdaptor(PhotoImage.this,mUploads);
        mRecylerView.setAdapter(mAdaptor);
        mAdaptor.setOnItemClickListner(PhotoImage.this);

        mStorage = FirebaseStorage.getInstance();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads");

        mdblistener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }
                mAdaptor.notifyDataSetChanged();

                dProgressbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PhotoImage.this, "Error!" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                dProgressbar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Normal click at position: " + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onWhatEverClick(int position) {
        Toast.makeText(this, "Whatever click at position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        Toast.makeText(this, "Delete click at psotion: " + position, Toast.LENGTH_SHORT).show();
        Upload selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();
        StorageReference photoRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(PhotoImage.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mdblistener);
    }

    public void finish(View view) {

        finish();
    }

    public void home(View view) {
        Intent intent = new Intent(this,HomeScreen.class);
        startActivity(intent);
    }
}
