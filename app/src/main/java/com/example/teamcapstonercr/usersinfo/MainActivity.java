package com.example.teamcapstonercr.usersinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.teamcapstonercr.HomeScreen;
import com.example.teamcapstonercr.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }


    public void onLogClick(View v){
        Intent myIntent2 = new Intent(getBaseContext(), Register.class);
        startActivity(myIntent2);
    }
}
