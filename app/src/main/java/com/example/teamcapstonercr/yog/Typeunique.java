package com.example.teamcapstonercr.yog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.teamcapstonercr.HomeScreen;
import com.example.teamcapstonercr.KaphaList;
import com.example.teamcapstonercr.PitttaList;
import com.example.teamcapstonercr.Purchases;
import com.example.teamcapstonercr.R;
import com.example.teamcapstonercr.VataList;
import com.example.teamcapstonercr.extras.About;

public class Typeunique extends AppCompatActivity {
    Button btn1;
    Button btn2;
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeunique);


        // VATA ALERT
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(Typeunique.this);
                alert.setTitle("Hey, Vata!");
                alert.setMessage("Get your personalized Yoga Poatures");
                alert.setNegativeButton("Take a sneak peak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Typeunique.this, VataList.class));
                    }
                });
                alert.setPositiveButton("I'll Purchase", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Typeunique.this, Purchases.class));
                    }
                });
                alert.create().show();
            }
        });

        // PITTA ALERT
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(Typeunique.this);
                alert.setTitle("Hey, Pitta!");
                alert.setMessage("Get your personalized Yoga Poatures");
                alert.setNegativeButton("Take a sneak peak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Typeunique.this, PitttaList.class));
                    }
                });
                alert.setPositiveButton("I'll Purchase", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Typeunique.this, Purchases.class));
                    }
                });
                alert.create().show();
            }
        });

        // KAPHA ALERT

        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(Typeunique.this);
                alert.setTitle("Hey, Kapha!");
                alert.setMessage("Get your personalized Yoga Poatures");
                alert.setNegativeButton("Take a sneak peak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Typeunique.this, KaphaList.class));
                    }
                });
                alert.setPositiveButton("I'll Purchase", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Typeunique.this,Purchases.class));
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
        Intent imgIntent = new Intent(Typeunique.this, HomeScreen.class);
        startActivity(imgIntent);
    }
}
