package com.example.convertapp.loginandregisteration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.convertapp.R;

public class forgetcode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetcode);
        EditText Code=findViewById(R.id.code);
        Button continues=findViewById(R.id.Continue);
        Intent i=getIntent();
        int codes=  i.getIntExtra("code",0);
        continues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Integer.parseInt(Code.getText().toString())==codes)  {

               Intent neIntent=new Intent(getApplicationContext(), changepass.class);
               startActivity(neIntent);


                }
                else {
                    Toast.makeText(getApplicationContext(),"Invalid code",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}