package com.example.teamcapstonercr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.teamcapstonercr.extras.Dashboard;
import com.example.teamcapstonercr.usersinfo.Login;

public class DoshaQ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosha_q);
    }

    public void onQuizClick(View v){
        Intent myIntent = new Intent(getBaseContext(), DoshaQuestion.class);
        startActivity(myIntent);
    }
    public void onReportClick(View v){
        Intent myIntent = new Intent(getBaseContext(), DoshaResult.class);
        startActivity(myIntent);
    }
    public void finish(View view) {

        finish();
    }
    public void clickable(View view){
        Intent imgIntent = new Intent(DoshaQ.this, HomeScreen.class);
        startActivity(imgIntent);
    }
}
