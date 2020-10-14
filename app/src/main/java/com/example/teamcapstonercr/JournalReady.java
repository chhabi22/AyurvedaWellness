package com.example.teamcapstonercr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.teamcapstonercr.journal.ViewJournal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class JournalReady extends AppCompatActivity {
    private FloatingActionButton fab_main, fab1_photo, fab2_write;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;
    TextView textview_photo, textview_write;

    Boolean isOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_ready);
        fab_main = findViewById(R.id.fab);
        fab1_photo = findViewById(R.id.fab2);
        fab2_write = findViewById(R.id.fab1);
        fab_close = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(this, R.anim.rotate_fab_clock);
        fab_anticlock = AnimationUtils.loadAnimation(this, R.anim.rotate_fab_anticlock);
        textview_photo = findViewById(R.id.text_viewphoto);
        textview_write = findViewById(R.id.write);

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            animateFab();
            }
        });

        fab1_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JournalReady.this, "Photo", Toast.LENGTH_SHORT).show();
                Intent intentphoto = new Intent(JournalReady.this, Photos.class);
                startActivity(intentphoto);

            }
        });

        fab2_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JournalReady.this, "Write", Toast.LENGTH_SHORT).show();
                Intent intentwrite = new Intent(JournalReady.this, Write.class);
                startActivity(intentwrite);
            }
        });
    }
    private void animateFab()
    {
        if (isOpen){
            fab_main.startAnimation(fab_clock);
            fab1_photo.startAnimation(fab_close);
            fab2_write.startAnimation(fab_close);
            fab1_photo.setClickable(false);
            fab2_write.setClickable(false);
            textview_write.setVisibility(View.INVISIBLE);
            textview_photo.setVisibility(View.INVISIBLE);
            isOpen=false;
        }
        else
        {
            fab_main.startAnimation(fab_anticlock);
            fab1_photo.startAnimation(fab_open);
            fab2_write.startAnimation(fab_open);
            textview_photo.setVisibility(View.VISIBLE);
            textview_write.setVisibility(View.VISIBLE);
            fab1_photo.setClickable(true);
            fab2_write.setClickable(true);
            isOpen=true;
        }

    }

    public void home(View view) {
        Intent intent = new Intent(this,HomeScreen.class);
        startActivity(intent);
    }
    public void finish(View view) {

        finish();
    }
}
