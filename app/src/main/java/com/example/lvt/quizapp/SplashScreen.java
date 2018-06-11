package com.example.lvt.quizapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    //TextView name,version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timer=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finally{
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    finish();
                }
            }
        };
        timer.start();

        /*name=(TextView)findViewById(R.id.name);
        version=(TextView)findViewById(R.id.version);

        Typeface customFont=Typeface.createFromAsset(getAssets(),"fonts/papaya.otf");
        name.setTypeface(customFont);*/

    }
}
