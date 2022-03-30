package com.example.pawfect_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Welcome_Page extends AppCompatActivity {
    ImageView lets_go, image1, image2, image3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        lets_go = findViewById(R.id.lets_go);
        image1 = findViewById(R.id.imageView);
        image2 = findViewById(R.id.img);
        image3 = findViewById(R.id.imageView4);
        lets_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome_Page.this, MainActivity_Login.class);
                startActivity(intent);
            }
        });
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome_Page.this, MainActivity_Login.class);
                startActivity(intent);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome_Page.this, MainActivity_Login.class);
                startActivity(intent);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome_Page.this, MainActivity_Login.class);
                startActivity(intent);
            }
        });
    }
}