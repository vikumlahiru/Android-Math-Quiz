package com.example.lvt.quizapp;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView title;
    Button newGame, howToPlay, about, soundOn, soundOFF, exit;

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(MainActivity.this, BackgroundMusicService.class));
        musicValid();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(MainActivity.this, BackgroundMusicService.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startService(new Intent(MainActivity.this, BackgroundMusicService.class));
        Typeface gasalt=Typeface.createFromAsset(getAssets(),"fonts/gasalt.ttf");

        howToPlay = (Button) findViewById(R.id.howToBtn);
        howToPlay.setTypeface(gasalt);
        about = (Button) findViewById(R.id.aboutBtn);
        about.setTypeface(gasalt);
        soundOn = (Button) findViewById(R.id.playBtn);
        soundOFF = (Button) findViewById(R.id.stopBtn);
        exit = (Button) findViewById(R.id.exitBtn);


        title = (TextView) findViewById(R.id.title);
        Typeface yourWorld = Typeface.createFromAsset(getAssets(), "fonts/pac.TTF");
        title.setTypeface(yourWorld);

        newGame = (Button) findViewById(R.id.newGameBtn);
        newGame.setTypeface(gasalt);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, BackgroundMusicService.class));
                finish();
                startActivity(new Intent(MainActivity.this, QuestionActivity.class));
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder
                        .setTitle("Confirm Exit!")
                        .setMessage("Do you want to quit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                stopService(new Intent(MainActivity.this, BackgroundMusicService.class));
                                finish();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        howToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder
                        .setTitle("How To Play")
                        .setMessage("* There questions related to mathematics. \n* You will have to answer questions without failing.\n * If you select a wrong choice you will not be able to continue")
                        .setCancelable(false)
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder
                        .setTitle("About")
                        .setMessage("Created By Lahiru Vikum Thamel\nVersion 1.0")
                        .setCancelable(false)
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });


        musicValid();
        soundOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(), BackgroundMusicService.class));
                musicValid();
            }
        });

        soundOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(), BackgroundMusicService.class));
                musicValid();
            }
        });


    }

    private boolean isServiceRunning(Class<?> serviceClass) {
        boolean rst = false;
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                rst = true;
            }
        }
        return rst;
    }

    private void musicValid() {
        if (isServiceRunning(BackgroundMusicService.class)) {
            soundOn.setVisibility(View.GONE);
            soundOFF.setVisibility(View.VISIBLE);
        } else {
            soundOn.setVisibility(View.VISIBLE);
            soundOFF.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {

    }
}
