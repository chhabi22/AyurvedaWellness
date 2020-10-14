package com.example.teamcapstonercr.journal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.teamcapstonercr.HomeScreen;
import com.example.teamcapstonercr.R;

public class JournalFirst extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_first);
    }
    public void finish(View view) {

        finish();
    }
    public void clickable(View view){
        Intent imgIntent = new Intent(JournalFirst.this, HomeScreen.class);
        startActivity(imgIntent);
    }

    public void home(View view) {
        Intent intent = new Intent(this,HomeScreen.class);
        startActivity(intent);
    }
}
