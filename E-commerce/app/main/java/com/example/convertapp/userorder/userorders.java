package com.example.convertapp.userorder;

import android.database.Cursor;
import android.database.CursorWindow;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.convertapp.R;
import com.example.convertapp.database.Carrelloshop;
import com.example.convertapp.loginandregisteration.login;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class userorders extends AppCompatActivity {
    ListView listviews;
    ArrayList<order> list;
    userorderadapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userorders);
Carrelloshop carrelloshop=new Carrelloshop(this);
        listviews = findViewById(R.id.list);
        TextView totalcost=findViewById(R.id.totalcost);
        list = new ArrayList<>();
        adapter = new userorderadapter(this, list);
        listviews.setAdapter(adapter);
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }
        list.clear();

        Cursor cursor;


        cursor = carrelloshop.getcart(login.uid);

        Bundle myBundle = getIntent().getExtras();
        int[] qut;

        qut = myBundle.getIntArray("quantity");
        int i=0;

        int sum = myBundle.getInt("sum", 0);
        totalcost.setText(" "+sum);
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            int price = cursor.getInt(3);
            int uids = cursor.getInt(4);

            list.add(new order(name, image, id, price,qut[i]));
            cursor.moveToNext();
            i++;
        }




    }
}