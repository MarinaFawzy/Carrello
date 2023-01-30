package com.example.convertapp.Userhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.convertapp.Category.Categorylist;
import com.example.convertapp.R;
import com.example.convertapp.Showorders.feedback;
import com.example.convertapp.prduct.productlist;
import com.example.convertapp.shoppingcart.cartlist;


public class userhome extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);
        View category=findViewById(R.id.category);
        category.setOnClickListener(this::onClick);
        View product=findViewById(R.id.product);
        product.setOnClickListener(this::onClick);
        View cart=findViewById(R.id.cart);
        cart.setOnClickListener(this::onClick);
        View feedback=findViewById(R.id.feedback);
        feedback.setOnClickListener(this::onClick);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.category:
                Intent category=new Intent(userhome.this,Categorylist.class);
                startActivity(category);
                break;
            case R.id.product:
                Intent product=new Intent(userhome.this,productlist.class);
                startActivity(product);
                break;
            case R.id.feedback:
                Intent feedbacks=new Intent(userhome.this, feedback.class);
                startActivity(feedbacks);


                break;
            case R.id.cart:
                Intent cart=new Intent(userhome.this,cartlist.class);
                startActivity(cart);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();

        //  super.onBackPressed();
    }
}