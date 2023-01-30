package com.example.convertapp.loginandregisteration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.convertapp.R;
import com.example.convertapp.database.Carrelloshop;
import com.google.android.material.textfield.TextInputLayout;

public class changepass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        Button change=findViewById(R.id.changepass);
        TextInputLayout confirm = findViewById(R.id.password);
        TextInputLayout pass = findViewById(R.id.confirmpassword);


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                if (!pass.getEditText().getText().toString().equals(confirm.getEditText().getText().toString())) {
                    confirm.setError(" confirm password not match the password  ");
                    confirm.requestFocus();
                }
                else {

                    Carrelloshop carrelloshop=new Carrelloshop(getApplicationContext());
                    carrelloshop.updatepass(pass.getEditText().getText().toString(), forgetpassword.useremail);
                    Toast.makeText(getApplicationContext(),"change password successfully go to login!",Toast.LENGTH_LONG).show();
                    Intent login=new Intent(getApplicationContext(), com.example.convertapp.loginandregisteration.login.class);
                    startActivity(login);

                }
            }
        });

    }
}