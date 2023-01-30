package com.example.convertapp.shoppingcart;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.convertapp.R;
import com.example.convertapp.Userhome.userhome;
import com.example.convertapp.database.Carrelloshop;
import com.example.convertapp.loginandregisteration.login;
import com.example.convertapp.Showorders.map;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class order extends AppCompatActivity implements View.OnClickListener {
    EditText address;
    int listsize;
    int[] qut;
    int[] pid;
    FusedLocationProviderClient fusedLocationProviderClient;
String locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent order = getIntent();
        int sum = order.getIntExtra("sum", 0);
        listsize = order.getIntExtra("listsize", 0);
        Bundle myBundle = getIntent().getExtras();
        pid = myBundle.getIntArray("productid");
        qut = myBundle.getIntArray("quantity");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        TextView tcost = findViewById(R.id.totalcost);
        tcost.setText(String.valueOf(sum));
        address = findViewById(R.id.address);
        Button cancel = findViewById(R.id.Cancel);
        Button confirm = findViewById(R.id.Confirm);
        cancel.setOnClickListener(this::onClick);
        confirm.setOnClickListener(this::onClick);
TextView location=findViewById(R.id.maps);
location.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent i = new Intent(getApplicationContext(), map.class);
        startActivityForResult(i, 1);
    }

   });
    }
   @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                LatLng dest = data.getParcelableExtra("result");
                Toast.makeText(getApplicationContext()," "+dest,Toast.LENGTH_LONG).show();
        Geocoder geocoder=new Geocoder(getApplicationContext());
                String addr=" ";
                try {
                    List<Address> list=geocoder.getFromLocation(dest.latitude,dest.longitude,1);
                    if(!list.isEmpty()){
                        addr=list.get(0).getAddressLine(0);
                      //  Toast.makeText(getApplicationContext(),addr,Toast.LENGTH_LONG).show();
                        address.setText(addr);

                    }
                } catch ( IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
        public void onClick (View v){
            switch (v.getId()) {
                case R.id.Cancel:
                    Intent cart = new Intent(order.this, cartlist.class);
                    startActivity(cart);

                    break;
                case R.id.Confirm:
                    if (address.getText().toString().isEmpty()) {
                        address.setError("Enter Address");
                        address.requestFocus();

                    }
                    else {
                        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                        Carrelloshop carrelloshop = new Carrelloshop(this);

                        if (listsize == 0) {
                            Toast.makeText(getApplicationContext(), "the cart is empty", Toast.LENGTH_LONG).show();

                        }
                        if (listsize > 0) {


                            carrelloshop.insertorder(address.getText().toString(), currentDateTimeString, login.uid);
                            Cursor c = carrelloshop.getorder();
                            c.moveToLast();
                            for (int i = 0; i < pid.length; i++) {
                                Cursor cname = carrelloshop.getproductbuid(pid[i]);
                                carrelloshop.insertorderdetailes(c.getInt(0), pid[i], cname.getString(1), qut[i]);


                            }

                            carrelloshop.deletcartperuser(login.uid);
                            Intent home = new Intent(order.this, userhome.class);
                            startActivity(home);


                        }
                    }
                    break;

            }

        }
    }
