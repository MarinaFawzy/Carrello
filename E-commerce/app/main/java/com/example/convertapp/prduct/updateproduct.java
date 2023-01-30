package com.example.convertapp.prduct;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.convertapp.R;
import com.example.convertapp.database.Carrelloshop;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class updateproduct extends AppCompatActivity implements View.OnClickListener {
ImageView updateimage;
    EditText name,price,quantity,categoryid;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateproduct);
        updateimage=findViewById(R.id.imageView);
         name=findViewById(R.id.nameedit);
         price=findViewById(R.id.eprice);
         quantity=findViewById(R.id.equantity);
         categoryid=findViewById(R.id.ecartid);
        Button add=findViewById(R.id.updates);
        Button show=findViewById(R.id.show);
      add.setOnClickListener(this::onClick);

        show.setOnClickListener(this::onClick);
        Intent i=getIntent();
         id= i.getIntExtra("id",0);



        updateimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);

            }
        });





    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        byte[] byteArray = stream.toByteArray();
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        byte[] array = buffer.array();

        return array;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 100 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                updateimage.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.updates:
              Carrelloshop carrelloshop=new Carrelloshop(this);
              carrelloshop.updateproduct(
               name.getText().toString(),
              imageViewToByte(updateimage), id,Integer.parseInt( price.getText().toString()),
           Integer.parseInt(quantity.getText().toString()),Integer.parseInt(categoryid.getText().toString()));
                Toast.makeText(getApplicationContext(), "Update successfully! ", Toast.LENGTH_LONG).show();
                name.setText("");
                price.setText("");
                quantity.setText("");
                categoryid.setText("");
                updateimage.setImageResource(R.mipmap.ic_launcher);


                break;

            case R.id.show:

                Intent i=new Intent(updateproduct.this, productlist.class);
                startActivity(i);

                break;

        }
        }
}