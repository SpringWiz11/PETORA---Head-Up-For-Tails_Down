package com.example.pawfect_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView textView=findViewById(R.id.textView);
    textView.animate().translationY(-1100).setDuration(2500).setStartDelay(2500);
        Thread thread=new Thread(){
            public void run(){
                try{
                    Thread.sleep(6000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally{
                    Intent intent=new Intent(SplashActivity.this,Welcome_Page.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
    }

    }
