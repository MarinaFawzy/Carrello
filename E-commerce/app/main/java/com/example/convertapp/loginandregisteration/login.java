package com.example.convertapp.loginandregisteration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.convertapp.R;
import com.example.convertapp.Userhome.userhome;
import com.example.convertapp.admin.Admincontrol;
import com.example.convertapp.database.Carrelloshop;
import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {
    Carrelloshop carrelloshop;
    EditText name;
    TextInputLayout pass;
   public static int uid;
    public static String adminname=" ";
    public static String adminpass=" ";

    CheckBox rememberme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        carrelloshop = new Carrelloshop(this);
        TextView reg = findViewById(R.id.register);
        name = findViewById(R.id.Ename);
        pass = findViewById(R.id.epass);
        Button login = findViewById(R.id.login);
        rememberme = findViewById(R.id.checkBox);
        TextView forgetpassword=findViewById(R.id.forgetpassword);


        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), com.example.convertapp.loginandregisteration.forgetpassword.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("remember", MODE_PRIVATE);
        String Username = sharedPreferences.getString("name", null);
        String Userpass = sharedPreferences.getString("pass", null);
        uid = sharedPreferences.getInt("id", 0);


        if (Username!=null) {
            if(Username.equals("admin")) {
                Intent i = new Intent(login.this, Admincontrol.class);

                startActivity(i);
            }
            else {
                Intent i = new Intent(login.this, userhome.class);

                startActivity(i);

            }
        }


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(login.this, registration.class);
                startActivity(next);


            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (name.getText().toString().isEmpty()) {
                    name.setError("Enter Name!");
                    name.requestFocus();
                } else if (pass.getEditText().getText().toString().isEmpty()) {
                    pass.setError("Enter Password!");
                    pass.requestFocus();
                } else {





                   Cursor cursor = carrelloshop.userLogin(name.getText().toString(), pass.getEditText().getText().toString());
                   if (cursor.getCount() > 0 && name.getText().toString() .equals( "admin") &&
                           pass.getEditText().getText().toString() .equals( "admin")&& rememberme.isChecked()) {
                       uid=cursor.getInt(0);
                       adminname=name.getText().toString();
                       adminpass=pass.getEditText().getText().toString();
                       getSharedPreferences("remember", MODE_PRIVATE).edit().
                               putInt("id",uid).
                               putString("name", name.getText().toString()).putString("pass", pass.getEditText().getText().toString()).commit();

                       Intent ii = new Intent(login.this, Admincontrol.class);
                       startActivity(ii);

                   }
                 else  if (cursor.getCount() > 0 && rememberme.isChecked()) {
                       uid=cursor.getInt(0);
                       getSharedPreferences("remember", MODE_PRIVATE).edit().
                               putInt("id",uid).
                               putString("name", name.getText().toString()).putString("pass", pass.getEditText().getText().toString()).commit();
                       Intent i = new Intent(login.this, userhome.class);
                       startActivity(i);
                   }


                   else if(cursor.getCount() > 0&&name.getText().toString() .equals( "admin") &&
                           pass.getEditText().getText().toString() .equals( "admin")) {
                       uid=cursor.getInt(0);
                       adminname=name.getText().toString();
                       adminpass=pass.getEditText().getText().toString();


                       Intent ii = new Intent(login.this, Admincontrol.class);
                       startActivity(ii);

                   }
                   else if(cursor.getCount() > 0 ) {
                       uid=cursor.getInt(0);
                       Intent i = new Intent(login.this, userhome.class);
                       startActivity(i);

                   }
                 else {
                       Toast.makeText(getApplicationContext(), "Username or Password invalid! ", Toast.LENGTH_LONG).show();
                   }

               }
            }
        });

    }
}