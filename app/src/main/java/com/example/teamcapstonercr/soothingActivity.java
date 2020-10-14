package com.example.teamcapstonercr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.teamcapstonercr.soundtherapy.SoundTypes;

public class soothingActivity extends AppCompatActivity {
    MediaPlayer player;
    SeekBar volume ;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soothing);

        player = MediaPlayer.create(soothingActivity.this,R.raw.relax);
        player.setLooping(true);
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
                Toast.makeText(soothingActivity.this,"SeekBar in StartTracking",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(soothingActivity.this,"SeekBar in StopTracking",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void playBtn(View v){
        player.start();
        player.setLooping(true);
    }
    protected void onPause(){
        super.onPause();
        player.release();
    }
    public void pauseBtn(View v){
        player.pause();
    }
    public void stopBtn(View v){
        player.stop();
    }
    public void repeatBtn(View v){
        player.setLooping(true);
    }
    public void finish(View view) {
        finish();
    }
    public void clickable(View v){
        Intent in = new Intent(soothingActivity.this, SoundTypes.class);
        startActivity(in);
    }
}
