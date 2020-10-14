package com.example.teamcapstonercr.soundtherapy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.teamcapstonercr.HomeScreen;
import com.example.teamcapstonercr.KaphaActivity;
import com.example.teamcapstonercr.KaphaList;
import com.example.teamcapstonercr.PitaActivity;
import com.example.teamcapstonercr.Purchases;
import com.example.teamcapstonercr.R;
import com.example.teamcapstonercr.VataActivity;
import com.example.teamcapstonercr.extras.About;
import com.example.teamcapstonercr.yog.Typeunique;
import com.example.teamcapstonercr.yog.YogaFirst;

public class SoundTypes extends AppCompatActivity {

    Button btnone;
    Button btntwo;
    Button btnthree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_types);

        // VATA ALERT
        btnone = findViewById(R.id.btnone);
        btnone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(SoundTypes.this);
                alert.setTitle("Hey,Vata!");
                alert.setMessage("Get your personalized Playlist");
                alert.setNegativeButton("Take a sneak peak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(SoundTypes.this, VataActivity.class));
                    }
                });
                alert.setPositiveButton("I'll Purchase", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(SoundTypes.this,Purchases.class));
                    }
                });
                alert.create().show();
            }
        });

        // PITTA ALERT
        btntwo = findViewById(R.id.btntwo);
        btntwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(SoundTypes.this);
                alert.setTitle("Hey,Pitta!");
                alert.setMessage("Get your personalized Playlist");
                alert.setNegativeButton("Take a sneak peak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(SoundTypes.this, PitaActivity.class));
                    }
                });
                alert.setPositiveButton("I'll Purchase", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(SoundTypes.this, Purchases.class));
                    }
                });
                alert.create().show();
            }
        });

        // KAPHA ALERT

        btnthree = findViewById(R.id.btnthree);
        btnthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(SoundTypes.this);
                alert.setTitle("Hey,Kapha!");
                alert.setMessage("Get your personalized Playlist");
                alert.setNegativeButton("Take a sneak peak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(SoundTypes.this, KaphaActivity.class));
                    }
                });
                alert.setPositiveButton("I'll Purchase", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(SoundTypes.this, Purchases.class));
                    }
                });
                alert.create().show();
            }
        });

    }
    public void finish(View view) {

        finish();
    }
    public void clickable(View view){
        Intent imgIntent = new Intent(SoundTypes.this, HomeScreen.class);
        startActivity(imgIntent);
    }
}
