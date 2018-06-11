package com.example.lvt.quizapp;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuestionActivity extends AppCompatActivity {

    TextView scoreTV, questionTV;


    Button answer1, answer2, answer3, answer4, playBackground, stopBackground;

    private Questions mQuestions = new Questions();

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionsLength = mQuestions.questions.length;

    Random r;

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(QuestionActivity.this, BackgroundMusicService.class));
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Typeface gasalt=Typeface.createFromAsset(getAssets(),"fonts/gasalt.ttf");


        startService(new Intent(getApplicationContext(), BackgroundMusicService.class));


        final MediaPlayer dingSound = MediaPlayer.create(this, R.raw.ding);
        final MediaPlayer noSound = MediaPlayer.create(this, R.raw.no);

        r = new Random();
        int num = r.nextInt(mQuestionsLength);


        scoreTV = (TextView) findViewById(R.id.scoreTV);
        questionTV = (TextView) findViewById(R.id.questionTV);
        answer1 = (Button) findViewById(R.id.answer1Btn);
        answer2 = (Button) findViewById(R.id.answer2Btn);
        answer3 = (Button) findViewById(R.id.answer3Btn);
        answer4 = (Button) findViewById(R.id.answer4Btn);
        playBackground = (Button) findViewById(R.id.playBtn);
        stopBackground = (Button) findViewById(R.id.stopBtn);

        questionTV.setTypeface(gasalt);
        answer1.setTypeface(gasalt);
        answer2.setTypeface(gasalt);
        answer3.setTypeface(gasalt);
        answer4.setTypeface(gasalt);
        scoreTV.setTypeface(gasalt);

        musicValid();
        updateQuestion(num);


        playBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(), BackgroundMusicService.class));
                musicValid();
            }
        });

        stopBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getApplicationContext(), BackgroundMusicService.class));
                musicValid();
            }
        });

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer1.getText() == mAnswer) {
                    mScore++;
                    scoreTV.setText("Score : " + mScore);
                    updateQuestion(r.nextInt(mQuestionsLength));
                    dingSound.start();
                } else {
                    noSound.start();
                    gameOver();
                }
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer2.getText() == mAnswer) {
                    mScore++;
                    scoreTV.setText("Score : " + mScore);
                    updateQuestion(r.nextInt(mQuestionsLength));
                    dingSound.start();
                } else {
                    noSound.start();
                    gameOver();
                }
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer3.getText() == mAnswer) {
                    mScore++;
                    scoreTV.setText("Score : " + mScore);
                    updateQuestion(r.nextInt(mQuestionsLength));
                    dingSound.start();
                } else {
                    noSound.start();
                    gameOver();
                }
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer4.getText() == mAnswer) {
                    mScore++;
                    scoreTV.setText("Score : " + mScore);
                    updateQuestion(r.nextInt(mQuestionsLength));
                    dingSound.start();
                } else {
                    noSound.start();
                    gameOver();
                }
            }
        });

    }

    private void updateQuestion(int i) {
        questionTV.setText(mQuestions.getQuestion(i));
        answer1.setText(mQuestions.getChoice1(i));
        answer2.setText(mQuestions.getChoice2(i));
        answer3.setText(mQuestions.getChoice3(i));
        answer4.setText(mQuestions.getChoice4(i));

        mAnswer = mQuestions.getAnswer(i);
    }

    private void gameOver() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuestionActivity.this);
        alertDialogBuilder
                .setMessage("Game Over! Your score is " + mScore + " points.")
                .setCancelable(false)
                .setPositiveButton("NEW GAME", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        stopService(new Intent(QuestionActivity.this, BackgroundMusicService.class));
                        finish();
                        startActivity(new Intent(getApplicationContext(), QuestionActivity.class));

                    }
                })
                .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        stopService(new Intent(QuestionActivity.this, BackgroundMusicService.class));
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
            playBackground.setVisibility(View.GONE);
            stopBackground.setVisibility(View.VISIBLE);
        } else {
            playBackground.setVisibility(View.VISIBLE);
            stopBackground.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(QuestionActivity.this);
        dialog
                .setTitle("Never give up!")
                .setMessage("Abandon Challenge?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                stopService(new Intent(QuestionActivity.this, BackgroundMusicService.class));
                finish();
                startActivity(new Intent(QuestionActivity.this, MainActivity.class));
            }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog=dialog.create();
        alertDialog.show();

    }
}
