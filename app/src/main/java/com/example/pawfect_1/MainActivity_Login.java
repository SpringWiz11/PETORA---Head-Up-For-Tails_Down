package com.example.pawfect_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity_Login extends AppCompatActivity {
    Button mRegisterBtn,mLoginBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        mRegisterBtn=findViewById(R.id.register_btn);
        mLoginBtn=findViewById(R.id.login_btn);
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
startActivity(new Intent(MainActivity_Login.this,RegisterActivity.class));
            }
        });
mLoginBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity_Login.this,LoginActivity.class));
    }
});
    }
}