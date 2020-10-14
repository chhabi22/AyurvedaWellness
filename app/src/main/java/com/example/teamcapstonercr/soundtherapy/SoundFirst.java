package com.example.teamcapstonercr.soundtherapy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.teamcapstonercr.HomeScreen;
import com.example.teamcapstonercr.R;

public class SoundFirst extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_first);
    }
    public void finish(View view) {

        finish();
    }
    public void clickable(View view){
        Intent imgIntent = new Intent(SoundFirst.this, HomeScreen.class);
        startActivity(imgIntent);
    }
}
