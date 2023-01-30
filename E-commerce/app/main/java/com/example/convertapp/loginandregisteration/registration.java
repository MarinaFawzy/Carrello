package com.example.convertapp.loginandregisteration;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.convertapp.R;
import com.example.convertapp.database.Carrelloshop;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class registration extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener ,DatePickerDialog.OnDateSetListener {
    EditText name,username,job,date,number,emails;
Carrelloshop carrelloshophelper=new Carrelloshop(this);

    String genderr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        RadioGroup gender = findViewById(R.id.gender);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        name = findViewById(R.id.Ename);
        username = findViewById(R.id.Euname);
        job = findViewById(R.id.Ejob);
        date = findViewById(R.id.edate);
        number = findViewById(R.id.Ephone);
        emails=findViewById(R.id.Eemail);
        TextInputLayout pass = findViewById(R.id.epass);
        Button signin=findViewById(R.id.signin);

        TextInputLayout confirmpass = findViewById(R.id.ecpass);
        Button signup = findViewById(R.id.signup);

        gender.setOnCheckedChangeListener(this);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login=new Intent(getApplicationContext(), com.example.convertapp.loginandregisteration.login.class);
                startActivity(login);
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datedialog datedialog = new datedialog();
                datedialog.show(getSupportFragmentManager(), "date");
            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().isEmpty()) {
                    name.setError("Enter Name");
                    name.requestFocus();
                }

                else if (emails.getText().toString().isEmpty()) {
                    emails.setError("Enter emails");
                    emails.requestFocus();
                }
                else if (username.getText().toString().isEmpty()) {
                    username.setError("Enter UserName");
                    username.requestFocus();
                } else if (job.getText().toString().isEmpty()) {
                    job.setError("Enter job");
                    job.requestFocus();
                } else if (date.getText().toString().isEmpty()) {
                    date.setError("Enter Birthday");
                    date.requestFocus();
                } else if (number.getText().toString().isEmpty()) {
                    number.setError("Enter phone");
                    number.requestFocus();
                } else if (pass.getEditText().getText().toString().isEmpty()) {
                    confirmpass.setError("Enter password ");
                    pass.requestFocus();
                } else if (confirmpass.getEditText().getText().toString().isEmpty()) {
                    confirmpass.setError("Enter confirm password  ");
                    confirmpass.requestFocus();
                } else if (!pass.getEditText().getText().toString().equals(confirmpass.getEditText().getText().toString())) {
                    confirmpass.setError(" confirm password not match the password  ");
                    confirmpass.requestFocus();
                } else {
                    carrelloshophelper.insertuser(name.getText().toString(), username.getText().toString(), pass.getEditText().getText().toString(), genderr,
                            date.getText().toString(), job.getText().toString(), Integer.parseInt(number.getText().toString()),
                            emails.getText().toString() );

                    Toast.makeText(getApplicationContext(),"logged in  successfully.",Toast.LENGTH_LONG).show();
                    Intent next=new Intent(registration.this,login.class);
                    startActivity(next);


                }
            }
        });
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.male:
                genderr="male";
                break;
            case R.id.female:
                genderr="female";
                break;
        }

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar c=Calendar.getInstance();
        month++;
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        date.setText(year+"/"+month+"/"+dayOfMonth);

    }
}