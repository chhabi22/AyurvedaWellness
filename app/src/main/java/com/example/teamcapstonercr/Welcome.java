package com.example.teamcapstonercr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.teamcapstonercr.usersinfo.MainActivity;
import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;
public class Welcome extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(new Step.Builder().setTitle("Welcome")
                .setContent("Start your Self-Healing journey with us.")
                .setBackgroundColor(Color.parseColor("#df5780"))
                .setDrawable(R.drawable.flower1)
                .build());

        addFragment(new Step.Builder().setTitle("Ayurveda Quiz")
                .setContent("Find out your unique type with our Ayurveda Dosha Quiz.")
                .setBackgroundColor(Color.parseColor("#df5780"))
                .setDrawable(R.drawable.flower2)
                .build());

        addFragment(new Step.Builder().setTitle("Gratitude Journal")
                .setContent("Be grateful and attract goodness in your life.")
                .setBackgroundColor(Color.parseColor("#df5780"))
                .setDrawable(R.drawable.journaimage)
                .build());

        addFragment(new Step.Builder().setTitle("Yoga Postures")
                .setContent("Do yoga for your unique type and feel the change.")
                .setBackgroundColor(Color.parseColor("#df5780"))
                .setDrawable(R.drawable.yogimage)
//                .setSummary("Do Follow us for more such content")
                .build());

        addFragment(new Step.Builder().setTitle("Sound Therapy")
                .setContent("Listen to sounds for your own unique type and feel the change.")
                .setBackgroundColor(Color.parseColor("#df5780"))
                .setDrawable(R.drawable.soundimage)
                .build());
    }

    @Override
    public void currentFragmentPosition(int position) {

    }

    @Override
    public void finishTutorial() {
        // Add HomeScreen.class
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
