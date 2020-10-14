package com.example.teamcapstonercr.extras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teamcapstonercr.HomeScreen;
import com.example.teamcapstonercr.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Feedback extends AppCompatActivity {
    Button send;
    EditText feedback;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        send = findViewById(R.id.send);
        feedback = findViewById(R.id.feedback);
        fauth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feed = feedback.getText().toString();
               userId  = fauth.getCurrentUser().getUid();
                DocumentReference documentReference = fstore.collection("Feedback").document(userId);
                Map<String,Object> userr = new HashMap<>();
                userr.put("Feedback",feed);
                documentReference.set(userr).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Feedback.this, "Feedback Submitted", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Feedback.this, "Error!" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
    public void finish(View view) {

        finish();
    }
    public void clickable(View view){
        Intent imgIntent = new Intent(Feedback.this, HomeScreen.class);
        startActivity(imgIntent);
    }
}
