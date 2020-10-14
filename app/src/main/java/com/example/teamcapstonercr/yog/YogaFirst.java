package com.example.teamcapstonercr.yog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.teamcapstonercr.HomeScreen;
import com.example.teamcapstonercr.R;
import com.example.teamcapstonercr.extras.About;

public class YogaFirst extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_first);
    }
    public void finish(View view) {

        finish();
    }
    public void clickable(View view){
        Intent imgIntent = new Intent(YogaFirst.this, HomeScreen.class);
        startActivity(imgIntent);
    }
}
