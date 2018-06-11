package com.example.lvt.quizapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class BackgroundMusicService extends Service{

    private MediaPlayer backgroundMusic;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        backgroundMusic=MediaPlayer.create(this,R.raw.menu);
        backgroundMusic.setLooping(true);
        backgroundMusic.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        backgroundMusic.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
