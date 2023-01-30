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

import com.example.convertapp.Category.Addcategory;
import com.example.convertapp.Category.Categorylist;
import com.example.convertapp.R;
import com.example.convertapp.database.Carrelloshop;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class Addproduct extends AppCompatActivity {
    EditText edtName,edtprice,edtquantaty,edtcartid;
    Button btnAdd, btnList;
    ImageView imageView;

    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        Carrelloshop carrelloshop=new Carrelloshop(this);
        edtName = (EditText) findViewById(R.id.edtName);
        edtprice = (EditText) findViewById(R.id.edtprice);
        edtquantaty = (EditText) findViewById(R.id.edtquantity);
        edtcartid = (EditText) findViewById(R.id.edtcartid);

        btnAdd = (Button) findViewById(R.id.btnAdd);
            btnList = (Button) findViewById(R.id.btnList);

            imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUEST_CODE_GALLERY);

                }
            });
btnList.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent productlist=new Intent(getApplicationContext(), com.example.convertapp.prduct.productlist.class);
        startActivity(productlist);
    }
});

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        carrelloshop.insertproduct(edtName.getText().toString(), imageViewToByte(imageView),
                               Integer.parseInt( edtprice.getText().toString()),
                                Integer.parseInt( edtquantaty.getText().toString()),
                                Integer.parseInt( edtcartid.getText().toString()) );
                        Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                        edtName.setText("");
                        edtcartid.setText("");
                        edtprice.setText("");
                        edtquantaty.setText("");
                        imageView.setImageResource(R.mipmap.ic_launcher);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });


            btnList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Addproduct.this, productlist.class);
                    startActivity(intent);
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

            if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
                Uri uri = data.getData();

                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);

                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView.setImageBitmap(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            super.onActivityResult(requestCode, resultCode, data);
        }

    }

