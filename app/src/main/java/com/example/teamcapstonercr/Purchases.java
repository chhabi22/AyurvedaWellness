package com.example.teamcapstonercr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.teamcapstonercr.extras.About;
import com.example.teamcapstonercr.soundtherapy.SoundTypes;
import com.example.teamcapstonercr.yog.Typeunique;

public class Purchases extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);
    }
    public void finish(View view) {

        finish();
    }
    public void clickable(View view){
        Intent imgIntent = new Intent(Purchases.this, HomeScreen.class);
        startActivity(imgIntent);
    }

    public void gotoyoga(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Action Required")
                .setPositiveButton("Purchase", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent imgIntent = new Intent(Purchases.this, Typeunique.class);
                        startActivity(imgIntent);
                    }
                }).setNegativeButton("Cancel", null)
                .show();
    }

    public void gotosound(View view) {

        new AlertDialog.Builder(this)
                .setTitle("Action Required")
                .setPositiveButton("Purchase", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent imgIntent = new Intent(Purchases.this, SoundTypes.class);
                        startActivity(imgIntent);
                    }
                }).setNegativeButton("Cancel", null)
                .show();

    }
}
