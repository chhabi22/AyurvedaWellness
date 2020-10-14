package com.example.teamcapstonercr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.teamcapstonercr.soundtherapy.SoundTypes;

public class normalActivity extends AppCompatActivity {
    MediaPlayer player1,player2;
    SeekBar volume ;
    AudioManager audioManager;
    private Handler myHandler = new Handler();;
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        player1 = MediaPlayer.create(normalActivity.this,R.raw.normal);
        player1.setLooping(true);
        volume = (SeekBar)findViewById(R.id.slider);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        volume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(normalActivity.this,"SeekBar in StartTracking",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(normalActivity.this,"SeekBar in StopTracking",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void playBtn(View v){
        player1.start();
        player1.setLooping(true);
    }
    protected void onPause(){
        super.onPause();
        player1.release();
    }

    public void pauseBtn(View v){
        player1.pause();
    }
    public void stopBtn(View v) {
        player1.stop();
        player1.release();
    }
    public void repeatBtn(View v){
        player1.setLooping(true);
    }
    public void finish(View view) {
        finish();
    }
    public void clickable(View v){
        Intent in = new Intent(normalActivity.this, SoundTypes.class);
        startActivity(in);
    }
}
