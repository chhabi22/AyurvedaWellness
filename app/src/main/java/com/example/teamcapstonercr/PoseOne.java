package com.example.teamcapstonercr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class PoseOne extends AppCompatActivity {
    TextView timeCount;
    Button start, reset;
    CountDownTimer countDownTimer;
    boolean timerRunning;
    long startTime = 600000;
    long remainingTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pose_one);

        timeCount = findViewById(R.id.timerCount);
        start = findViewById(R.id.startBtn);
        reset = findViewById(R.id.resetBtn);
        remainingTime = startTime;

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    pauseTimer();
                }
                else{
                    startTimer();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    private void resetTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        remainingTime = startTime;
        updateCounterTime();
        updateButton();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(remainingTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = millisUntilFinished;
                updateCounterTime();
            }

            @Override
            public void onFinish() {

                timerRunning = false;
                updateButton();
            }
        }.start();
        timerRunning = true;
        updateButton();
    }

    private void updateCounterTime() {
        int minutes = (int) (remainingTime / 1000) / 60;
        int seconds = (int) (remainingTime/ 1000) % 60;

        String timeleftformatted = String.format(Locale.getDefault(), "%02d: %02d", minutes, seconds);
        timeCount.setText(timeleftformatted);
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        updateButton();
    }

    private void updateButton() {
        if (timerRunning){
            reset.setVisibility(View.VISIBLE);
            start.setText("Pause");
        }
        else{
            start.setText("Start");
            if (remainingTime<1000){
                start.setVisibility(View.GONE);
            }
            else{
                start.setVisibility(View.VISIBLE);
            }
            if (remainingTime<startTime){
                reset.setVisibility(View.VISIBLE);
            }
            else{
                reset.setVisibility(View.GONE);
            }
        }
    }
    //BACK NAVIGATION
    public void finish(View view) {

        finish();
    }
}
