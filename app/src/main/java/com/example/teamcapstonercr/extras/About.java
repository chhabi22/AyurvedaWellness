package com.example.teamcapstonercr.extras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.example.teamcapstonercr.HomeScreen;
import com.example.teamcapstonercr.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    public void finish(View view) {

        finish();
    }
    public void clickable(View view){
        Intent imgIntent = new Intent(About.this, HomeScreen.class);
        startActivity(imgIntent);
    }

}
