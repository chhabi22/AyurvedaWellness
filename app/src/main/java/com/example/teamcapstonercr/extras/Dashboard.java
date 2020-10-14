package com.example.teamcapstonercr.extras;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.teamcapstonercr.HomeScreen;
import com.example.teamcapstonercr.Purchases;
import com.example.teamcapstonercr.R;
import com.example.teamcapstonercr.usersinfo.Login;
import com.example.teamcapstonercr.usersinfo.MainActivity;
import com.example.teamcapstonercr.usersinfo.Register;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Dashboard extends AppCompatActivity {

TextView username;
FirebaseAuth dAuth;
FirebaseFirestore dStore;
String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        username = findViewById(R.id.username);

        dAuth = FirebaseAuth.getInstance();
        dStore = FirebaseFirestore.getInstance();
        userid = dAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = dStore.collection("users").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
               username.setText(documentSnapshot.getString("user_fullname"));
            }
        });
    }

    public void onLoginClick(View v){
        Intent myIntent = new Intent(getBaseContext(), Login.class);
        startActivity(myIntent);
    }
    public void onRegClick(View v){
        Intent myIntent = new Intent(getBaseContext(), Purchases.class);
        startActivity(myIntent);
    }
    public void finish(View view) {

        finish();
    }
    public void clickable(View view){
        Intent imgIntent = new Intent(Dashboard.this, HomeScreen.class);
        startActivity(imgIntent);
    }

    public void onLogoutClick(View v){
        dAuth.signOut();
    }
}
