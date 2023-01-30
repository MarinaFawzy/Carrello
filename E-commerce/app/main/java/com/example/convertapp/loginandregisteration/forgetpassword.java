package com.example.convertapp.loginandregisteration;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.convertapp.R;
import com.example.convertapp.database.Carrelloshop;

public class forgetpassword extends AppCompatActivity implements View.OnClickListener{
Button change;
TextView back;
    View view;
    Cursor cursor;
    public static String useremail;
    EditText emails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        view=findViewById(R.id.forgetpassword);
         emails=findViewById(R.id.email);
        change=findViewById(R.id.Changes);
        back=findViewById(R.id.backs);
        change.setOnClickListener(this::onClick);
        back.setOnClickListener(this::onClick);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Changes:
                Carrelloshop carrelloshop=new Carrelloshop(this);


                Cursor c= carrelloshop.checkuser(emails.getText().toString());

                if(c.getCount()>0){
                    int min = 109900;
                    int max = 340056;

                    useremail=emails.getText().toString();

                    useremail=emails.getText().toString();

                            int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);


                            senEmail(" "+random_int,useremail);

                    Intent next=new Intent(getApplicationContext(), forgetcode.class);
                    next.putExtra("code",random_int);

                    startActivity(next);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Invalid email",Toast.LENGTH_LONG).show();
                        }
                        break;

                    case R.id.backs:
                        Intent intent = new Intent(getApplicationContext(), login.class);
                        startActivity(intent);
                        break;

                }
        }
private void senEmail(String msg, String email) {

        String mSubject = " Carrello shop forgot your password";
        String mMessage = "This is your account recovery code \n \n " + msg;


        JavaMailAPI javaMailAPI = new JavaMailAPI(this, email, mSubject, mMessage);

        javaMailAPI.execute();
        }



        }