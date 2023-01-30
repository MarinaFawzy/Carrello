
package com.example.convertapp.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import com.example.convertapp.Category.Addcategory;
import com.example.convertapp.Category.Categorylist;
import com.example.convertapp.R;
import com.example.convertapp.Showorders.feedback;
import com.example.convertapp.Showorders.orderlist;
import com.example.convertapp.prduct.Addproduct;
import com.example.convertapp.prduct.productlist;

import java.util.Random;

public class Admincontrol extends AppCompatActivity implements View.OnClickListener   {
View addcategorys,addproducts,showcategory,showproducts,Report,chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admincontrol);

        showcategory = findViewById(R.id.Category);
        showproducts = findViewById(R.id.product);
        Report=findViewById(R.id.report);
        chart=findViewById(R.id.chart);

        showcategory.setOnClickListener(this::onClick);
        showproducts.setOnClickListener(this::onClick);
       Report.setOnClickListener(this::onClick);
       chart.setOnClickListener(this::onClick);
if(feedback.notification!=null) {
    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
    notificationManager.notify(new Random().nextInt(), feedback.notification);

}

    }
    @Override
    public void onClick(View v) {


        switch (v.getId())
        {

            case R.id.Category:
               Intent category=new Intent(Admincontrol.this, Categorylist.class);
                startActivity(category);
                break;
            case R.id.product:
                Intent product=new Intent(Admincontrol.this, productlist.class);
                startActivity(product);
                break;
            case R.id.report:
                Intent report=new Intent(Admincontrol.this, orderlist.class);
                startActivity(report);
                break;
            case R.id.chart:
                Intent chart=new Intent(Admincontrol.this, Chart.class);
                startActivity(chart);
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addcategory:
              Intent addcategory=new Intent(Admincontrol.this, Addcategory.class);
              startActivity(addcategory);
                return true;
            case R.id.Addproduct:
                Intent addproduct=new Intent(Admincontrol.this, Addproduct.class);
                startActivity(addproduct);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();

   
    }
}