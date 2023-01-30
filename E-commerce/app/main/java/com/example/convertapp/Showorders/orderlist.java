package com.example.convertapp.Showorders;

import android.database.Cursor;
import android.database.CursorWindow;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.convertapp.R;
import com.example.convertapp.database.Carrelloshop;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class orderlist extends AppCompatActivity {
    ListView listviews;
    ArrayList<ordermodel> list;
    orderadapter orderadapter = null;
    Carrelloshop carrelloshop = new Carrelloshop(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        listviews = findViewById(R.id.list);
        list = new ArrayList<>();

        TextView user=findViewById(R.id.Specificuser);
EditText names=findViewById(R.id.name);
names.setVisibility(View.INVISIBLE);
        orderadapter = new orderadapter(this, list);
        listviews.setAdapter(orderadapter);
        Cursor Spinner=carrelloshop.getorder();
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> arraySpinner = new ArrayList<String>();
        arraySpinner.add("None");
        while (!Spinner.isAfterLast()){
            arraySpinner.add(Spinner.getString(2));
            Spinner.moveToNext();

        }
user.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        names.setVisibility(View.VISIBLE);
    }
});

        android.widget.Spinner s = (Spinner) findViewById(R.id.date);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectiondate = s.getSelectedItem().toString();
        Cursor cursor;

        if (selectiondate == "None" && names.getText().toString().isEmpty()) {
            cursor = carrelloshop.getorder();
        }
       else if (selectiondate == "None" && !names.getText().toString().isEmpty()) {
            Cursor userid = carrelloshop.checkuser(names.getText().toString());
            if (userid.getCount() > 0) {
                cursor = carrelloshop.getorderuserid(userid.getInt(0));
            } else {
                cursor = null;
            }

        }

        else if (selectiondate != "None" && names.getText().toString().isEmpty()) {
            cursor = carrelloshop.getorder_date(selectiondate);
        } else if (selectiondate != "None" && !names.getText().toString().isEmpty()) {
            Cursor userid = carrelloshop.checkuser(names.getText().toString());

            if (userid.getCount() > 0) {
                cursor = carrelloshop.getorderuseridanddate(userid.getInt(0), selectiondate);
            } else {
                cursor = null;
            }


        } else {
            Cursor userid = carrelloshop.checkuser(names.getText().toString());
            if (userid.getCount() > 0) {
                cursor = carrelloshop.getorderuserid(userid.getInt(0));
            } else {
                cursor = null;
            }

        }


        list.clear();


        if (cursor == null) {
            list.clear();
            orderadapter.notifyDataSetChanged();


        } else {
            while (!cursor.isAfterLast()) {
                int oid = cursor.getInt(0);
                String add = cursor.getString(1);
                String date = cursor.getString(2);
                int cuid = cursor.getInt(3);


                Cursor cursor1 = carrelloshop.getorderdetailsid(oid);
                String product = " ";
                if (cursor1.getCount() > 0) {
                    while (!cursor1.isAfterLast()) {

                        String names = cursor1.getString(3);
                        int qut = cursor1.getInt(2);
                        product += names + " Quantity " + String.valueOf(qut) + " ";

                        cursor1.moveToNext();
                    }
                    list.add(new ordermodel(" "+cuid, date, product, oid));

                    cursor.moveToNext();
                }
            }
            orderadapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


});


    }
}