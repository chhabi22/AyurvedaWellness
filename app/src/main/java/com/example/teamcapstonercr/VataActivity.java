package com.example.teamcapstonercr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.teamcapstonercr.soundtherapy.SoundTypes;

import java.util.ArrayList;
public class VataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vata);

        final ListView list = findViewById(R.id.layout);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Normal");
        arrayList.add("Relax");
        arrayList.add("Nature");
        arrayList.add("Calm");
        arrayList.add("Meditate");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                if (position == 0) {
                    Intent intent = new Intent(VataActivity.this, normalActivity.class);
                    startActivity(intent);
                }
                else if (position == 1) {
                    Intent intent = new Intent(VataActivity.this, soothingActivity.class);
                    startActivity(intent);
                }
                else if (position == 2) {
                    Intent intent = new Intent(VataActivity.this, calmActivity.class);
                    startActivity(intent);
                }else if (position == 3) {
                    Intent intent = new Intent(VataActivity.this, mediActivity.class);
                    startActivity(intent);
                }else if (position == 4) {
                    Intent intent = new Intent(VataActivity.this, natureActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void finish(View view) {
        finish();
    }
    public void clickable(View v){
        Intent in = new Intent(VataActivity.this, SoundTypes.class);
        startActivity(in);
    }
}
