package com.example.teamcapstonercr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Date;

public class Write extends AppCompatActivity implements WriteRecyclerAdaptorr.NoteListner {
    FloatingActionButton addfab;
    RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;


    WriteRecyclerAdaptorr noterecycleradaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        firebaseAuth = FirebaseAuth.getInstance();


        addfab = findViewById(R.id.addfab);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        addfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAlertDialoge();
            }
        });

        initRecyclerView(firebaseAuth.getCurrentUser());
    }

    private void ShowAlertDialoge() {
        final EditText enoteEditText = new EditText(this);

        new AlertDialog.Builder(this)
                .setTitle("I am Grateful for")
                .setView(enoteEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Write.this, "Yes " + enoteEditText.getText(), Toast.LENGTH_SHORT).show();
                        addNote(enoteEditText.getText().toString());
                    }
                }).setNegativeButton("Cancel", null)
                .show();
    }

    private void addNote(String text) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Writec write = new Writec(text, new Timestamp(new Date()), userId);

        FirebaseFirestore.getInstance()
                .collection("Journal")
                .add(write)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Write.this, "Successfully added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Write.this, "Error!" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecyclerView(FirebaseUser user) {
        Query query = FirebaseFirestore.getInstance()
                .collection("Journal")
                .whereEqualTo("userId", user.getUid());
        FirestoreRecyclerOptions<Writec> options = new FirestoreRecyclerOptions.Builder<Writec>()
                .setQuery(query, Writec.class).build();
        noterecycleradaptor = new WriteRecyclerAdaptorr(options);
        recyclerView.setAdapter(noterecycleradaptor);

        noterecycleradaptor.startListening();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (direction == ItemTouchHelper.LEFT) {
                Toast.makeText(Write.this, "Deleting", Toast.LENGTH_SHORT).show();

                WriteRecyclerAdaptorr.NoteViewHolder noteViewHolder = (WriteRecyclerAdaptorr.NoteViewHolder) viewHolder;
                noteViewHolder.deleteitem(viewHolder.getAdapterPosition());
            }
        }


    };
    @Override
    public void handledeleteitem(DocumentSnapshot snapshot) {
        snapshot.getReference().delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Write.this, "Deleted Journal Thought", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void home(View view) {
        Intent intent = new Intent(this,HomeScreen.class);
        startActivity(intent);
    }
    public void finish(View view) {

        finish();
    }

    }



