package com.example.convertapp.loginandregisteration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.convertapp.R;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        Handler hh=new Handler();
        hh.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(start.this, login.class));

            }
        },3000);
    }

}