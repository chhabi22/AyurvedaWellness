package com.example.teamcapstonercr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamcapstonercr.extras.About;
import com.example.teamcapstonercr.extras.Dashboard;
import com.example.teamcapstonercr.extras.Feedback;
import com.example.teamcapstonercr.journal.JournalFirst;
import com.example.teamcapstonercr.soundtherapy.SoundTypes;
import com.example.teamcapstonercr.usersinfo.Register;
import com.example.teamcapstonercr.yog.Typeunique;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeScreen extends AppCompatActivity {

    TextView journalText;
    TextView yogaText;
    TextView soundText;
    TextView quizText;
    Button verifyemailbtn;
    TextView verifyemail;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        verifyemail = findViewById(R.id.verifyemail);
        verifyemailbtn = findViewById(R.id.verifyemailbtn);

        fAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = fAuth.getCurrentUser();

        if (!user.isEmailVerified())
        {
            verifyemailbtn.setVisibility(View.VISIBLE);
            verifyemail.setVisibility(View.VISIBLE);

            verifyemailbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(v.getContext(), "Verification Email Has Been Sent", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG","onFailure: " + e.getMessage());
                        }
                    });
                }
            });
        }

        // INTENTS
        journalText = findViewById(R.id.journalbtn);
        journalText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, JournalReady.class);
                startActivity(intent);
            }
        });

        yogaText = findViewById(R.id.yogaBtn);
        yogaText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yogaIntent = new Intent(HomeScreen.this, Typeunique.class);
                startActivity(yogaIntent);
            }
        });

        soundText = findViewById(R.id.soundbtn);
        soundText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent soundIntent = new Intent(HomeScreen.this,SoundTypes.class);
                startActivity(soundIntent);
            }
        });

        quizText = findViewById(R.id.quizbtn);
        quizText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizIntent = new Intent(HomeScreen.this, DoshaQ.class);
                startActivity(quizIntent);
            }
        });
        // INTENTS END

// BOTTOM NAVIGATION -------------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
      bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
              switch (menuItem.getItemId()) {

                  case R.id.dashboard:
                      Intent intent1 = new Intent(HomeScreen.this, Dashboard.class);
                      startActivity(intent1);
                      break;
                  case R.id.feedback:
                      Intent intent2 = new Intent(HomeScreen.this, Feedback.class);
                      startActivity(intent2);
                      break;
                  case R.id.about:
                      Intent intent3 = new Intent(HomeScreen.this, About.class);
                      startActivity(intent3);
                      break;
              }
                  return false;

          }
      });

      // END OF BOTTOM NAVIGATION ------------------------------------

    }
} //END

